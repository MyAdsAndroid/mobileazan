/**
 * 
 */
package main;

import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

/**
 * @author Alaa
 *
 */
public class Settings {
	private float lat=31.1981f;
	private float lon=29.9192f;
	private int gmt = 2;
	private int vol = 100;
	private String azanFile;
//	private MIDlet midlet;
	private int method = pt.PrayerTime.EGYPT_SURVEY;
	private static Settings instance;
	public static Settings getInstance(MIDlet midlet) {
		if(instance == null)
			instance = new Settings(midlet);
		return instance;
	}
	private Settings(MIDlet midlet) {
//		this.midlet = midlet;
	}
	public String getAzanFile() {
		return azanFile;
	}
	public int getGmt() {
		return gmt;
	}
	public float getLat() {
		return lat;
	}
	public float getLon() {
		return lon;
	}
	public void load() {
		try {
			RecordStore rs = RecordStore.openRecordStore("Mobile Azan", false);
			RecordEnumeration renum = null;
			renum = rs.enumerateRecords(null, null, false);
			while (renum.hasNextElement()) {
				String recStr = new String(renum.nextRecord());
				String valueStr = recStr.substring(1);
				switch (recStr.charAt(0)) {
				case 'a':
					lat = Float.parseFloat(valueStr);
					break;
				case 'b':
					lon = Float.parseFloat(valueStr);
					break;
				case 'c':
					gmt = Integer.parseInt(valueStr);
					break;
				case 'd':
					method = Integer.parseInt(valueStr);
					break;
				case 'e':
					vol = Integer.parseInt(valueStr);
					break;
				}
			}
			renum.destroy();
			rs.closeRecordStore();
		} catch (RecordStoreFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordStoreNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	public void oldload() {
//		try {
//			RecordStore rs = RecordStore.openRecordStore("Mobile Azan", false);
//			RecordEnumeration renum=null;
//			renum = rs.enumerateRecords(null, null, false);
//			String read = new String(rs.getRecord(renum.nextRecordId()));
//			
//			String latString=null;
//			String lonString=null;
//			String gmtString=null;	
//			String methodString = null;
//			try {
//				latString = read.substring(0,6);
//				lonString = read.substring(8,14);
//				gmtString = read.substring(16,read.lastIndexOf(':')-1);
//				methodString = read.substring(read.lastIndexOf(':')+1,read.length());
//				try {
//					lat = Float.parseFloat(latString);
//				} catch (NumberFormatException e) {}
//				try {
//					lon = Float.parseFloat(lonString);
//				} catch (NumberFormatException e) {}
//				try {
//					gmt = Integer.parseInt(gmtString);
//				} catch (NumberFormatException e) {}
//				try {
//					method = Integer.parseInt(methodString);
//				} catch (NumberFormatException e) {}
//			} catch (StringIndexOutOfBoundsException e) {}
//			renum.destroy();
//			rs.closeRecordStore();
//		} catch (RecordStoreFullException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RecordStoreNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RecordStoreException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public void save() {
		try {
			try{
			RecordStore.deleteRecordStore("Mobile Azan");
			}
			catch(RecordStoreNotFoundException e){}
			RecordStore rs = RecordStore.openRecordStore("Mobile Azan", true);
			byte[] latB = ('a'+Float.toString(lat)).getBytes();
			byte[] lonB = ('b'+Float.toString(lon)).getBytes();
			byte[] gmtB = ('c'+Integer.toString(gmt)).getBytes();
			byte[] methodB = ('d'+Integer.toString(method)).getBytes();
			byte[] volB = ('e' + Integer.toString(vol)).getBytes();
			rs.addRecord(latB, 0, latB.length);
			rs.addRecord(lonB, 0, lonB.length);
			rs.addRecord(gmtB, 0, gmtB.length);
			rs.addRecord(methodB, 0, methodB.length);
			rs.addRecord(volB, 0, volB.length);
			rs.closeRecordStore();
		} catch (RecordStoreFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordStoreNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public void oldsave() {
//		try {
//			RecordStore rs= RecordStore.openRecordStore("Mobile Azan", true);
//			String toWrite = 
//				Float.toString(lat) + ":" +
//				Float.toString(lon) + ":" +
//				Integer.toString(gmt) + ":" +
//				Integer.toString(method);
//			int count = 0;
//			for( int id = 1; 
//			id < rs.getNextRecordID() && count < rs.getNumRecords();
//			++id ){
//				try{
//					rs.deleteRecord(id);
//				} catch (InvalidRecordIDException e) {}
//				++count;
//			}
//			rs.addRecord(toWrite.getBytes(), 0, toWrite.getBytes().length);
//			rs.closeRecordStore();
//		} catch (RecordStoreFullException e) {
//			Alert alert = new Alert("Exception");
//			alert.setString(e.getMessage());
//			Display.getDisplay(midlet).setCurrent(alert);
//			e.printStackTrace();
//		} catch (RecordStoreNotFoundException e) {
//			Alert alert = new Alert("Exception");
//			alert.setString(e.getMessage());
//			Display.getDisplay(midlet).setCurrent(alert);
//			e.printStackTrace();
//		} catch (RecordStoreException e) {
//			Alert alert = new Alert("Exception");
//			alert.setString(e.getMessage());
//			Display.getDisplay(midlet).setCurrent(alert);
//			e.printStackTrace();
//		}
//	}
	public void setGmt(int gmt) {
		this.gmt = gmt;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public int getMethod() {
		return method ;
	}
	public void setMethod(int method) {
		this.method = method;
	}
	public int getVol() {
		return vol;
	}
	public void setVol(int vol) {
		this.vol = vol;
	}
}
