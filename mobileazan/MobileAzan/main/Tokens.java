/**
 * 
 */
package main;

/**
 * @author Alaa
 *
 */
public class Tokens {
	
	public static final int FAJR = 1;
	public static final int SHRUQ = 2;
	public static final int DUHR = 3;
	public static final int ASR = 4;
	public static final int MAGHRIB = 5;
	public static final int ESHA = 6;
	
	public static final int ARABIC = 11;
	public static final int ENGLISH = 10;
	
//	public static final int
//	public static final int
//	public static final int
//	public static final int
//	public static final int
//	public static final int
//	public static final int
//	public static final int
//	public static final int
//	public static final int

	public static final int HIDE = 20;
	public static final int SET = 21;
	public static final int TEST = 22;
	public static final int EXIT = 23;
	public static final int STOP = 24;
	public static final int SAVE = 25;
	public static final int CANCEL = 26;

	public static final int MOBILE_AZAN = 30;
	public static final int AZAN = 31;
	public static final int TIME_TO_PRAY = 32;

	public static final int EGYPTIAN_SURVEY = 40;
	public static final int KARACHI_UNIVERSITY_SHAFEY = 41;
	public static final int KARACHI_UNIVERSITY_HANAFI = 42;
	public static final int SOCIETY_OF_NORTH_AMERICA = 43;
	public static final int MUSLIM_LEAGUE = 44;
	public static final int UMM_ALQURA = 45;
	
	public static final int LAT = 50;
	public static final int LON = 51;
	public static final int GMT = 52;
	public static final int METHOD = 53;
	public static final int VOLUME = 54;
//	public static final int
//	public static final int
//	public static final int
//	public static final int
//	public static final int
	
	private static int lang = ENGLISH; 
	
	static {
		String l = System.getProperty("microedition.locale");
		
		if (l.equals("ar"))
			lang = ARABIC;
	} 
	
	private Tokens() {}
	
	
	public static String get(int tokenID) {
		if (lang == ARABIC) {
			switch(tokenID) {
			case FAJR:
				return "�����";
			case SHRUQ:
				return "������";
			case DUHR:
				return "�����";
			case ASR:
				return "�����";
			case MAGHRIB:
				return "������";
			case ESHA:
				return "������";
			case HIDE:
				return "�����";
			case SET:
				return "�������";
			case TEST:
				return "������ ������";
			case EXIT:
				return "����";
			case STOP:
				return "����� ������";
			case SAVE:
				return "���";
			case CANCEL:
				return "�����";
			case MOBILE_AZAN:
				return "���� ������";
			case AZAN:
				return "������";
			case TIME_TO_PRAY:
				return "��� ���� :";
			case EGYPTIAN_SURVEY:
				return "���� ������� �������";
			case KARACHI_UNIVERSITY_SHAFEY:
				return "����� ������ (�����)";
			case KARACHI_UNIVERSITY_HANAFI:
				return "����� ������ (����)";
			case SOCIETY_OF_NORTH_AMERICA:
				return "���� ������ ��������";
			case MUSLIM_LEAGUE:
				return "����� ������ ��������";
			case UMM_ALQURA:
				return "����� �� �����";
			case LAT:
				return "�� ����� :";
			case LON:
				return "�� ����� :";
			case GMT:
				return "������� ������ :";
			case METHOD:
				return "����� ������ :";
			case VOLUME:
				return "����� ����� :";
			}
		} else if(lang == ENGLISH){
			switch(tokenID) {
			case FAJR:
				return "Fajr";
			case SHRUQ:
				return "Shruq";
			case DUHR:
				return "Duhr";
			case ASR:
				return "Asr";
			case MAGHRIB:
				return "Maghrib";
			case ESHA:
				return "Esha";
			case HIDE:
				return "Hide";
			case SET:
				return "Settings";
			case TEST:
				return "Test Azan";
			case EXIT:
				return "Exit";
			case STOP:
				return "Stop Azan";
			case SAVE:
				return "Save";
			case CANCEL:
				return "Cancel";
			case MOBILE_AZAN:
				return "Mobile Azan";
			case AZAN:
				return "Azan";
			case TIME_TO_PRAY:
				return "Time to pray:";
			case EGYPTIAN_SURVEY:
				return "Egyptian Survey";
			case KARACHI_UNIVERSITY_SHAFEY:
				return "Karachi university (Shafey)";
			case KARACHI_UNIVERSITY_HANAFI:
				return "Karachi university (Hanafi)";
			case SOCIETY_OF_NORTH_AMERICA:
				return "Society of North America";
			case MUSLIM_LEAGUE:
				return "Muslim League";
			case UMM_ALQURA:
				return "Umm AlQura University";
			case LAT:
				return "Latitude :";
			case LON:
				return "Longitude :";
			case GMT:
				return "Time zone :";
			case METHOD:
				return "Method :";
			case VOLUME:
				return "Volume :";
			}
			
		}
		return null;
	}
	
	
	
	

}
