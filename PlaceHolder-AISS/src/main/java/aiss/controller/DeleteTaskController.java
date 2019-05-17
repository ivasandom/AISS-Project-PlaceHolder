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

public class DeleteTaskController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DeleteTaskController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.log(Level.INFO, "Processing DeleteTaskController.");

		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		String taskId = req.getParameter("id");
		
		if (Checkers.notNull(accessTokenTodoist, taskId)) {
			
			log.log(Level.INFO, "Deleting task.");

			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			boolean deleted = todoistResource.deleteTask(taskId);
			if (deleted) {
				// Si se ha eliminado devolvemos a pagina inicio
				resp.sendRedirect("/");
				log.log(Level.FINE, "Task deleted. Forwarding to index.");

			}else {
				// Si no se ha eliminado devolvemos 404
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				log.log(Level.SEVERE, "The task with could not be deleted. Perhaps it doesn´t exists. Forwarding to index .");
				resp.sendRedirect("/error.jsp");

			}
		}
		else {
		// Si no se ha eliminado devolvemos 404
		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		log.log(Level.SEVERE, "The task with could not be added. Perhaps it doesn´t exists. Forwarding to index .");
		resp.sendRedirect("/error.jsp");
		}
		
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
}
