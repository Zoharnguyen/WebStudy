package com.localhost.model;

public class Position {
	private String email;
	private String role;

	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Position(String email, String role) {
		super();
		this.email = email;
		this.role = role;
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
	
	public static void main(String[] args) {
		String str1 = "123456789";
		String str2 = null;
		str2 = str1.substring(0, 3);
		System.out.println("str2= "+str2);
		String str3 = str1.substring(4, 8);
		System.out.println("str3= "+str3);
		System.out.println("str1= "+str1);
	}
}
