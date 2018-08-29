package com.localhost.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class DateComparator implements Comparator<String> {
	public int compare(String date1, String date2) {
			if (ChangeStringToDate.changeStringToDate(date1)
					.compareTo(ChangeStringToDate.changeStringToDate(date2)) == 0)
				return 0;
			else if (ChangeStringToDate.changeStringToDate(date1)
					.compareTo(ChangeStringToDate.changeStringToDate(date2)) < 0)
				return 1;
			else
				return -1;
	}
}

public class FuntionToSortDate {

	public static List<String> sort(List<String> listS) {
		Collections.sort(listS, new DateComparator());
		return listS;
	}
}