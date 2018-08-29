package com.localhost.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.localhost.model.ListContent;

class StatusComparator implements Comparator<ListContent> {
	public int compare(ListContent user1, ListContent user2) {
		return user2.getStatus().compareTo(user1.getStatus());
	}
}

class StartDateComparator implements Comparator<ListContent> {
	public int compare(ListContent user1, ListContent user2) {
		if (user1.getStatus().equals(user2.getStatus()) && user1.getEndDate().equals(user2.getEndDate())) {
			if (ChangeStringToDate.changeStringToDate(user1.getStartDate())
					.compareTo(ChangeStringToDate.changeStringToDate(user2.getStartDate())) == 0)
				return 0;
			else if (ChangeStringToDate.changeStringToDate(user1.getStartDate())
					.compareTo(ChangeStringToDate.changeStringToDate(user2.getStartDate())) < 0)
				return 1;
			else
				return -1;
		} else
			return 0;
	}
}

class EndDateComparator implements Comparator<ListContent> {
	public int compare(ListContent user1, ListContent user2) {
		if (user1.getStatus().equals(user2.getStatus())) {
			if (ChangeStringToDate.changeStringToDate(user1.getStartDate())
					.compareTo(ChangeStringToDate.changeStringToDate(user2.getEndDate())) == 0)
				return 0;
			else if (ChangeStringToDate.changeStringToDate(user1.getEndDate())
					.compareTo(ChangeStringToDate.changeStringToDate(user2.getEndDate())) < 0)
				return 1;
			else
				return -1;
		} else
			return 0;
	}
}

public class FuntionToSortListContent {

	public static List<ListContent> sort(List<ListContent> listContent) {
		Collections.sort(listContent, new StatusComparator());
		Collections.sort(listContent, new EndDateComparator());
		Collections.sort(listContent, new StartDateComparator());
		return listContent;
	}
}