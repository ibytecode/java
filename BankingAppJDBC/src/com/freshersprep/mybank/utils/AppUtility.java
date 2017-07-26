package com.freshersprep.mybank.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtility {

	/**
	 * Converts Date to MySQL Date format. Returns Date in String format.
	 * @param date
	 * @return String
	 */
	public static String getDateStr(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(date);
		return dateStr;
	}
	
	/**
	 * Formats the Date to be displayed. Returns Date in String format.
	 * @param date
	 * @return String
	 */
	public static String getDisplayDateStr(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String dateStr = dateFormat.format(date);
		return dateStr;
	}
}
