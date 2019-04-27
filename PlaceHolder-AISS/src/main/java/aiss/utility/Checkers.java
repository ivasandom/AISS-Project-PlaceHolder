package aiss.utility;

public class Checkers {
	
	public static boolean notNull(Object ...objects) {
		
		for (Object obj: objects) {
			if (obj == null) {
				return false;
			}
		}
		
		return true;
		
	}
}
