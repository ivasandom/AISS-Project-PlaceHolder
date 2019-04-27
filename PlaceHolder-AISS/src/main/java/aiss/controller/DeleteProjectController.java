package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.TodoistResource;
import aiss.utility.Checkers;

public class DeleteProjectController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DeleteTaskController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.log(Level.INFO, "Processing DeleteProjectController.");

		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		String projectId = req.getParameter("id");
		
		if (Checkers.notNull(accessTokenTodoist, projectId)) {
			
			log.log(Level.INFO, "Deleting project.");
			
			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			boolean deleted = todoistResource.deleteProject(projectId);
			if (deleted) {
				// Si se ha eliminado devolvemos a pagina inicio
				resp.sendRedirect("/");
				log.log(Level.FINE, "Project deleted. Forwarding to index.");

			} else {
				// Si no se ha eliminado devolvemos 404
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				log.log(Level.SEVERE, "The project with could not be deleted. Perhaps it doesn´t exists. Forwarding to index .");

			}
		} else {
			// Si no se ha eliminado devolvemos 404
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			log.log(Level.WARNING, "Error when deleting the project.");

		}
		
		
		
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
}
