package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.harvest.Project;
import aiss.model.resource.HarvestResource;
import aiss.model.resource.TodoistResource;
import aiss.utility.Checkers;
import aiss.utility.ProjectConfig;

public class AddProjectController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Redirects to login if accessTokenHarvest or accessTokenTodoist do not exist.
		if(Checkers.checkAuthenticatedOrRedirect(req.getSession(), resp)) return;
		
		log.log(Level.INFO, "Processing AddProjectController.");

		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
		TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			
		req.setAttribute("clients", harvestResource.getClients());
		req.setAttribute("todoistProjects", todoistResource.getMyProjects());
			
		// Header.jsp attributes
		req.setAttribute("myProjects", harvestResource.getMyProjects().getProjects());
		req.setAttribute("profile", harvestResource.getProfile());
			
		req.getRequestDispatcher("/projects-create.jsp").forward(req, resp);
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		// Redirects to login if accessTokenHarvest or accessTokenTodoist do not exist.
		if(Checkers.checkAuthenticatedOrRedirect(req.getSession(), resp)) return;
		
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		// Form Data
		Long clientId = Long.valueOf(req.getParameter("client_id"));
		String name = req.getParameter("name");
		boolean isBillable = Boolean.valueOf(req.getParameter("is_billable"));
		String billBy = req.getParameter("bill_by");
		String budgetBy = req.getParameter("budget_by");
		String todoistProjectId = req.getParameter("todoistProject");
		String githubRepository = req.getParameter("githubRepository");
		String gitlabRepository = req.getParameter("gitlabRepository");
		
		// Resources
		HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
		TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			
		Project nuevoProject = harvestResource.createProject(clientId, name,  isBillable, billBy, budgetBy);
			
		if (nuevoProject != null) {
			
			ProjectConfig config = nuevoProject.getConfig();
			
			if (todoistProjectId == null || todoistProjectId.equals("new_project")) {
				aiss.model.todoist.Project todoistNewProject = todoistResource.createProject(name);
				config.setTodoistProjectId(todoistNewProject.getId().toString());
			} else {
				config.setTodoistProjectId(todoistProjectId);
			}
				
			if (githubRepository != null && githubRepository != "") config.setGithubRepository(githubRepository);
			if (gitlabRepository != null && gitlabRepository != "") config.setGitlabRepository(gitlabRepository);
				
			config.updateConfig(harvestResource, nuevoProject);
			
			resp.sendRedirect("/projects?id="+nuevoProject.getId());
			log.log(Level.FINE, "Project added. Forwarding to new project page.");

		} else {
			
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			log.log(Level.SEVERE, "The project could not be added. Perhaps project name already exists.");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);;

		}
		
	}

}
