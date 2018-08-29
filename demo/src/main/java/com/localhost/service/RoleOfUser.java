package com.localhost.service;

import java.util.List;

import com.localhost.model.Position;
import com.localhost.model.Users;
import com.localhost.repository.DatabaseUtils;

public class RoleOfUser {
	public static String creatRoleForUser(Users user) {
		List<Position> listPosition =  DatabaseUtils.readPosition();
		for(Position position : listPosition) {
			if(user.getEmail().equals(position.getEmail()) == true) return position.getRole();
		}
		return "member";
	}
}
