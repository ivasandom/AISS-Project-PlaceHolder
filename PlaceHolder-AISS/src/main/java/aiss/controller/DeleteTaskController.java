package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.TodoistResource;

public class DeleteTaskController {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DeleteTaskController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String access_token = (String) req.getSession().getAttribute("Todoist-token");
		TodoistResource todoistResource = new TodoistResource(access_token);
		String id = req.getParameter("id");
		if (access_token != null) {
			
			boolean success = todoistResource.deleteTask(id);
			
			if (success) {
				req.setAttribute("message", "Task deleted successfully");
				log.log(Level.FINE, "Task with id=" + id + " deleted. Forwarding to index.");
			}
			else {
				req.setAttribute("message", "The task could not be deleted");
				log.log(Level.FINE, "The task with id=" + id + " could not be deleted. Perhaps it is duplicated. Forwarding to index.");
			}
		
		}req.getRequestDispatcher("index.jsp").forward(req, resp);
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
}
