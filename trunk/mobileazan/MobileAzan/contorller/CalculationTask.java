/**
 * 
 */
package contorller;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import model.PrayerTime;


import view.MainForm;
import view.Tokens;


/**
 * @author Alaa
 *
 */
public class CalculationTask extends TimerTask {
	private PrayerTime prayers[];
	private Timer timer;
	private MainForm model;


	public CalculationTask(MainForm model,PrayerTime[] prayers,Timer timer) {
		this.prayers = prayers;
		this.timer = timer;
		this.model = model;
	}


	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	public void run() {
		model.calculatePrayerTimes();
		prayers = model.getPrayerTimes();
		timer.schedule(new FajrAzanTask(model), prayers[0].getCalendar().getTime());
		timer.schedule(new AzanTask(model,Tokens.get(Tokens.DUHR)), prayers[2].getCalendar().getTime());
		timer.schedule(new AzanTask(model,Tokens.get(Tokens.ASR)), prayers[3].getCalendar().getTime());
		timer.schedule(new AzanTask(model,Tokens.get(Tokens.MAGHRIB)), prayers[4].getCalendar().getTime());
		timer.schedule(new AzanTask(model,Tokens.get(Tokens.ESHA)), prayers[5].getCalendar().getTime());
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.set(Calendar.DAY_OF_MONTH, tomorrow.get(Calendar.DAY_OF_MONTH)+1);
		tomorrow.set(Calendar.HOUR_OF_DAY, 0);
		tomorrow.set(Calendar.MINUTE, 1);
		timer.schedule(new CalculationTask(model,prayers,timer), tomorrow.getTime());



	}

}
