package com.localhost.service;

import java.util.List;

import com.localhost.model.IdEmailUser;
import com.localhost.repository.DatabaseUtils;

public class WorkContentOfUser {
	public static boolean checkContentOfUser(String id, String email) {
		List<IdEmailUser> list = DatabaseUtils.readContentOfUser();
		for(IdEmailUser cou : list) {
			if(cou.getId().equals(id) && cou.getEmail().equals(email)) return true;
		}
		return false;
	}
}
