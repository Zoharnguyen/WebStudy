package com.localhost.model;

public class ContentOfToday {
	private String idContent;
	private String title;

	public ContentOfToday() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContentOfToday(String idContent, String title) {
		super();
		this.idContent = idContent;
		this.title = title;
	}

	public String getIdContent() {
		return idContent;
	}

	public void setIdContent(String idContent) {
		this.idContent = idContent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
