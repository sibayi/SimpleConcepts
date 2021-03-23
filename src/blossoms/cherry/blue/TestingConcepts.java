package blossoms.cherry.blue;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestingConcepts {

	public static void main(String[] args) {
		
		Date today = Calendar.getInstance().getTime();
		
		DateFormat monthInt = new SimpleDateFormat("MM");
		DateFormat yearInt = new SimpleDateFormat("yyyy");
		
		int currentMonthInt = Integer.parseInt(monthInt.format(today));
		int currentYearInt = Integer.parseInt(yearInt.format(today));
		
		String currentMonthStr = getMonthForInt(currentMonthInt - 1) + " " + yearInt.format(today);
		String nextMonthStr = getNextMonthString(currentMonthInt, currentYearInt);
		
		System.out.println("this month: " + currentMonthStr);
		System.out.println("next month: " + nextMonthStr);

	}
	
	public static String getMonthForInt(int mon) {
		String month = "invalid";
		DateFormatSymbols dfs = new DateFormatSymbols();
		
		String[] months = dfs.getMonths();
		
		if(mon >= 0 && mon < 12) {
			month = months[mon];
		}
		
		return month;
	}
	
	public static String getNextMonthString(int month, int year) {
		if(month == 12) {
			return getMonthForInt(0) + " " + ++year;
		}
		
		return getMonthForInt(month) + " " + year;
	}

}
