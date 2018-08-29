package com.localhost.model;

public class UserAttendance {
	private String idContent;
	private String date;
	private String title;
	private String userName;
	private String avatar;
	private String status;
	private String note;
	private String email;

	public UserAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAttendance(String idContent, String date, String title, String userName, String avatar, String status,
			String note, String email) {
		super();
		this.idContent = idContent;
		this.date = date;
		this.title = title;
		this.userName = userName;
		this.avatar = avatar;
		this.status = status;
		this.note = note;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIdContent() {
		return idContent;
	}

	public void setIdContent(String idContent) {
		this.idContent = idContent;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
