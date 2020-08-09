package com.dg.drimansy.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	private static final String F_DATE_TIME_WEB = "yyyy-MM-dd'T'HH:mm";
	private static final String F_SHORT_WEB = "yyyy-MM-dd";
	
	public static LocalDate	 getShortDate(String date){
		return LocalDate.parse(date);
	}
	
	public static Date getNow() {
		return new Date();
	}
	
	public static String getShortFWebOrNow(Date date) {
		return getShortFWeb(date == null ? getNow() : date);
	}
	
	public static String getShortFWeb(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(F_SHORT_WEB);  
        return dateFormat.format(date);  
	}
	
	public static String getFWebOrNow(Date date) {
		return getFWeb(date == null ? getNow() : date);  
	}
	
	public static String getFWeb(Date date) {
		LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		String dateFormat = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		int indexOf = dateFormat.lastIndexOf(":");
		return dateFormat.substring(0, indexOf);
	}
	
	public static Date getDateFromWeb(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(F_SHORT_WEB);
		return formatter.parse(dateStr);
	}
	
	public static Date getDateTimeFromWeb(String dateStr) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(F_DATE_TIME_WEB);
		LocalDateTime parse = LocalDateTime.parse(dateStr, formatter);
		return java.util.Date.from(parse.atZone(ZoneId.systemDefault())
			      .toInstant());
	}
}
