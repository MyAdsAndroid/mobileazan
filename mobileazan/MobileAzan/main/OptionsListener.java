/**
 * 
 */
package main;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

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
	public void commandAction(Command arg0, Displayable arg1) {
		String label = arg0.getLabel();
		if(label.equals("Cancel"))
			mainForm.setFocus();
		else if (label.equals("Save")) {
			mainForm.saveSettings();
			mainForm.calculatePrayerTimes();
			mainForm.setFocus();
		}

	}

}
