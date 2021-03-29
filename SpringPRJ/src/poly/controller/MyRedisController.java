package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.service.IMyRedisService;


@Controller
public class MyRedisController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	// 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴))
	@Resource(name="MyRedisService")
	private IMyRedisService myRedisService;
	
	// 빅데이터 처리를 위함 
	@RequestMapping(value = "myRedis/test")
	@ResponseBody
	public String myRedis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".myRedis Start!");
		
		myRedisService.doSaveData();
		
		log.info(this.getClass().getName() + ".myRedis End!");
		
		return "Success";
	}
}
