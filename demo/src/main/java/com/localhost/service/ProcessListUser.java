package com.localhost.service;

import com.localhost.model.Users;
import com.localhost.repository.DatabaseUtils;

public class ProcessListUser {
	public static Users findUserByEmail(String email) {
		for (Users user : DatabaseUtils.readUsers()) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}
}
