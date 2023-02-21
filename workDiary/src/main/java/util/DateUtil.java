package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

	public static final String DATE_FORMAT = "yyyy/MM/dd";
	
	private static SimpleDateFormat sdFormatter = new SimpleDateFormat(DATE_FORMAT);
	private static DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
	
	
	public static String dateToString(Date date) {
		
		return sdFormatter.format(date);
	}
	public static Date stringToDate(String str) throws ParseException {
		
		return sdFormatter.parse(str);
	}
	
	public static java.sql.Date utilDateToSqlDate(Date date){
		
		long timeStamp = date.getTime();
		return new java.sql.Date(timeStamp);
	}
	public static Date sqlDateToUtilDate(java.sql.Date date) {
		
		return date;
	}
	
	public static LocalDate stringToLocalDate(String str) {
		
		return LocalDate.parse(str, dtFormatter);
	}
	
	public static String localDateToString(LocalDate lDate) {
		
		return lDate.format(dtFormatter);
	}
	
	public static Date localDateToDate(LocalDate lDate) {
		
		String str = localDateToString(lDate);
		try {
			
			return stringToDate(str);
		} catch (ParseException ex) {
			
			System.out.println(ex.getMessage());
			return new Date();
		}
	}
	
	public static LocalDate dateToLocalDate(Date date) {
		
		String str = dateToString(date);
		return stringToLocalDate(str);
	}
}
