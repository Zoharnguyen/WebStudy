package com.localhost.model;

public class ListMember {
	private String userName;
	private String avatar;
	private String email;
	private String joinedDate;
	private String role;
	private String skype;
	public ListMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListMember(String userName, String avatar, String email,  String joinedDate, String role, String skype) {
		super();
		this.userName = userName;
		this.email = email;
		this.avatar = avatar;
		this.joinedDate = joinedDate;
		this.role = role;
		this.skype = skype;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}

}
