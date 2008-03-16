/**
 * 
 */
package contorller;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import view.MainForm;

/**
 * @author Alaa
 *
 */
public class OptionsListener implements CommandListener {
	
	private MainForm mainForm;

	public OptionsListener(MainForm mainForm) {
		this.mainForm = mainForm;
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command cmd, Displayable arg1) {
		if(cmd == mainForm.cancel)
			mainForm.setFocus();
		else if (cmd == mainForm.save) {
			mainForm.saveSettings();
			mainForm.calculatePrayerTimes();
			mainForm.setFocus();
		}

	}

}
