package com.localhost.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeStringToDate {

	public static Date changeStringToDate(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	} 
	
//	public static void main(String[] args) throws ParseException {
//		 Date date = Calendar.getInstance().getTime();
//		 System.out.println("date= "+date);
//		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");  
//		String strDate = dateFormat.format(date); 
//		System.out.println("String= "+strDate);
//	}
}
