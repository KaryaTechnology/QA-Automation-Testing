package Library;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WorkOut {

	public static void main(String[] args) {
		System.out.println(timeAddition("06/20/2019;23:30", 100, "d"));
	}

	public static String timeAddition(String TimeAndDate, int addTime, String units_d_h_m) {
		String returnTime = TimeAndDate;
		try {

			final long ONE_MINUTE_IN_MILLIS = 60000;

			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy;HH:mm");
			Date date = dateFormat.parse(TimeAndDate);
			Calendar Cal = Calendar.getInstance();

			Cal.setTime(date);
			
			if (units_d_h_m.trim().equalsIgnoreCase("d")) {
				Cal.add(Calendar.DAY_OF_YEAR, addTime);
				returnTime = dateFormat.format(Cal.getTime()).toString();
			} else if (units_d_h_m.trim().equalsIgnoreCase("h")) {
				Cal.add(Calendar.HOUR_OF_DAY, addTime);
				returnTime = dateFormat.format(Cal.getTime()).toString();
			} else if (units_d_h_m.trim().equalsIgnoreCase("m")) {
				long timeInMili = date.getTime();
				date = new Date(timeInMili + (addTime * ONE_MINUTE_IN_MILLIS));
				returnTime = dateFormat.format(date);
			}

			return returnTime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnTime;
	}

}
