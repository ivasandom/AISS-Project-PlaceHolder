package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.gitHub.Repository;
import aiss.model.gitHub.User;
import aiss.model.resource.GitHubResource;


public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HomeController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String accessToken = (String) req.getSession().getAttribute("GitHub-token");
		
		if (accessToken != null) {
			
			User currentUser = GitHubResource.getGithubUser(accessToken);
			Repository[] repositories = GitHubResource.getMyRepositories(accessToken);
			
//			System.out.println(currentUser.getName());
//			log.info(currentUser.getLogin());
			req.setAttribute("repositories", repositories);
			
		}
		
		
 		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}