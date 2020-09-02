package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.NoticeDTO;
import poly.service.INoticeService;


@Controller
public class NoticeController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "NoticeService")
	private INoticeService noticeService;
	
	@RequestMapping(value = "notice/NoticeList", method=RequestMethod.GET)
	public String NoticeList(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	throws Exception{
		
		log.info(this.getClass().getName() + "NoticeList Start!");
		
		List<NoticeDTO> rList = noticeService.getNoticeList();
		
		if(rList==null) {
			rList = new ArrayList<NoticeDTO>();
		}
		
		model.addAttribute("rList", rList);
		
		rList = null;
		
		log.info(this.getClass().getName() + ".NoticeList end!");
		
		return "/notice/NoticeList";
	}
			
}
