package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.github.Repository;
import aiss.model.github.User;
import aiss.model.gitlab.GitLabRepository;
import aiss.model.harvest.Profile;
import aiss.model.harvest.Projects;
import aiss.model.resource.GitHubResource;
import aiss.model.resource.GitLabResource;
import aiss.model.resource.HarvestResource;
import aiss.model.resource.TodoistResource;


public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HomeController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		log.log(Level.INFO, "Processing HomeController.");

		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		
		if (accessTokenHarvest != null) {
			HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
			
			Profile profile = harvestResource.getProfile();
			Projects myProjects = harvestResource.getMyProjects();

			req.setAttribute("myProjects", myProjects.getProjects());
			req.setAttribute("profile", profile);
		}else {
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		
		log.log(Level.INFO, "HomeController obtained successfully. Forwarding to index");

 		req.getRequestDispatcher("index.jsp").forward(req, resp);
 		
	}
}