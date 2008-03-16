/**
 * 
 */
package contorller;

import java.util.TimerTask;

import view.MainForm;
import view.Tokens;



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
		model.showAzanForm(Tokens.get(Tokens.FAJR));
		model.setPlayer(new AzanPlayer(model));
		model.getPlayer().start();
	}
	

}
