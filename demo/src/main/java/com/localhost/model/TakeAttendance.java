package com.localhost.model;

public class TakeAttendance {
	private String userName;
	private String email;
	private String avatar;
	private String status;
	private String note;

	public TakeAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TakeAttendance(String userName, String email, String avatar, String status, String note) {
		super();
		this.userName = userName;
		this.email = email;
		this.avatar = avatar;
		this.status = status;
		this.note = note;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
