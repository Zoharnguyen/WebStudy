package com.localhost.service;

import java.util.List;

import com.localhost.model.ListContent;
import com.localhost.model.Users;
import com.localhost.repository.DatabaseUtils;

public class AutoId {

	public static int autoIdUser() {
		List<Users> listUser = DatabaseUtils.readUsers();
		int max;
		if (Validate.checkDataInToDatabase(listUser)) {
			max = Integer.valueOf(listUser.get(0).getId());
			for (Users user : listUser) {
				if (max < Integer.valueOf(user.getId()))
					max = Integer.valueOf(user.getId());
			}
		} else {
			max = 1;
		}
		return max+1;
	}

	public static int autoIdContent() {

		List<ListContent> listContent = DatabaseUtils.readListContent();
		int max;
		if (Validate.checkDataInToDatabase(listContent)) {
			max = Integer.valueOf(listContent.get(0).getId());
			for (ListContent content : listContent) {
				if (max < Integer.valueOf(content.getId()))
					max = Integer.valueOf(content.getId());
			}
		} else {
			max = 1;
		}
		return max+1;
	}

}
