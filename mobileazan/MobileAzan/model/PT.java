package model;

import pt.Date;
import pt.Location;
import pt.Method;
import pt.Prayer;
import pt.PrayerTime;

/**
 * @author Alaa
 *
 */
public class PT {
	public static model.PrayerTime [] getPrayerTimes(int year, int month, int day,float latitude,float longitude,float gmt,int method) {
		model.PrayerTime[] prayers = new model.PrayerTime[7];
	    int i;
	  
	    Location loc = new Location();
	    Method conf = new Method();
	    Date date = new Date();

	    Prayer[] ptList = new Prayer[6];
	    for (int j = 0; j < ptList.length; j++) {
			ptList[j] = new Prayer();
		}
	    Prayer[] imsaak = new Prayer[1];
	    imsaak[0] = new Prayer();
	    Prayer[] nextImsaak = new Prayer[1];
	    nextImsaak[0] = new Prayer();
	    Prayer[] nextFajr = new Prayer[1];
	    nextFajr[0] = new Prayer();
	    /* fill the Date structure */
	    date.day = day;
	    date.month = month;
	    date.year = year;
	    /* fill the location info. structure */
	    loc.degreeLat = latitude;
	    loc.degreeLong = longitude;
	    loc.gmtDiff = gmt;
	    loc.dst = 0;
	    loc.seaLevel = 0;
	    loc.pressure = 1010;
	    loc.temperature= 10;

	  
	    /* auto fill the method structure. Have a look at prayer.h for a
	     * list of supported methods */
	    // TODO Use another methods, not egypt survey only
	    PrayerTime.getMethod(method, conf);
	    conf.round = 0;
	  
	    /* Call the main function to fill the Prayer times array of
	     * structures */
	    PrayerTime.getPrayerTimes (loc, conf, date, ptList);

	    /* Call functions for other prayer times and qibla */
	    PrayerTime.getImsaak (loc, conf, date, imsaak);
	    PrayerTime.getNextDayFajr (loc, conf, date, nextFajr);
	    PrayerTime.getNextDayImsaak (loc, conf, date, nextImsaak);
	     

	    for (i = 0; i < 6; i++) {
	    	prayers[i] = new model.PrayerTime(year,month,day,ptList[i].hour,ptList[i].minute,ptList[i].second);
	    }
	    prayers[6] = new model.PrayerTime(year,month,day+1,nextFajr[0].hour,nextFajr[0].minute,nextFajr[0].second);

		return prayers;
	}
}
