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
				return "«·›Ã—";
			case SHRUQ:
				return "«·‘—Êﬁ";
			case DUHR:
				return "«·ŸÂ—";
			case ASR:
				return "«·⁄’—";
			case MAGHRIB:
				return "«·„€—»";
			case ESHA:
				return "«·⁄‘«¡";
			case HIDE:
				return "≈Œ›«¡";
			case SET:
				return "≈⁄œ«œ« ";
			case TEST:
				return "«Œ »«—";
			case EXIT:
				return "Œ—ÊÃ";
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
			}
			
		}
		return null;
	}
	
	
	
	

}
