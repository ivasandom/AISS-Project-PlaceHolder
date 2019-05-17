package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.TodoistResource;
import aiss.model.todoist.Project;
import aiss.utility.Checkers;

public class AddProjectController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.log(Level.INFO, "Processing AddProjectController.");

		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		if (accessTokenTodoist != null) {
			
			log.log(Level.INFO, "Adding project.");

			req.getRequestDispatcher("projects-create.jsp");
		} else {
			resp.sendRedirect("/");
		}
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		String newProjectName = req.getParameter("name");
		
		if (Checkers.notNull(accessTokenTodoist, newProjectName)) {
			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			Project nuevoProject = todoistResource.createProject(newProjectName);
			
			if (nuevoProject != null) {
				resp.sendRedirect("/projects?id="+nuevoProject.getId());
				log.log(Level.FINE, "Project added. Forwarding to new project page.");

			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				log.log(Level.SEVERE, "The project with could not be added. Perhaps it is duplicated. Forwarding to index .");
				resp.sendRedirect("/error.jsp");

			}
			
		} else {
			// Si no está logueado entonces devolvemos error
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			resp.sendRedirect("/error.jsp");

		}
		
	}

}
