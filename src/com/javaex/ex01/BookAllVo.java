package com.javaex.ex01;

public class BookAllVo {
	
	
	private int bookId;
	private String title;
	private String pubDate;
	private int authorId;
	private String authorName;
	private String authorDesc;
	
	
	public BookAllVo() {}
	public BookAllVo(int bookId, String title, String pubDate, int authorId, String authorName, String authorDesc) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubDate = pubDate;
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorDesc() {
		return authorDesc;
	}
	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	
	
	
	
	@Override
	public String toString() {
		return "BookAllVo [bookId=" + bookId + ", title=" + title + ", pubDate=" + pubDate + ", authorId=" + authorId
				+ ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
	};
	
	
	
	
	
	
	

}
