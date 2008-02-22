/**
 * 
 */
package azan;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

import main.MainForm;
import main.Settings;

/**
 * @author Alaa
 *
 */
public class AzanPlayer extends Thread{

	private Player player;
//	private int currentPiece;
	private MainForm model;

	public AzanPlayer(MainForm model) {
		this.model = model;
	}
	public void run() {
		try{
		playAzan();
		model.setFocus();}
		catch(Exception e){}
	}
	
	private void playAzan() {
//		for(currentPiece = 0;currentPiece < 12;currentPiece ++) {
//			playCurrentPiece();
//		}
		playCurrentPiece();
	}
	
	public void stop(){
//		currentPiece = 100;
		if(player != null)
		try {
			player.stop();
			player.deallocate();
			player.close();
			} catch (MediaException e) {}
			catch(Exception e){}

	}
	
	private void playCurrentPiece() {
		if(player != null) {
			try {
			player.stop();
			player.deallocate();
			player.close();
			} catch (MediaException e) {}
			catch(IllegalStateException e){}
		}
		InputStream is = this.getClass().getResourceAsStream("/wav2/Makkah.amr");
		try {
			player = (Manager.createPlayer(is, "audio/amr"));
			if(player.getState()!=Player.CLOSED)
			player.realize();
			
		    // get volume control for player and set volume to max
		    VolumeControl vc;
			vc = (VolumeControl) player.getControl("VolumeControl");
		    if(vc != null)
		    {
		      vc.setLevel(Settings.getInstance(model).getVol());
		    }
			if(player.getState()!=Player.CLOSED)
		    player.prefetch();
			if(player.getState()!=Player.CLOSED)
		    player.start();
		    while(player.getState() == Player.STARTED) {}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e){}
	
	}

}
