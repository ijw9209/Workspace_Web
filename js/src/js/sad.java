package js;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class sad {

	public static void main(String[] args) {
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			Calendar cal = Calendar.getInstance();
			String today = null;
			today = formatter.format(cal.getTime());
			Timestamp ts = Timestamp.valueOf(today);

			 

			System.out.println( " Timestamp : " + ts);
	}
}
