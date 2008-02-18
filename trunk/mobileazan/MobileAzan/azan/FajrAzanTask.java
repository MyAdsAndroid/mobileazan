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
public class FajrAzanTask extends TimerTask {


	private MainForm model;

	public FajrAzanTask(MainForm model) {
		this.model = model;
	}
	
	public void run() {
		model.showAzanForm("Fajr");
		model.setPlayer(new AzanPlayer(model));
		model.getPlayer().start();
	}
	

}
