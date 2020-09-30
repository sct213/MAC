package poly.service.impl;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import edu.stanford.nlp.pipeline.CoreSentence;
import poly.dto.NewsDTO;
import poly.persistance.mapper.INewsMapper;
import poly.service.INewsService;

@Service("NewsService")
public class NewsService implements INewsService {

	@Resource(name = "NewsMapper")
	private INewsMapper newsMapper;

	private Logger log = Logger.getLogger(this.getClass());

	public int getNewsInfoFromWEB() throws Exception {

		log.info(this.getClass().getName() + ".getNewsInfoFromWEB start!!");

		int res = 0; // 크롤링 결과 (0보다 크면 크롤링 성공)

		String url = "http://en.yna.co.kr";

		// JSOUP 라이브러리를 통해 사이트에 접속되면, 그 사이트 전체의 HTML 소스를 저장할 변수
		Document doc = null;

		// 사이트 접속
		doc = Jsoup.connect(url).get();
		//log.info(doc.toString());

		// 사이트에 접속하여 전체 기사 중 메인 기사를 찾아 들어가야 함.

		// 웹 페이지 전체 소스 중 일부 태그를 선택하기 위해 사용
		// 메인페이지의 url을 가져오기 위함
		Elements element_urlGet = doc.select("article.top-news-zone" + " div.box" + " a");
		//log.info("element_urlGet: "+ element_urlGet.toString());
		log.info("element_urlGet == null? : "+ (element_urlGet == null));
		
		// element_urlGet 소스에 href를 가져옴
		url = "http:" + element_urlGet.attr("href");
		log.info("url : " + url.toString());
		log.info("element_urlGet == null? :  "+ (url == null));

		// 메인 기사 주소로 재접속
		doc = Jsoup.connect(url).get();

		// 뉴스의 제목
		Elements element_titleget = doc.select("h1.tit");
		String element_title = element_titleget.text();
		log.info("news_title: " + element_title);

		// <div class="view_tit_byline_l"><a
		// href="/search/list_name.php?byline=Ock+Hyun-ju">By Ock Hyun-ju</a></div>
		// 뉴스의 기자
		String element_author = "없음";
		log.info("news_editor: " + element_author);

		// <div class="view_tit_byline_r">Published : Aug 26, 2020 - 15:44
		// &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Updated : Aug 26, 2020 - 20:36</div>
		// 뉴스의 날짜
		Elements element_dateget = doc.select("div.info-con" + " span.txt");
		String element_date = element_dateget.text();
		log.info("news_date: " + element_date);
		// split으로 앞의 불필요한 문자들을 삭제 후 삽입할 것
		// <div class="view_con_t"> 뉴스의 내용
		Elements element_contentsget = doc.select("div.article-story");
		String element_contents = element_contentsget.text();
		log.info("news_content: " + element_contents);
		
		NewsDTO nDTO = null;

		// 수집된 데이터 DB에 저장
		nDTO = new NewsDTO();
		
		log.info("nDTO.set 시작");
		nDTO.setNews_title(element_title.toString());
		nDTO.setNews_editor(element_author.toString());
		nDTO.setNews_date(element_date.toString());
		nDTO.setNews_content(element_contents.toString());
		log.info("nDTO.set 종료");
		
		res = newsMapper.InsertNewsInfo(nDTO);
		
		log.info(this.getClass().getName() + ".getNewsInfoFromWEB end!");

		return res;
	}

	@Override
	public NewsDTO getNewsInfoFromDB(NewsDTO nDTO) {
	
		return newsMapper.getNewsInfoFromDB(nDTO);
	}
}

