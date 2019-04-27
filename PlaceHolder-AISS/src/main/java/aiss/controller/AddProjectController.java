package aiss.controller;

import java.io.IOException;
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
		
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		if (accessTokenTodoist != null) {
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
				// Si lo hemos creado redirigimos a la página del nuevo proyecto
				resp.sendRedirect("/projects?id="+nuevoProject.getId());
			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			
		} else {
			// Si no está logueado entonces devolvemos error
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
	}

}
