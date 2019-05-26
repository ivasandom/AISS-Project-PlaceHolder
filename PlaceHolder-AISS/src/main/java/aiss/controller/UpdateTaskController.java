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

public class UpdateTaskController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UpdateTaskController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		log.log(Level.INFO, "Processing UpdateTaskController.");

		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		String idTask = req.getParameter("taskId");
		String newContent = req.getParameter("content");
		
		log.log(Level.FINE, "Access Token Todoist: " + accessTokenTodoist);
		log.log(Level.FINE, "Id of the task : "+idTask);
		log.log(Level.FINE, "New content : "+newContent);
		
		if (Checkers.notNull(accessTokenTodoist, idTask, newContent)) {
			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			boolean updated = todoistResource.updateTask(idTask, newContent);
			
			if (updated) {
				
			} else {
				log.log(Level.WARNING, "Error when updating task");
				resp.sendError(HttpServletResponse.SC_NOT_MODIFIED);
				resp.sendRedirect("/error.jsp");
			}
			
		} else {
			log.log(Level.WARNING, "Error. A parameter is null.");
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			resp.sendRedirect("/error.jsp");
		}
		
	}
}
