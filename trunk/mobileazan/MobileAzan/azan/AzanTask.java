/**
 * 
 */
package azan;

import java.util.TimerTask;

import main.MainForm;


/**
 * @author Alaa
 *
 */
public class AzanTask extends TimerTask {
	
	private MainForm model;
	private String prayerName;

	public AzanTask(MainForm model, String prayerName) {
		this.model = model;
		this.prayerName = prayerName;
	}
	
	public void run() {
		model.showAzanForm(prayerName);
		model.setPlayer(new AzanPlayer(model));
		model.getPlayer().start();
	}

}
