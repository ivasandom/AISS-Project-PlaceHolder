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
		
		log.log(Level.INFO, "Processing AddProjectController.");

		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		if (Checkers.notNull(accessTokenHarvest, accessTokenTodoist)) {
			HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			
			req.setAttribute("clients", harvestResource.getClients());
			req.setAttribute("todoistProjects", todoistResource.getMyProjects());
			
			// for header.jsp
			req.setAttribute("myProjects", harvestResource.getMyProjects().getProjects());
			req.setAttribute("profile", harvestResource.getProfile());
			
			req.getRequestDispatcher("/projects-create.jsp").forward(req, resp);
			
		} else {
			resp.sendRedirect("/");
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		Long clientId = null;
		String name = null;
		boolean isBillable = false;;
		String billBy = null;
		String budgetBy = null;
		String todoistProjectId = null;
		String githubRepository = null;
		String gitlabRepository = null;
		String bitbucketRepository = null;
		
		try {
			
			clientId = Long.valueOf(req.getParameter("client_id"));
			name = req.getParameter("name");
			isBillable = Boolean.valueOf(req.getParameter("is_billable"));
			billBy = req.getParameter("bill_by");
			budgetBy = req.getParameter("budget_by");
			
			// Enlazar
			todoistProjectId = req.getParameter("todoistProject");
			githubRepository = req.getParameter("githubRepository");
			gitlabRepository = req.getParameter("gitlabRepository");
			bitbucketRepository = req.getParameter("bitbucketRepository");
		} catch (Exception e) {
			
		}
		
		if (Checkers.notNull(accessTokenHarvest)) {
			HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			
			Project nuevoProject = harvestResource.createProject(clientId, name,  isBillable, billBy, budgetBy);
			// Creamos la configuracion JSON del proyecto
			ProjectConfig config = new ProjectConfig(harvestResource, nuevoProject);
			
			if (todoistProjectId == null || todoistProjectId.equals("new_project")) {
				aiss.model.todoist.Project todoistNewProject = todoistResource.createProject(name);
				config.setTodoistProjectId(todoistNewProject.getId().toString());
			} else {
				// aiss.model.todoist.Project todoistProject = todoistResource.getProject(todoistProjectId);
				config.setTodoistProjectId(todoistProjectId);
			}
			
			if (githubRepository != null && githubRepository != "") config.setGithubRepository(githubRepository);
			if (gitlabRepository != null && gitlabRepository != "") config.setGitlabRepository(gitlabRepository);
			if (bitbucketRepository != null && bitbucketRepository != "") config.setBitbucketRepository(bitbucketRepository);
			
			config.updateConfig(harvestResource, nuevoProject);
			
			if (nuevoProject != null) {
				resp.sendRedirect("/projects?id="+nuevoProject.getId());
				log.log(Level.FINE, "Project added. Forwarding to new project page.");

			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				log.log(Level.SEVERE, "The project with could not be added. Perhaps it is duplicated.");
				resp.sendRedirect("/error.jsp");

			}
			
		} else {
			// Si no está logueado entonces devolvemos error
			log.log(Level.WARNING, "Error. User is not logued.");
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			resp.sendRedirect("/error.jsp");

		}
		
	}

}
