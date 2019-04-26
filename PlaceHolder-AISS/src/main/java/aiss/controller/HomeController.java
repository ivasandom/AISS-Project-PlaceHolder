package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.github.Repository;
import aiss.model.github.User;
import aiss.model.resource.GitHubResource;
import aiss.model.resource.TodoistResource;
import aiss.model.todoist.Project;


public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HomeController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String accessTokenGitHub = (String) req.getSession().getAttribute("GitHub-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		if (accessTokenGitHub != null) {
			
			GitHubResource githubResource = new GitHubResource(accessTokenGitHub);
			User currentUser = githubResource.getGithubUser();
			Repository[] repositories = githubResource.getMyRepositories();
			
			req.setAttribute("repositories", repositories);
			req.setAttribute("user", currentUser);
			
		}
		
		if (accessTokenTodoist != null) {
			
			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			Project[] myProjects = todoistResource.getMyProjects();
			req.setAttribute("myProjects", myProjects);
			
		}
		
 		req.getRequestDispatcher("index.jsp").forward(req, resp);
 		
	}
}