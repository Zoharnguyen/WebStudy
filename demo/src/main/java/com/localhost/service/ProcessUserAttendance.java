package com.localhost.service;

import com.localhost.model.UserAttendance;
import com.localhost.repository.DatabaseUtils;

public class ProcessUserAttendance {
	public static boolean checkDuplicateIdEmailContent(String id,String email) {
		for(UserAttendance user : DatabaseUtils.readUserAttendance()) {
			if(user.getIdContent().equals(id) && user.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	public static boolean checkDuplicateIdContent(String id) {
		for(UserAttendance user : DatabaseUtils.readUserAttendance()) {
			if(user.getIdContent().equals(id)) {
				return true;
			}
		}
		return false;
	}
}
