package poly.controller;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.ChatDTO;
import poly.service.IChatService;
import poly.util.CmmUtil;

@Controller
public class ChatController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "ChatService")
	private IChatService chatService;
	
	@RequestMapping(value = "chat/index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info(this.getClass().getName() + ".index Start!");
		
		log.info(this.getClass().getName() + ".index End!");
		
		return "/chat/index";
	}
	
	@RequestMapping(value = "chat/intro")
	public String intro(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		log.info(this.getClass().getName() + ".intro Start!");
		
		session.setAttribute("SS_ROOM_NAME", "");
		session.setAttribute("SS_USER_NAME", "");
		
		String room_name = CmmUtil.nvl(request.getParameter("room_name"));
		String userNm = CmmUtil.nvl(request.getParameter("user_name"));
		
		log.info("userNm : " + userNm);
		log.info("room_name : " + room_name);
		
		session.setAttribute("SS_ROOM_NAME", room_name);
		session.setAttribute("SS_USER_NAME", userNm);
		
		ChatDTO pDTO = new ChatDTO();
		
		pDTO.setRoomKey("Chat_" + room_name);
		pDTO.setUserNm("관리자.");
		pDTO.setMsg(userNm + "님! [" + room_name + "] 채팅방 입장을 환영합니다.");
//		pDTO.setDateTime(DateUtil.getDateTime("yyyy.MM.dd hh:mm:ss"));
		
		chatService.insertChat(pDTO);
		
		log.info(this.getClass().getName() + ".intro End!");
		
		return "/chat/intro";
	}
	
	@RequestMapping(value = "chat/roomList")
	@ResponseBody
	public Set<String> roomList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info(this.getClass().getName() + ".roomList Start!");
		
		Set<String> rSet = chatService.getRoomList();
		
		if (rSet == null) {
			rSet = new LinkedHashSet<String>();
		}
		
		log.info(this.getClass().getName() + ".roomList End!");
		
		return rSet;
	}
	
	@RequestMapping(value = "chat/msg")
	@ResponseBody
	public List<ChatDTO> msg(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		log.info(this.getClass().getName() + ".msg Start!");
		
		String room_name = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));
		String userNm = CmmUtil.nvl((String) session.getAttribute("SS_USER_NAME"));
		
		String msg = CmmUtil.nvl(request.getParameter("send_msg"));
		
		log.info("userNm : " + userNm);
		log.info("room_name : " + room_name);
		log.info("msg : " + msg);
		
		List<ChatDTO> rList = null;
		
		if (msg.length() > 0) {
			ChatDTO pDTO = new ChatDTO();

			pDTO.setRoomKey("Chat_" + room_name);
			pDTO.setUserNm(userNm);
			pDTO.setMsg(msg);
//			pDTO.setDateTime(DateUtil.getDateTime("yyyy.MM.dd hh:mm:ss"));
			
			rList = chatService.insertChat(pDTO);
			
			if (rList == null) {
				rList = new LinkedList<ChatDTO>();
			}
		}
		
		log.info(this.getClass().getName() + ".msg End!");
		
		return rList;
	}
	
	@RequestMapping(value = "chat/getMsg")
	@ResponseBody
	public List<ChatDTO> getMsg(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		log.info(this.getClass().getName() + ".getMsg Start!");
		
		String room_name = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));
		
		log.info("room_name" + room_name);
		
		ChatDTO pDTO = new ChatDTO();
		
		pDTO.setRoomKey("Chat_" + room_name);
		
		List<ChatDTO> rList = chatService.getChat(pDTO);
		
		if (rList == null) {
			rList = new LinkedList<ChatDTO>();
		}
		
		pDTO = null;
		
		log.info(this.getClass().getName() + ".getMsg End!");
		
		return rList;
	}
}















