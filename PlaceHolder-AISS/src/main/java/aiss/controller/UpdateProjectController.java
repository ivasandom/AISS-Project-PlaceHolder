package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.TodoistResource;

public class UpdateProjectController {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UpdateProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}

}

