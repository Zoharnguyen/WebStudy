package com.localhost.model;

public class Users {
	private String id;
	private String email;
	private String userName;
	private String familyName;
	private String givenName;
	private String avatar;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(String id, String email, String userName, String familyName, String givenName, String avatar) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.familyName = familyName;
		this.givenName = givenName;
		this.avatar = avatar;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
