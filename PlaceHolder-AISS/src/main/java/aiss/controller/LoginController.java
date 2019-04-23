package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LoginController.class.getName());
	private static final List<String> availableProviders = new ArrayList<String>(Arrays.asList("GitHub", "GitLab", "Bitbucket", "Trello", "Todoist"));
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String provider = req.getParameter("provider");
		log.info(provider);
		if (!availableProviders.contains(provider)) {
			req.getRequestDispatcher("error").forward(req, resp);
		}
		
		String accessToken = (String) req.getSession().getAttribute(provider + "-token");
		if (accessToken != null) {
			log.info(accessToken);
			req.getRequestDispatcher("/").forward(req, resp);
		} else {
			log.info("Intentando obtener token de " + provider);
			req.getRequestDispatcher("/AuthController/" + provider).forward(req,resp);
		}
	}
}
