/**
 * 
 */
package pt;

import pt.Date;

/**
 * @author Alaa
 *
 */
public class Date{
	public Date(Date date) {
		this.day = date.day;
		this.month = date.month;
		this.year = date.year;
	}
	public Date() {
	}
	public int day;
	public int month;
	public int year;
}
