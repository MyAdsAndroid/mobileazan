package azan;

import java.util.Calendar;

/**
 * @author Alaa
 *
 */
public class PrayerTime {
	private int year,month,day,hour,minute,second,ampm;

	public PrayerTime(int year, int month, int day, int hour, int minute, int second) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour % 12;
		this.minute = minute;
		this.second = second;
		this.ampm = (hour >= 12)?1:0;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}


	public int getMinute() {
		return minute;
	}

	public int getMonth() {
		return month;
	}

	public int getSecond() {
		return second;
	}

	public int getYear() {
		return year;
	}
	public Calendar getCalendar() {
		Calendar pt = Calendar.getInstance();
		pt.set(Calendar.YEAR, year);
		pt.set(Calendar.MONTH, month - 1);
		pt.set(Calendar.DAY_OF_MONTH, day);
		pt.set(Calendar.HOUR, hour);
		pt.set(Calendar.AM_PM,ampm == 1?Calendar.PM:Calendar.AM);
		pt.set(Calendar.MINUTE, minute);
		pt.set(Calendar.SECOND, second);
		return pt;
	}

	public int getAmpm() {
		return ampm;
	}
	public String getTime() {
		return Integer.toString(getHour()==0?12:getHour()) + ":" + Integer.toString(getMinute()) + ":" +Integer.toString(getSecond()) + " " + (ampm==1?"PM":"AM");
	}
	
}
