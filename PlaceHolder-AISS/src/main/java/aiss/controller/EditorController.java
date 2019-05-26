package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.HarvestResource;
import aiss.model.resource.TodoistResource;
import aiss.utility.Checkers;
import aiss.utility.ProjectConfig;

public class EditorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HomeController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// Redirects to login if accessTokenHarvest or accessTokenTodoist do not exist.
		if(Checkers.checkAuthenticatedOrRedirect(req.getSession(), resp)) return;
		
		log.log(Level.INFO, "Processing EditorController.");

		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
		TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
		
		String projectId = req.getParameter("project");
		
		if (projectId != null) {
			
			aiss.model.harvest.Project harvestProject = harvestResource.getProject(projectId);
			
			if (harvestProject == null) {
				log.log(Level.WARNING, "Error. Harvest project is null.");
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				req.getRequestDispatcher("/error.jsp").forward(req, resp);
				return;
			}
			
			ProjectConfig config = new ProjectConfig(harvestResource, harvestProject);
			aiss.model.todoist.Project todoistProject = todoistResource.getProject(config.getTodoistProjectId());
			
			req.setAttribute("project", harvestProject);
			req.setAttribute("projectConfig", config);
			
			log.log(Level.FINE, "EditorController processed successfully.");
			
			req.setAttribute("todoistTasks", todoistResource.getActiveTasks(todoistProject.getId().toString()));
			req.getRequestDispatcher("/editor.jsp").forward(req, resp);
						
		} else {
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			log.log(Level.SEVERE, "Parameter id is required and was not provided.");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
			
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}