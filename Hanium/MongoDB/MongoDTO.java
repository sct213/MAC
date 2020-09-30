package poly.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreSentence;

public class MongoNewsDTO {

	private Date insertDate;
	private String news_url;
	private String news_name;
	private String news_title;
	private List<String> original_sentences;
	private List<List<String>> tokens;
	private List<List<String>> lemmas;
	private List<String> translation;
	
	public MongoNewsDTO() {
		this.insertDate = new Date();
		this.original_sentences = new ArrayList<>();
		this.lemmas = new ArrayList<>();
		this.tokens = new ArrayList<>();
		this.translation = new ArrayList<>();
	}
	
	public MongoNewsDTO(Iterator<CoreSentence> it) {
		this();
		while(it.hasNext()) {
			
			CoreSentence sent = it.next();
			this.original_sentences.add(sent.text());   //문장
			
			sent.tokens().get(0).originalText();    //문장의 첫번째 단어
			
			sent.tokens().get(0).index();          //문장의 첫번째 단어의 번호
			
			List<String> token = new ArrayList<String>();
			List<String> lemma = new ArrayList<String>();
			
			for (Iterator<CoreLabel> tokenIt = sent.tokens().iterator(); tokenIt.hasNext();) {
				CoreLabel tempToken = tokenIt.next();
				token.add(tempToken.originalText());   //문장의 쪼개진 단어별로 입력
				lemma.add(tempToken.lemma());         //문장의 쪼개진 단어의 원형을 입력
			}
			this.tokens.add(token);            //tokens라는 리스트에 리스트를 입력(이중 리스트)
			this.lemmas.add(lemma);            //lemmas라는 리스트에 리스트를 입력(이중 리스트)
			
		}
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getNews_url() {
		return news_url;
	}

	public void setNews_url(String news_url) {
		this.news_url = news_url;
	}

	public String getNews_name() {
		return news_name;
	}

	public void setNews_name(String news_name) {
		this.news_name = news_name;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public List<String> getOriginal_sentences() {
		return original_sentences;
	}

	public void setOriginal_sentences(List<String> original_sentences) {
		this.original_sentences = original_sentences;
	}

	public List<List<String>> getTokens() {
		return tokens;
	}

	public void setTokens(List<List<String>> tokens) {
		this.tokens = tokens;
	}

	public List<List<String>> getLemmas() {
		return lemmas;
	}

	public void setLemmas(List<List<String>> lemmas) {
		this.lemmas = lemmas;
	}

	public List<String> getTranslation() {
		return translation;
	}

	public void setTranslation(List<String> translation) {
		this.translation = translation;
	}
	
	
}