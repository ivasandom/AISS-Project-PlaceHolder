package aiss.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.harvest.Client_;
import aiss.model.harvest.Project;
import aiss.model.resource.HarvestResource;
import aiss.model.resource.TodoistResource;
import aiss.utility.Checkers;
import aiss.utility.ProjectConfig;

public class UpdateProjectController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UpdateProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.log(Level.INFO, "Processing UpdateProjectController GET");
		
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		String id = req.getParameter("id");
		
		
		if (Checkers.notNull(accessTokenHarvest, accessTokenTodoist)) {
			
			if (id != null) {
				HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
				TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
				
				Project project = harvestResource.getProject(id);
				ProjectConfig projectConfig = new ProjectConfig(harvestResource, project);
				
				List<Client_> clients = harvestResource.getClients();
				
				// for form values
				req.setAttribute("project", project);
				req.setAttribute("projectConfig", projectConfig);
				req.setAttribute("todoistProjects", todoistResource.getMyProjects());
				req.setAttribute("clients", clients);
				
				// for header.jsp
				req.setAttribute("myProjects", harvestResource.getMyProjects().getProjects());
				req.setAttribute("profile", harvestResource.getProfile());
				
				// load JSP file
				req.getRequestDispatcher("/project-edit.jsp").forward(req, resp);
				
			} else {
				// TODO raise HTTP404
				log.log(Level.WARNING, "Error. id is null");
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				req.getRequestDispatcher("/error.jsp").forward(req, resp);
			}
			
		} else {
			// TODO redirect to login
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
				
		
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		
		log.log(Level.INFO, "Processing UpdateProjectController.");

		String projectId = req.getParameter("projectId");
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		
		// Form data
		
		Long clientId = Long.valueOf(req.getParameter("client_id"));
		String name = req.getParameter("name");
		Boolean isBillable = Boolean.valueOf(req.getParameter("is_billable"));
		String billBy = req.getParameter("bill_by");
		String budgetBy = req.getParameter("budget_by");
		
		String todoistProjectId = req.getParameter("todoistProject");
		String githubRepository = req.getParameter("githubRepository");
		String gitlabRepository = req.getParameter("gitlabRepository");
		
		log.log(Level.FINE, "Todoist project id: " + todoistProjectId);
		log.log(Level.FINE, "GitHub repository: " + githubRepository);
		log.log(Level.FINE, "GitLab repository: " + gitlabRepository);
		
		
		if (Checkers.notNull(accessTokenHarvest, accessTokenTodoist, projectId)) {
			
			HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
			
			Project project = harvestResource.getProject(projectId);
			
			if (project != null) {
				// ProjectConfig update
				ProjectConfig projectConfig = new ProjectConfig(harvestResource, project);
				projectConfig.setTodoistProjectId(todoistProjectId);
				projectConfig.setGithubRepository(githubRepository);
				projectConfig.setGitlabRepository(gitlabRepository);
				projectConfig.updateConfig(harvestResource, project);
				
				// Project fields update
				boolean updated = harvestResource.updateProject(projectId, clientId, name, isBillable, billBy, budgetBy);
				
				if (updated) {
					log.log(Level.FINE, "UpdateProjectController proccessed successfully");
					resp.sendRedirect("/projects?id=" + projectId);
				} else {
					log.log(Level.WARNING, "Error when updating project");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				return;
				
			} else {
				log.log(Level.WARNING, "Error. Project is null");
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
	
		} else {
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
		}
		
	}

}