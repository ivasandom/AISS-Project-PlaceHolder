package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.TodoistResource;

public class UpdateTaskController {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UpdateTaskController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.log(Level.INFO, "Processing UpdateTaskController.");

		String id = req.getParameter("id");
		String access_token = (String) req.getSession().getAttribute("Todoist-token");
		String name = req.getParameter("name");
		
		TodoistResource todoistResource = new TodoistResource(access_token);
		
		if (access_token != null) {
			log.log(Level.INFO, "Updating task.");

			boolean success = todoistResource.updateTask(name, id);
			
			if (success) {
				req.setAttribute("message", "Task updated successfully");
				log.log(Level.FINE, "Task with id=" + id + " updated. Forwarding to index.");
			}
			else {
				req.setAttribute("message", "The task could not be updated");
				log.log(Level.FINE, "The task with id=" + id + " could not be updated. Perhaps it is duplicated. Forwarding to index .");
			}
		req.getRequestDispatcher("projects.jsp").forward(req, resp);
		}
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
}
