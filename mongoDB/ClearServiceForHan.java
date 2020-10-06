package poly.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.stanford.nlp.pipeline.CoreSentence;
import poly.dto.MongoNewsDTO;
import poly.dto.NewsDTO;
import poly.persistance.mapper.INewsMapper;
import poly.persistance.mongo.IMongoTestMapper;
import poly.service.INewsService;
import poly.util.NLPUtil;
import poly.util.WebCrawler;

@Service("NewsService")
public class NewsService implements INewsService {

	@Resource(name = "NewsMapper")
	private INewsMapper newsMapper;

	@Resource(name = "MongoTestMapper")
	private IMongoTestMapper mongTestMapper;

	// 로그파일 출력개체
	private Logger log = Logger.getLogger(this.getClass());
	
	// ###############################################
	// 모든 영어 뉴스 크롤링 crawlingAll  !!
	// ###############################################
	@Override
	public List<MongoNewsDTO> crawlingAll() throws Exception {

		log.info("###START### : " + this.getClass().getName() + "crawlingAll start");
		
		List<MongoNewsDTO> rList = new ArrayList<>();

		log.info("###START### : CrawlHerald");
		// KoreaHerald Crawl
		String[] crawlRes = WebCrawler.crawlHerald();
		String title = crawlRes[0];
		String inputText = crawlRes[1];
		String newsUrl = crawlRes[2];
		String newsName = "herald";
	
		Iterator<CoreSentence> Contents = NLPUtil.sentence(inputText);

		MongoNewsDTO mDTO = new MongoNewsDTO(Contents);
		
		for(int i=0; i<4; i++){
			
			if(i == 1) {
				crawlRes = WebCrawler.crawlreuters();
				newsName = "reuters";
			}else if(i == 2) {
				crawlRes = WebCrawler.crawltimes();
				newsName = "times";
			}else if(i == 3) {
				crawlRes = WebCrawler.crawlyonhap();
				newsName = "yonhap";
			}
			
			title = crawlRes[0];
			inputText = crawlRes[1];
			newsUrl = crawlRes[2];
			
			Contents = NLPUtil.sentence(inputText);
			mDTO = new MongoNewsDTO(Contents);
			
			log.info("title: " + title);
			log.info("input : " + inputText);
			log.info("newsUrl: " + newsUrl);
			
			mDTO.setNews_title(title);
			mDTO.setNews_url(newsUrl);
			mDTO.setNews_name(newsName);
			
			log.info(mDTO.getNews_name());
			log.info(mDTO.getNews_title());
			log.info("###END### : Crawl");

			rList.add(mDTO);
			mDTO = null;
		}		
		log.info("###END### : " + this.getClass().getName() + "crawlingAll end");
		
		return rList;
	}

	
	// 웹크롤링한 뉴스 MYSQL DB에 저장
	@Override
	public int MySQLsaveNews(String title, String inputText, String newsUrl, String newsname) throws Exception {

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

	// MySQl DB에서 데이터 가져오기
	@Override
	public List<NewsDTO> getNewsInfoFromDB() {
		return newsMapper.getNewsInfoFromDB();
	}

	@Override
	// @Scheduled(cron = "0 0 7 ? * *")
	// mySQL DB에 저장    TESTscheduleCrawl
	public int testscheduleCrawl() throws Exception {
		int res = 0;

		log.info(this.getClass().getName() + "crawlHerald() start");
		String[] crawlRes = WebCrawler.crawlHerald();
		String title = crawlRes[0];
		String inputText = crawlRes[1];
		String newsUrl = crawlRes[2];
		String newsname = "herald";

		log.info("title : " + title);
		log.info("inputText : " + inputText);
		log.info("newsUrl : " + newsUrl);
		res = MySQLsaveNews(title, inputText, newsUrl, newsname);
		log.info(this.getClass().getName() + "crawlHerald() end");

		log.info(this.getClass().getName() + "crawlreuters() start");
		String[] crawlRes1 = WebCrawler.crawlreuters();
		String title1 = crawlRes1[0];
		String inputText1 = crawlRes1[1];
		String newsUrl1 = crawlRes1[2];
		String newsname1 = "reuters";
		res += MySQLsaveNews(title1, inputText1, newsUrl1, newsname1);
		log.info(this.getClass().getName() + "crawlbbc() end");

		log.info(this.getClass().getName() + "crawltimes() start");
		String[] crawlRes2 = WebCrawler.crawltimes();
		String title2 = crawlRes2[0];
		String inputText2 = crawlRes2[1];
		String newsUrl2 = crawlRes2[2];
		String newsname2 = "times";
		res += MySQLsaveNews(title2, inputText2, newsUrl2, newsname2);
		log.info(this.getClass().getName() + "crawltimes() end");

		log.info(this.getClass().getName() + "crawlyonhap() start");
		String[] crawlRes3 = WebCrawler.crawlyonhap();
		String title3 = crawlRes3[0];
		String inputText3 = crawlRes3[1];
		String newsUrl3 = crawlRes3[2];
		String newsname3 = "yonhap";
		res += MySQLsaveNews(title3, inputText3, newsUrl3, newsname3);
		log.info(this.getClass().getName() + "crawlyonhap()) end");

		// 뉴스 결과 넣어주기
		// model.addAttribute("res", String.valueOf(res));

		log.info(this.getClass().getName() + ".getNewsInfoFromWEB End!");
		return res;
	}

}
