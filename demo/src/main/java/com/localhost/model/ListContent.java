package com.localhost.model;

public class ListContent {
	private String id;
	private String userName;
	private String avatar;
	private String level;
	private String startDate;
	private String endDate;
	private String status;
	private String contentName;
	private String contentDescription;
	private String tags;

	public ListContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListContent(String id, String userName, String avatar, String level, String startDate, String endDate,
			String status, String contentName, String contentDescription, String tags) {
		super();
		this.id = id;
		this.userName = userName;
		this.avatar = avatar;
		this.level = level;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.contentName = contentName;
		this.contentDescription = contentDescription;
		this.tags = tags;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
