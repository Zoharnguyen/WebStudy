package com.localhost.service;

import java.util.List;

import com.localhost.model.ListMember;
import com.localhost.repository.DatabaseUtils;

public class ProcessListMember {
	public static ListMember findMemberByEmail(String email) {
		List<ListMember> list = DatabaseUtils.readListMember();
		for (ListMember member : list) {
			if (member.getEmail().equals(email)) {
				return member;
			}
		}
		return null;
	}
	public static ListMember findMemberPendingItemByEmail(String email) {
		List<ListMember> list = DatabaseUtils.readListMemberPendingItem();
		for (ListMember member : list) {
			if (member.getEmail().equals(email)) {
				return member;
			}
		}
		return null;
	}
}
