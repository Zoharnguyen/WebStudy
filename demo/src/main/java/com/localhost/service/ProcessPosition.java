package com.localhost.service;

import java.util.List;

import com.localhost.model.Position;
import com.localhost.repository.DatabaseUtils;

public class ProcessPosition {
	public static boolean checkIntoPosition(String email) {
		List<Position> listPosition = DatabaseUtils.readPosition();
		for (Position position : listPosition) {
			if (position.getEmail().equals(email))
				return true;
		}
		return false;
	}
	public static Position findPositionByEmail(String email) {
		for(Position position : DatabaseUtils.readPosition()) {
			if(email.equals(position.getEmail())){
				return position;
			}
		}
		return null;
	}
	
}
