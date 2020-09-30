package poly.dto;

/**
 * @author 이협건
 * @version 1.1 공지사항 DTO
 */
public class NewsDTO {

	private String news_title;
	private String news_content;
	private String news_date;
	private String news_editor;
	private String news_checkTime;
	private String news_seq;
	private String news_no;

	public String getNews_no() {
		return news_no;
	}

	public void setNews_no(String news_no) {
		this.news_no = news_no;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public String getNews_date() {
		return news_date;
	}

	public void setNews_date(String news_date) {
		this.news_date = news_date;
	}

	public String getNews_editor() {
		return news_editor;
	}

	public void setNews_editor(String news_editor) {
		this.news_editor = news_editor;
	}

	public String getNews_checkTime() {
		return news_checkTime;
	}

	public void setNews_checkTime(String news_checkTime) {
		this.news_checkTime = news_checkTime;
	}

	public String getNews_seq() {
		return news_seq;
	}

	public void setNews_seq(String news_seq) {
		this.news_seq = news_seq;
	}

}
