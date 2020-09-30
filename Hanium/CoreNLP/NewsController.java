package poly.controller;

import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.NewsDTO;
import poly.service.INewsService;
import poly.util.NLPUtil;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;


@Controller
public class NewsController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "NewsService") 
	private INewsService newsService;
	
	@RequestMapping(value="/Today/clawlingNews")
	public String getNewsInfoFromWEB(HttpServletRequest request, HttpServletResponse response, ModelMap model) 
	throws Exception {
		
		log.info(this.getClass().getName() + ".getNewsInfoFromWEB Start!");
		
		int res = newsService.getNewsInfoFromWEB();
		
		model.addAttribute("res", String.valueOf(res));
		
		log.info(this.getClass().getName() + ".getNewsInfoFromWEB End!");
		
		return "/Today/clawlingNews";
		
	}
	
	@RequestMapping(value="/Today/viewNews")
	public String getMovieInfoFromWEB(HttpServletRequest request, HttpServletResponse response, ModelMap model) 
	throws Exception {
		
		log.info(this.getClass().getName() + ".getNewsInfoFromDB Start!");
		
		String news_no = "1";
		
		NewsDTO nDTO = new NewsDTO();
		
		nDTO.setNews_no(news_no);
		
		nDTO = newsService.getNewsInfoFromDB(nDTO);
		
		Iterator<CoreSentence> it = NLPUtil.sentence(nDTO.getNews_content());
		
		while(it.hasNext()) {
			
			CoreSentence sent = it.next();
			
			log.info(sent.text());
			log.info(sent.tokens().get(0).originalText());
			log.info(sent.tokens().get(0).index());
			log.info(sent.lemmas());
			
		}
		
		log.info(this.getClass().getName() + ".getNewsInfoFromDB End!");
		
		return "/Today/viewNews";
		
	}
	
	
	
	
	
}