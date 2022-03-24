package blossoms.cherry.blue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Random;

public class ReadingWriting {

	public static void main(String[] args) {
		
		System.out.println("Month year: " + futureMonth(Integer.parseInt(args[0]) + 1));
		System.out.println("Date: " + futureDate(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
		
		if(args[2].equalsIgnoreCase("write")) {
			System.out.println("Executing writeTest()");
			writeTest();
		} else {
			System.out.println("Executing readTest()");
			readTest();
		}
		
		String nightwing = "grayson,richard";
		
		Dictionary<String, String> dic =  new Hashtable<>();
		dic.put("lastname", nightwing.split(",")[0]);
		dic.put("firstname", nightwing.split(",")[1]);
		
		System.out.println("Nightwing's lastname: " + dic.get("lastname"));
		
		System.out.println("Random text: " + randomText(6));

	}
	
	// Based on ASCII chart
	public static String randomText(int num) {
		final int ascii_0 = 48;
		final int ascii_9 = 57;
		final int ascii_A = 65;
		final int ascii_Z = 90;
		
		Random random = new Random();
		return random.ints(ascii_0, ascii_Z + 1).filter(i -> (i <= ascii_9 || i >= ascii_A)).limit(num).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
	
	public static void writeTest() {
		try {
			FileWriter writer = new FileWriter("src/resources/tc322-tc324.txt", true);
			writer.write(randomText(4) + "\n");
			writer.close();
		} catch(IOException err) {
			System.out.println("Error occurred: " + err);
		}
	}
	
	public static void readTest() {
		try {
			FileReader reader = new FileReader("src/resources/tc322-tc324.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			
			while((line = bufferedReader.readLine()) != null)
				System.out.println("Contents: " + line);
			
			reader.close();
			
			File file = new File("src/resources/tc322-tc324.txt");
			if(file.delete())
				System.out.println("File was successfully deleted");
			else
				System.out.println("Error in deleting file");
		} catch(IOException err) {
			System.out.println("Error occurred: " + err);
		}
	}
	
	public static String futureMonth(int additionalMonths) {
		SimpleDateFormat month = new SimpleDateFormat("MMMM yyyy");
		Calendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
		
		calendar.add(Calendar.DATE, additionalMonths * 28);
		
		return month.format(calendar.getTime());
	}
	
	public static String futureDate(int additionalMonths, int weekday) {	// make weekday an enum {SUNDAY = 1, MONDAY, ..., SATURDAY, NEXT_SUNDAY, NEXT_MONDAY, ..., NEXT_SATURDAY}
		SimpleDateFormat dayOfMonth = new SimpleDateFormat("d");
		Calendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
		
		int difference = + weekday + additionalMonths * 28 - calendar.get(Calendar.DAY_OF_WEEK);
		
		calendar.add(Calendar.DATE, difference);
		
		return dayOfMonth.format(calendar.getTime());
	}

}
