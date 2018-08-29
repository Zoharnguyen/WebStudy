package com.localhost.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.localhost.model.ListMember;

class RoleComparator implements Comparator<ListMember> {
	public int compare(ListMember member1, ListMember member2) {
		return member1.getRole().compareTo(member2.getRole());
	}
}

class UserNameComparator implements Comparator<ListMember> {
	public int compare(ListMember member1, ListMember member2) {
		if (member1.getRole().equals(member2.getRole())) {
			return Validate.getLastName(member1.getUserName()).compareTo(Validate.getLastName(member2.getUserName()));
		} else
			return 0;
	}
}

public class FuntionToSortMemberClass {
	public static List<ListMember> sortMember(List<ListMember> listMember) {
		Collections.sort(listMember, new RoleComparator());
		Collections.sort(listMember, new UserNameComparator());
		return listMember;
	}
	
}
