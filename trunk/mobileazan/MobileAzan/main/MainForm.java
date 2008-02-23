package main;

import java.util.Calendar;
import java.util.Timer;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import azan.AzanPlayer;
import azan.AzanTask;
import azan.CalculationTask;
import azan.FajrAzanTask;
import azan.PT;
import azan.PrayerTime;

public class MainForm extends MIDlet implements CommandListener{

	private Form form;
	private Form optionsForm;
	private Form azanForm;
	private StringItem[] prayers;
	private PrayerTime[] prayerTimes;
	private Settings settings;
	private TextField latText;
	private TextField lonText;
	private TextField gmtText;
	private ChoiceGroup methodList;
	private Gauge volGauge;
	
	private Command exit = new Command(Tokens.get(Tokens.EXIT),Command.EXIT,1);
	private Command set = new Command(Tokens.get(Tokens.SET),Command.OK,1);
	private Command test = new Command(Tokens.get(Tokens.TEST),Command.SCREEN,1);
	private Command hide = new Command(Tokens.get(Tokens.HIDE),Command.BACK,1);
	private Command stop = new Command(Tokens.get(Tokens.STOP),Command.STOP,1);
	public Command cancel = new Command(Tokens.get(Tokens.CANCEL),Command.CANCEL,1);
	public Command save = new Command(Tokens.get(Tokens.SAVE),Command.OK,1);

	private Timer timer;
	private AzanPlayer player;

	public AzanPlayer getPlayer() {
		return player;
	}

	public void setPlayer(AzanPlayer player) {
		this.player = player;
	}

	/*
	 * Things to do..
	 * ------------------done--------------------------------------1- Adjust properties, making it save and load from a record store..
	 * ------------------done--------------------------------------2- Play azan in times
	 * ------------------done--------------------------------------3- Add Options form
	 * ==postponed== 4- Make the device silent in pray-time and gom3a and prespecified periods and keyam in ramandan
	 * 5- Make a special alarm for Fajr (Maybe the azan only is enough)
	 * ==after all== 6- Make a nice GUI
	 * ------------------done--------------------------------------7- Enable user to choose another method
	 * ------------------done--------------------------------------8- Add the option to minimize
	 * 9- Show qibla direction
	 * 10- Return to the right form when midlet is resumed
	 * ------------------done--------------------------------------11- Enable user to play, stop azan
	 * ------------------done--------------------------------------12- Add volume level control
	 * 13- Arabize
	 * 14- Optimize by removing all items from memory before minimizing and reallocate on startApp()
	 * 15- Multiple Azan voices
	 * 16- Multiple Volume control
	 * 17- Using Push registery to reload midlet when needed
	 * 18- Convert audio resources to AMR format
	 */
	public MainForm() {
		settings = Settings.getInstance(this);
		settings.load();
		calculatePrayerTimes();
		int i;
		Calendar now = Calendar.getInstance();
		for (i = 0; i < prayers.length; i++) {
			if(prayerTimes[i].getCalendar().after(now))
				break;
		}		timer = new Timer();
		if (i<= 0) { //add fajr azan task
			timer.schedule(new FajrAzanTask(this), prayerTimes[0].getCalendar().getTime());
		}
		if (i<= 2) { //duhr
			timer.schedule(new AzanTask(this,Tokens.get(Tokens.DUHR)), prayerTimes[2].getCalendar().getTime());

		}
		if (i<= 3) { //asr
			timer.schedule(new AzanTask(this,Tokens.get(Tokens.ASR)), prayerTimes[3].getCalendar().getTime());

		}
		if (i<= 4) { //maghrib
			timer.schedule(new AzanTask(this,Tokens.get(Tokens.MAGHRIB)), prayerTimes[4].getCalendar().getTime());

		}
		if (i<= 5) { //esha
			timer.schedule(new AzanTask(this,Tokens.get(Tokens.ESHA)), prayerTimes[5].getCalendar().getTime());

		}
		if (i<= 6) { //calculation task
			Calendar tomorrow = Calendar.getInstance();
			tomorrow.set(Calendar.DAY_OF_MONTH, tomorrow.get(Calendar.DAY_OF_MONTH)+1);
			tomorrow.set(Calendar.HOUR_OF_DAY, 0);
			tomorrow.set(Calendar.MINUTE, 1);
			timer.schedule(new CalculationTask(this,prayerTimes,timer), tomorrow.getTime());
		}


	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		throw new MIDletStateChangeException();

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		Display.getDisplay(this).setCurrent(form);
	}

	public void commandAction(Command cmd, Displayable arg1) {
		if(cmd == exit)
			notifyDestroyed();
		else if (cmd == set) {
			optionsForm = new Form(Tokens.get(Tokens.SET));
			latText = new TextField(Tokens.get(Tokens.LAT),Float.toString(settings.getLat()),7,TextField.DECIMAL);
			optionsForm.append(latText);
			lonText = new TextField(Tokens.get(Tokens.LON),Float.toString(settings.getLon()),7,TextField.DECIMAL);
			optionsForm.append(lonText);
			gmtText = new TextField(Tokens.get(Tokens.GMT),Integer.toString(settings.getGmt()),5,TextField.NUMERIC);
			optionsForm.append(gmtText);
			methodList = new ChoiceGroup(Tokens.get(Tokens.METHOD),ChoiceGroup.POPUP);//|ChoiceGroup.TEXT_WRAP_ON);
			methodList.setFitPolicy(ChoiceGroup.TEXT_WRAP_ON);
			methodList.append(Tokens.get(Tokens.EGYPTIAN_SURVEY), null);
			methodList.append(Tokens.get(Tokens.KARACHI_UNIVERSITY_SHAFEY), null);
			methodList.append(Tokens.get(Tokens.KARACHI_UNIVERSITY_HANAFI), null);
			methodList.append(Tokens.get(Tokens.SOCIETY_OF_NORTH_AMERICA), null);
			methodList.append(Tokens.get(Tokens.MUSLIM_LEAGUE), null);
			methodList.append(Tokens.get(Tokens.UMM_ALQURA), null);
			boolean [] flags = new boolean[6];
			for (int i = 0; i < flags.length; i++) {
				flags[i] = false;
			}
			flags[settings.getMethod()-1] = true;
			methodList.setSelectedFlags(flags);
			optionsForm.append(methodList);
			volGauge = new Gauge(Tokens.get(Tokens.VOLUME),true,100,settings.getVol());
			optionsForm.append(volGauge);
			optionsForm.addCommand(cancel);
			optionsForm.addCommand(save);
			optionsForm.setCommandListener(new OptionsListener(this));
			Display.getDisplay(this).setCurrent(optionsForm);
		} else if (cmd == stop) {
			player.stop();
			setFocus();
		} else if (cmd == test) {
				showAzanForm(Tokens.get(Tokens.TEST));
				player = new AzanPlayer(this);
				player.start();
			} else if (cmd == hide) {
				Display.getDisplay(this).setCurrent(null);
			}

		}

		public void setFocus() {
			Display.getDisplay(this).setCurrent(form);
		}
		public void saveSettings() {
			settings.setLat(Float.parseFloat(latText.getString()));
			settings.setLon(Float.parseFloat(lonText.getString()));
			settings.setGmt(Integer.parseInt(gmtText.getString()));
			settings.setMethod(methodList.getSelectedIndex()+1);
			settings.setVol(volGauge.getValue());
			settings.save();
		}
		public void calculatePrayerTimes() {
			Calendar now = Calendar.getInstance();
			prayerTimes = PT.getPrayerTimes(now.get(Calendar.YEAR), now.get(Calendar.MONTH) +1, now.get(Calendar.DAY_OF_MONTH), settings.getLat(), settings.getLon(), settings.getGmt(),settings.getMethod());
			prayers = new StringItem[6];
			prayers[0] = new StringItem(Tokens.get(Tokens.FAJR),prayerTimes[0].getTime());
			prayers[1] = new StringItem(Tokens.get(Tokens.SHRUQ),prayerTimes[1].getTime());
			prayers[2] = new StringItem(Tokens.get(Tokens.DUHR),prayerTimes[2].getTime());
			prayers[3] = new StringItem(Tokens.get(Tokens.ASR),prayerTimes[3].getTime());
			prayers[4] = new StringItem(Tokens.get(Tokens.MAGHRIB),prayerTimes[4].getTime());
			prayers[5] = new StringItem(Tokens.get(Tokens.ESHA),prayerTimes[5].getTime());
			form = new Form(Tokens.get(Tokens.MOBILE_AZAN));
			for (int i = 0; i < prayers.length; i++) {
				form.append(prayers[i]);
			}
			form.addCommand(exit);
			form.addCommand(set);
			form.addCommand(test);
			form.addCommand(hide);
			form.setCommandListener(this);
			setFocus();
		}
		public PrayerTime[] getPrayerTimes() {
			return prayerTimes;
		}
		public void showAzanForm(String prayerName) {
			azanForm = new Form(Tokens.get(Tokens.AZAN));
			StringItem timeToPrayer = new StringItem(Tokens.get(Tokens.TIME_TO_PRAY),prayerName);
			azanForm.append(timeToPrayer);
			azanForm.addCommand(stop);
			azanForm.setCommandListener(this);
			Display.getDisplay(this).setCurrent(azanForm);
			resumeRequest();
		}

		public void playerUpdate(Player player, String event, Object eventData) {
			if(player == this.player && event.equals(PlayerListener.END_OF_MEDIA)) {
				setFocus();
			}
		}

	}
