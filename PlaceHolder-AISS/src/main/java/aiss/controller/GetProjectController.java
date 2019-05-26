package aiss.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.harvest.Profile;
import aiss.model.harvest.Project;
import aiss.model.harvest.TaskAssignment;
import aiss.model.resource.HarvestResource;
import aiss.model.resource.TodoistResource;
import aiss.model.todoist.TaskSimple;
import aiss.utility.Checkers;
import aiss.utility.ProjectConfig;

public class GetProjectController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Redirects to login if accessTokenHarvest or accessTokenTodoist do not exist.
		if(Checkers.checkAuthenticatedOrRedirect(req.getSession(), resp)) return;
		
		log.log(Level.INFO, "Processing GetProjectController.");
		String projectId = req.getParameter("id");
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
			
			
		if (projectId != null) {
			log.log(Level.INFO, "Getting project");
					
			HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			Profile profile = harvestResource.getProfile();
			Project project = harvestResource.getProject(projectId);
					
					
			req.setAttribute("profile", profile);
			if (project==null) {
				
				log.log(Level.WARNING, "Error. The project of that id is null.");
				
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				ProjectConfig projectConfig = new ProjectConfig(harvestResource, project);
				List<TaskAssignment> harvestTasks = harvestResource.getProjectTasks(projectId);
				TaskSimple[] todoistTasks = todoistResource.getActiveTasks(projectConfig.getTodoistProjectId().toString());
						
				req.setAttribute("project", project);
				req.setAttribute("projectConfig", projectConfig);
				req.setAttribute("harvestTasks", harvestTasks);
				req.setAttribute("todoistTasks", todoistTasks);
				
				// for header.jsp
				req.setAttribute("myProjects", harvestResource.getMyProjects().getProjects());
				req.setAttribute("profile", harvestResource.getProfile());
				
				log.log(Level.FINE, "GetProjectController processed successfully.");
				
				req.getRequestDispatcher("project.jsp").forward(req, resp);
			}
					
		} else {
			log.log(Level.WARNING, "Error. Id is null.");
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
		
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
}
