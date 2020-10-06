package poly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import poly.persistance.mongo.IMongoTestMapper;
import poly.service.INewsService;
import poly.service.IUserService;

@Controller
public class TestController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "UserService")
	IUserService userService;

	@Resource(name = "NewsService")
	INewsService newsService;

	@Resource(name = "MongoTestMapper")
	IMongoTestMapper mongoTestMapper;

	@RequestMapping(value = "mongoSelect", produces = "application/json; charset=UTF8")
	@ResponseBody
	public List<Map<String, Object>> select(HttpServletRequest request, Model model, HttpSession session)
			throws Exception {
		return mongoTestMapper.test();
	}

	@RequestMapping(value = "mongoSelectWithCondition", produces = "application/json; charset=UTF8")
	@ResponseBody
	public List<Map<String, Object>> selectWithCondition(HttpServletRequest request, Model model, HttpSession session)
			throws Exception {
		DBObject query = new BasicDBObject("name", "gildong");
		// name이 gildong인 데이터를 query변수에 대입
		
		return mongoTestMapper.selectWithCondition(query);
		// mongoTestMapper의 selectWithCondition에 query파라미터를 return
	}

	@RequestMapping(value = "mongoInsert", produces = "application/json; charset=UTF8")
	@ResponseBody
	public List<Map<String, Object>> insert(HttpServletRequest request, Model model, HttpSession session)
			throws Exception {
		Map<String, Object> obj = new HashMap<>();
		obj.put("name", "chang0");
		// name key에 chang0을 put
		obj.put("age", 24);
		obj.put("sentence", new String[] { "gdgdgd", "12341234" });
		// sentence key 문자열 배열로 gdgdgd, 12341234를 put

		mongoTestMapper.insert(obj);
		// mongoTestMapper의 insert에 obj 파라미터를 대입

		return mongoTestMapper.test();
	}

	@RequestMapping(value = "insertHerald", produces ="application/json; charset=UTF8")
	@ResponseBody
	public List<Map<String, Object>> insertHerald(HttpServletRequest request, Model model, HttpSession session) 
			throws Exception{
		
		log.info("###START### : insertHerald");
		
		// MongoDB에 키값으로 넣기 위한 obj 
		Map<String, Object> obj = new HashMap<>();
				
		// crawlingAll메서드의 rList를 pList에 대입 
		List<Map<String, Object>> pList = newsService.crawlingAll();
		
		// 첫번째 값은 Map형의 herald 뉴스
		Map<String, Object> pMap = pList.get(0);
		
		Iterator<String> keys = pMap.keySet().iterator();
		
		int i = 1;
		while(keys.hasNext()) {
			
			obj.put("newsName", pMap.get("newsName"));
			obj.put("newsTitle", pMap.get("newsTitle"));	
			obj.put("Lemma",pMap.get("newsLemmas"));
			obj.put("Token", pMap.get("newsTokens"));
			obj.put("OriginSentence", pMap.get("newsContents"));
			obj.put("newsUrl", pMap.get("newsUrl"));
			obj.put("InsertDate", pMap.get("insertDate"));
			
			mongoTestMapper.insert(obj);
			log.info("###START### : obj.put");
			
			if(i == 4) {
				return null;
			}
			
			pMap = pList.get(i);
			
			obj = null;
			obj = new HashMap<>();
			
			i++;
		}
//		obj.put("newsName", pMap.get("newsName"));
//		obj.put("newsTitle", pMap.get("newsTitle"));	
//		obj.put("Lemma",pMap.get("newsLemmas"));
//		obj.put("Token", pMap.get("newsTokens"));
//		obj.put("OriginSentence", pMap.get("newsContents"));
//		obj.put("newsUrl", pMap.get("newsUrl"));
//		obj.put("InsertDate", pMap.get("insertDate"));
//		
//		mongoTestMapper.insert(obj);
//		
//		obj = null;
//		obj = new HashMap<>();
//		
//		log.info("###END### : insert Herald");
//		
//		// 두번째 값은 Map형의 UK 뉴
//		pMap = pList.get(1);
//		
//		obj.put("newsName", pMap.get("newsName"));
//		obj.put("newsTitle", pMap.get("newsTitle"));	
//		obj.put("Lemma", pMap.get("newsLemmas"));
//		obj.put("Token", pMap.get("newsTokens"));
//		obj.put("OriginSentence",pMap.get("newsContents"));
//		obj.put("newsUrl", pMap.get("newsUrl"));
//		obj.put("InsertDate", pMap.get("insertDate"));
//		
//		mongoTestMapper.insert(obj);
//		
//		obj = null;
//		obj = new HashMap<>();
//		
//		log.info("###END### : insert UK");
//		
//		// 세번째 값은 Map - Times
//		pMap = pList.get(2);
//		
//		obj.put("newsName", pMap.get("newsName"));
//		obj.put("newsTitle", pMap.get("newsTitle"));	
//		obj.put("Lemma",pMap.get("newsLemmas"));
//		obj.put("Token", pMap.get("newsokens"));
//		obj.put("OriginSentence",pMap.get("newsContents"));
//		obj.put("newsUrl", pMap.get("newsUrl"));
//		obj.put("InsertDate", pMap.get("insertDate"));
//		
//		mongoTestMapper.insert(obj);
//		
//		obj = null;
//		obj = new HashMap<>();
//		
//		log.info("###END### : insert Times");
//		
//		// 네번째 값은 Map - Yonhap
//		pMap = pList.get(3);
//		
//		obj.put("newsName", pMap.get("newsName"));
//		obj.put("newsTitle", pMap.get("newsTitle"));	
//		obj.put("Lemma",pMap.get("newsLemmas"));
//		obj.put("Token", pMap.get("newsTokens"));
//		obj.put("OriginSentence",pMap.get("newsContents"));
//		obj.put("newsUrl", pMap.get("newsUrl"));
//		obj.put("InsertDate", pMap.get("insertDate"));
//		
//		mongoTestMapper.insert(obj);
//		
//		obj = null;
//		obj = new HashMap<>();
//		
//		log.info("###END### : insert Yonhap");
		
		return null;
	}
	@RequestMapping(value = "mongoInsertNews", produces = "application/json; charset=UTF8")
	@ResponseBody
	public List<Map<String, Object>> insertNews(HttpServletRequest request, Model model, HttpSession session)
			throws Exception {
		Map<String, Object> obj = new HashMap<>();
		obj.put("title", "trump dies");
		obj.put("newsUrl", "www.trumpdies.com");
		// 임의로 값을 만들어 Key에 대입
		
		List<String> origSent = new ArrayList<>();
		origSent.add("trump dies");
		origSent.add("oh my god!!");
		// 임의 값을 만들어 List origSent에 값을 대입
		
		obj.put("originalSentences", origSent);
		// originalSentence key에 List형인 origSent를 put
		
		List<String[]> lemmas = new ArrayList<>();
		
		origSent.forEach(sentence ->{
			lemmas.add(sentence.split(" "));
		});
		
		obj.put("lemmas", lemmas);
		
		List<String> translation = new ArrayList<>();
		origSent.add("트럼프 뒤짐");
		origSent.add("헐~~");
		
		obj.put("translation", translation);
		
		mongoTestMapper.insert(obj);
		
	
		
		return null;
	}

	// 템플릿
	@RequestMapping(value = "template")
	public String template() {
		return "/template";
	}

	// ajax test
	@RequestMapping(value = "/hello/text")
	public String Hello() {

		return "/hello";
	}

	@ResponseBody
	@RequestMapping(value = "/hello/hello")
	public boolean Hello(HttpServletRequest request) {
		String value = request.getParameter("id");

		if (value.equals("헬로")) {
			return true;
		}

		return false;
	}
}
