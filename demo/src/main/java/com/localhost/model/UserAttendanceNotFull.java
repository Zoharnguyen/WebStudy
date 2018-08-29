package com.localhost.model;

public class UserAttendanceNotFull {
//	(String userName, String email, String avatar, String status, String note
	private String email;
	private String userName;
	private String avatar;
	private String idContent;
	private String status;
	private String note;

	public UserAttendanceNotFull() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAttendanceNotFull(String email, String idContent, String status, String note, String avatar, String userName) {
		super();
		this.email = email;
		this.idContent = idContent;
		this.status = status;
		this.note = note;
		this.avatar = avatar;
		this.userName = userName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdContent() {
		return idContent;
	}

	public void setIdContent(String idContent) {
		this.idContent = idContent;
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
