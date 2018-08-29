package com.localhost.service;

import java.util.Date;
import java.util.List;

import com.localhost.model.IdEmailUser;
import com.localhost.model.ListContent;
import com.localhost.model.ListMember;
import com.localhost.model.UserDetailForToken;
import com.localhost.model.Users;
import com.localhost.repository.DatabaseUtils;

public class Validate {
	public static boolean checkUserLogin(Users user) {
		for (Users user1 : DatabaseUtils.readUsers()) {
			if (user.getEmail().equals(user1.getEmail()) == true) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkInsertContent(ListContent content) {
		for (ListContent content1 : DatabaseUtils.readListContent()) {
			if (content.getId().equals(content1.getId()) == true
					&& content.getUserName().equals(content1.getUserName()))
				return false;
		}
		return true;
	}

	public static boolean checkInsertMember(ListMember member) {
		for (ListMember member1 : DatabaseUtils.readListMember()) {
			if (member1.getEmail().equals(member.getEmail()))
				return false;
		}
		return true;
	}

	public static <T> boolean checkDataInToDatabase(List<T> list) {
		if (list.size() > 0)
			return true;
		else
			return false;
	}

	public static String getLastName(String name) {
		name = name.trim();
		int i, j = 0;
		for (i = 0; i < name.length(); i++) {
			if (name.charAt(i) == ' ')
				j++;
		}
		return name.split(" ")[j];
	}

	public static boolean checkToken(String token) {
		UserDetailForToken userDetail = ParseToken.parseToken(token);
		Date now = new Date();
		long time = now.getTime();
		long timeline = Long.parseLong(userDetail.getTimeline());
		if (userDetail.getEmail() == null || userDetail.getRole() == null || userDetail.getTimeline() == null)
			return false;
		if (time - timeline > 18000000)
			return false;
		return true;
	}

	public static boolean checkDuplicateDate(String date, List<String> list) {
		for (String dateS : list) {
			if (dateS.equals(date))
				return true;
		}
		return false;
	}
	
	public static boolean checkDuplicateEmail(String email, List<String> list) {
		for(String emailL : list) {
			if(emailL.equals(email)) return false;
		}
		return true;
	}
	
	public static boolean checkId(String id) {
		for(IdEmailUser idE : DatabaseUtils.readIdEmailUser()) {
			if(id.equals(idE.getId())) {
				return true;
			}
		}
		return false;
	}
	
//	public static void main(String[] args) {
//		System.out.println(checkToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJudHExQGdtYWlsLmNvbSIsInJvbGUiOiJjYXB0YWluIiwidGltZWxpbmUiOiIxNTMzNzY1Mjc3OTA3In0._9vuzMJ7mGyijEeVjXF6t5xdH4SM20i7HhVKVsxBGccFFmEuU-G22YwXez3qMY-3VuPDBMQbJlWoAqvfxXYqVQ"));
//	}

}
