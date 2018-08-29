package com.localhost.model;

public class UserDetailForToken {
	private String email;
	private String role;
	private String timeline;

	public UserDetailForToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDetailForToken(String email, String role, String timeline) {
		super();
		this.email = email;
		this.role = role;
		this.timeline = timeline;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTimeline() {
		return timeline;
	}

	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}

}
