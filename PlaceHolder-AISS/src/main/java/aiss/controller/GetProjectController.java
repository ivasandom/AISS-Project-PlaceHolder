package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.harvest.Profile;
import aiss.model.harvest.Project_;
import aiss.model.resource.HarvestResource;
import aiss.utility.ProjectConfig;

public class GetProjectController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetProjectController.class.getName());
	
//	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		log.log(Level.INFO, "Processing GetProjectController.");
//
//		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
//		String projectId = req.getParameter("id");
//		
//		if (accessTokenHarvest != null && projectId != null) {
//			log.log(Level.INFO, "Getting project.");
//
//			HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
//			Project_ project = harvestResource.getProject(projectId);
//			if (project == null) {
//				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//				log.log(Level.WARNING, "Error when obtaining project.");
//
//			}
//			
//			TaskSimple[] tasks = harvestResource.getActiveTasks(projectId);
//			
//			req.setAttribute("myProjects", myProjects);
//			req.setAttribute("project", project);
//			req.setAttribute("projectTasks", tasks);
//			log.log(Level.INFO, "Project obtained successfully.");
//
//		}
//	
//		
//		req.getRequestDispatcher("project.jsp").forward(req, resp);
//	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.log(Level.INFO, "Processing GetProjectController.");
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		String projectId = req.getParameter("id");
		
		if (accessTokenHarvest != null) {
			if (projectId != null) {
				log.log(Level.INFO, "Getting project");
				
				HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
				Profile profile = harvestResource.getProfile();
				Project_ project = harvestResource.getProject(projectId);
				
				req.setAttribute("profile", profile);
				if (project==null) {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				} else {
					ProjectConfig projectConfig = new ProjectConfig(harvestResource, project);
								
					req.setAttribute("project", project);
					req.setAttribute("projectConfig", projectConfig);
					
					req.getRequestDispatcher("project.jsp").forward(req, resp);
				}
				
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			// redirect to harvest login oauth
			TaskSimple[] tasks = todoistResource.getActiveTasks(projectId);
			
			req.setAttribute("myProjects", myProjects);
			req.setAttribute("project", project);
			req.setAttribute("projectTasks", tasks);
			log.log(Level.INFO, "Project obtained successfully.");

		} else {
			log.log(Level.WARNING, "Error when logging");
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// ajax
		doGet(req,resp);
	}
}
