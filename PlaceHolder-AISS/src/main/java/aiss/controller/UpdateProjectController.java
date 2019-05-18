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

public class UpdateProjectController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UpdateProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.log(Level.INFO, "Processing UpdateProjectController GET");
		
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String id = req.getParameter("id");
		
		
		if (accessTokenHarvest != null) {
			
			if (id != null) {
				HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
				Project project = harvestResource.getProject(id);
				List<Client_> clients = harvestResource.getClients();
				
				req.setAttribute("project", project);
				req.setAttribute("clients", clients);
				req.getRequestDispatcher("/project-edit.jsp").forward(req, resp);
				
			} else {
				// TODO raise HTTP404
			}
			
		} else {
			// TODO redirect to login
		}
				
		
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {


		log.log(Level.INFO, "Processing UpdateProjectController.");

		String id = req.getParameter("id");
		String access_token = (String) req.getSession().getAttribute("Todoist-token");
		String name = req.getParameter("name");
		
		TodoistResource todoistResource = new TodoistResource(access_token);
		
		if (access_token != null) {
			
			log.log(Level.INFO, "Updating project.");

			boolean success = todoistResource.updateProject(id, name);
			
			if (success) {
				req.setAttribute("message", "Project updated successfully");
				log.log(Level.FINE, "Project with id=" + id + " updated. Forwarding to index.");
			}
			else {
				req.setAttribute("message", "The project could not be updated");
				log.log(Level.FINE, "The project with id=" + id + " could not be updated. Perhaps it is duplicated. Forwarding to index .");
			}
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
		
	}

}

