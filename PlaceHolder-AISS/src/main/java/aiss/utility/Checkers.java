package aiss.utility;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Checkers {
	
	public static boolean notNull(Object ...objects) {
		
		for (Object obj: objects) {
			if (obj == null) {
				return false;
			}
		}
		
		return true;
		
	}
	
	public static boolean checkAuthenticatedOrRedirect(HttpSession session, HttpServletResponse resp) throws IOException {
		String accessTokenHarvest = (String) session.getAttribute("Harvest-token");
		String accessTokenTodoist = (String) session.getAttribute("Todoist-token");
		
		if (!Checkers.notNull(accessTokenHarvest, accessTokenTodoist)) {
			System.out.println("hola!");
			resp.sendRedirect("/login");
			return true;
		}
		return false;
	}
}
