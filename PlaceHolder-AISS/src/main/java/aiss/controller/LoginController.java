package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.utility.Checkers;

public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LoginController.class.getName());
	private static final List<String> availableProviders = new ArrayList<String>(Arrays.asList("GitHub", "GitLab", "Todoist", "Harvest"));
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		log.log(Level.INFO, "Processing LoginController.");
		
		String provider = req.getParameter("provider");
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		if (!availableProviders.contains(provider) || provider == null) {
			
			// If user is not authenticated and no provider is received then render login view
			if (Checkers.notNull(accessTokenTodoist, accessTokenHarvest)) {
				log.log(Level.INFO, "There are no providers.");
				resp.sendRedirect("/");
			} else {
				log.log(Level.INFO, "Rendering login view.");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
			
		} else {
			
			log.log(Level.INFO, "Authenticate in provider: " + provider);
			req.getRequestDispatcher("/AuthController/" + provider).forward(req,resp);
			
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
	
}
