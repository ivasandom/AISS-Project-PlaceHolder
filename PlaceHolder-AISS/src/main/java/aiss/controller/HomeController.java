package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.harvest.Profile;
import aiss.model.harvest.Projects;
import aiss.model.resource.HarvestResource;

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
		}
		
		log.log(Level.INFO, "HomeController obtained successfully. Forwarding to index");

 		req.getRequestDispatcher("index.jsp").forward(req, resp);
 		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
	
}