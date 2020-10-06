package poly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import edu.stanford.nlp.pipeline.CoreSentence;
import poly.dto.MongoNewsDTO;
import poly.dto.NewsDTO;
import poly.persistance.mapper.INewsMapper;
import poly.service.INewsService;
import poly.util.NLPUtil;
import poly.util.WebCrawler;

@Service("NewsService")
public class NewsService implements INewsService {

	@Resource(name = "NewsMapper")
	private INewsMapper newsMapper;

	// 로그파일 출력개체
	private Logger log = Logger.getLogger(this.getClass());

	// 웹크롤링한 뉴스 DB에 저장
	@Override
	public int SaveNews(String title, String inputText, String newsUrl, String newsname) throws Exception {

		log.info(this.getClass().getName() + "saveNews start");

		NewsDTO pDTO = new NewsDTO();
		
		pDTO.setNews_title(title);
		pDTO.setNews_contents(inputText);
		pDTO.setNews_url(newsUrl);
		pDTO.setNews_name(newsname);
		
		log.info("nDTO : " + pDTO.getNews_title());
		log.info("nDTO : " + pDTO.getNews_contents());
		log.info("nDTO : " + pDTO.getNews_url());
		log.info("nDTO : " + pDTO.getNews_name());

		int saved = newsMapper.InsertNewsInfo(pDTO);
		pDTO = null;
		
		return saved;
	}

	@Override
	@Scheduled(cron="0 0 7 ? * *")
	public void scheduleCrawl() throws Exception{
		int res = 0;
		
		log.info(this.getClass().getName() + "crawlHerald() start");
		String[] crawlRes = WebCrawler.crawlHerald();
		String title = crawlRes[0];
		String inputText = crawlRes[1];
		String newsUrl = crawlRes[2];
		String newsname = "herald";
		
		log.info("title : "+title);
		log.info("inputText : "+inputText);
		log.info("newsUrl : "+newsUrl);
		res = SaveNews(title, inputText, newsUrl, newsname);
		log.info(this.getClass().getName() + "crawlHerald() end");
		
		
		log.info(this.getClass().getName() + "crawluk() start");
		String[] crawlRes1 = WebCrawler.crawluk();
		String title1 = crawlRes1[0];
		String inputText1 = crawlRes1[1];
		String newsUrl1 = crawlRes1[2];
		String newsname1 = "uk";
		res += SaveNews(title1, inputText1, newsUrl1, newsname1);
		log.info(this.getClass().getName() + "crawluk() end");
		
		log.info(this.getClass().getName() + "crawltimes() start");
		String[] crawlRes2 = WebCrawler.crawltimes();
		String title2 = crawlRes2[0];
		String inputText2 = crawlRes2[1];
		String newsUrl2 = crawlRes2[2];
		String newsname2 = "times";
		res += SaveNews(title2, inputText2, newsUrl2, newsname2);
		log.info(this.getClass().getName() + "crawltimes() end");
		
		log.info(this.getClass().getName() + "crawlyonhap() start");
		String[] crawlRes3 = WebCrawler.crawlyonhap();
		String title3 = crawlRes3[0];
		String inputText3 = crawlRes3[1];
		String newsUrl3 = crawlRes3[2];
		String newsname3 = "yonhap";
		res += SaveNews(title3, inputText3, newsUrl3, newsname3);
		log.info(this.getClass().getName() + "crawlyonhap()) end");
		
		// 뉴스 결과 넣어주기
		// model.addAttribute("res", String.valueOf(res));

		log.info(this.getClass().getName() + ".getNewsInfoFromWEB End!");
		
	}
	
	public List<Map<String, Object>> crawlingAll() throws Exception{
		
		log.info("###START### : " + this.getClass().getName() + "crawlingAll start");
		
		Date date = new Date();
		
		List<Map<String, Object>> rList = new ArrayList<>();
		
		log.info("###START### : CrawlHerald");
		// KoreaHerald Crawl
		Map<String, Object> CrawlMap = new HashMap<>();
	
		String[] crawlRes = WebCrawler.crawlHerald();
		String title = crawlRes[0];
		String inputText = crawlRes[1];
		String newsUrl = crawlRes[2];
		String newsName = "Herald";
		
		Iterator<CoreSentence> Contents = NLPUtil.sentence(inputText);
		
		MongoNewsDTO DTO = new MongoNewsDTO(Contents);
		
		List<List<String>> TokenList = DTO.getTokens();
		List<List<String>> LemmaList = DTO.getLemmas();
		List<String> OriginalSentence = DTO.getOriginal_sentences();
		
		CrawlMap.put("newsLemmas", LemmaList);
		
		CrawlMap.put("newsTokens", TokenList);
		
		CrawlMap.put("newsContents", OriginalSentence);
		
		CrawlMap.put("newsTitle", title);
		
		CrawlMap.put("newsUrl", newsUrl);
		
		CrawlMap.put("insertDate", date);
		
		log.info("###END### : CrawlHerald");
		
		rList.add(CrawlMap);
		
		/////////////////////////////////////////////////
		
		log.info("###START### : CrawlUK");
		
		CrawlMap = null;
		CrawlMap = new HashMap<>();
		
		String[] crawlRes1 = WebCrawler.crawluk();
		String title1 = crawlRes1[0];
		String inputText1 = crawlRes1[1];
		String newsUrl1 = crawlRes1[2];
		
		Iterator<CoreSentence> uContents = NLPUtil.sentence(inputText1);
		
		MongoNewsDTO uDTO = new MongoNewsDTO(uContents);
		
		List<List<String>> uTokenList = uDTO.getTokens();
		List<List<String>> uLemmaList = uDTO.getLemmas();
		List<String> uOriginalSentence = uDTO.getOriginal_sentences();
		
		CrawlMap.put("newsLemmas", uLemmaList);
		
		CrawlMap.put("newsTokens", uTokenList);
		
		CrawlMap.put("newsContents", uOriginalSentence);
		
		CrawlMap.put("newsName", "UK");
		
		CrawlMap.put("newsTitle", title1);
		
		CrawlMap.put("newsUrl", newsUrl1);
		
		CrawlMap.put("insertDate", date);
	
		log.info("###END### : CrawlUK");
		
		rList.add(CrawlMap);
		
		///////////////////////////////////////////////////
		
		log.info("###START### : CrawlTimes");
		
		CrawlMap = null;
		CrawlMap = new HashMap<>();
		
		String[] crawlRes2 = WebCrawler.crawltimes();
		String title2 = crawlRes2[0];
		String inputText2 = crawlRes2[1];
		String newsUrl2 = crawlRes2[2];
		
		Iterator<CoreSentence> tContents = NLPUtil.sentence(inputText2);
		
		MongoNewsDTO tDTO = new MongoNewsDTO(tContents);
		
		List<List<String>> tTokenList = tDTO.getTokens();
		List<List<String>> tLemmaList = tDTO.getLemmas();
		List<String> tOriginalSentence = tDTO.getOriginal_sentences();
		
		CrawlMap.put("newsLemmas", tLemmaList);
		
		CrawlMap.put("newsTokens", tTokenList);
		
		CrawlMap.put("newsContents", tOriginalSentence);
		
		CrawlMap.put("newsName", "NewyorkTimes");
		
		CrawlMap.put("newsTitle", title2);
		
		CrawlMap.put("newsUrl", newsUrl2);
		
		CrawlMap.put("insertDate", date);
	
		log.info("###END### : Crawl Times");
		
		rList.add(CrawlMap);
			
		///////////////////////////////////////////////////
		
		CrawlMap = null;
		CrawlMap = new HashMap<>();
		
		String[] crawlRes3 = WebCrawler.crawlyonhap();
		String title3 = crawlRes3[0];
		String inputText3 = crawlRes3[1];
		String newsUrl3 = crawlRes3[2];
		
		Iterator<CoreSentence> yContents = NLPUtil.sentence(inputText3);
		
		MongoNewsDTO yDTO = new MongoNewsDTO(yContents);
		
		List<List<String>> yTokenList = yDTO.getTokens();
		List<List<String>> yLemmaList = yDTO.getLemmas();
		List<String> yOriginalSentence = yDTO.getOriginal_sentences();
		
		CrawlMap.put("newsLemmas", yLemmaList);
		
		CrawlMap.put("newsTokens", yTokenList);
		
		CrawlMap.put("newsContents", yOriginalSentence);
		
		CrawlMap.put("newsName", "YonhapNews");
		
		CrawlMap.put("newsTitle", title3);
		
		CrawlMap.put("newsUrl", newsUrl3);
		
		CrawlMap.put("insertDate", date);
	
		log.info("###END### : Crawl Yonhap");
		
		rList.add(CrawlMap);
		
		return rList;
	}
	
	// DB에서 데이터 가져오기
	@Override
	public NewsDTO getNewsInfoFromDB(NewsDTO nDTO) {

		return newsMapper.getNewsInfoFromDB(nDTO);
	}


	@Override
	public Map<String, Object> mongoCrawlNews() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
