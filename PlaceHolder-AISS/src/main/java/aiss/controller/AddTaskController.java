package aiss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import aiss.model.resource.TodoistResource;
import aiss.model.todoist.Task;
import aiss.model.todoist.TaskSimple;
import aiss.utility.Checkers;
import aiss.utility.TaskConfig;

public class AddTaskController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddTaskController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.log(Level.SEVERE, "AddTaskController Method not allowed");
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		req.getRequestDispatcher("/error.jsp").forward(req, resp);
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
					
		log.log(Level.INFO, "Proccessing AddTaskController.");
		
		// Response ContentType = application/json
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		JSONObject response = new JSONObject();
		
		// OAuth2 Tokens
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		// Form Data
		String newTaskContent = req.getParameter("content");
		String newTaskProjectId = req.getParameter("projectId");
		String newTaskAssignment = req.getParameter("assignment");
		
		boolean parametrosRecibidos = Checkers.notNull(accessTokenTodoist, newTaskContent, newTaskProjectId, newTaskAssignment);
		
		if (accessTokenTodoist != null) {
			
			if (parametrosRecibidos) {
				
				log.log(Level.INFO, "Adding task.");

				TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
				
				TaskSimple tarea = new TaskSimple();
				TaskConfig taskConfig = new TaskConfig(newTaskContent, newTaskAssignment);
				tarea.setProjectId(Long.parseLong(newTaskProjectId));
				tarea.setContent(new JSONObject(taskConfig).toString());
				
				Task nuevaTarea = todoistResource.createTask(tarea);
				
				if (nuevaTarea != null) {
					
					log.log(Level.FINE, "Task added.");
					
					// https://stackoverflow.com/questions/2010990/how-do-you-return-a-json-object-from-a-java-servlet
					resp.setContentType("application/json");
					out.print(new JSONObject(nuevaTarea));
					out.flush();
			
					return;
					
				} else {
					
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
					log.log(Level.SEVERE, "The task with could not be added. Perhaps it is duplicated. Forwarding to index .");
					response.put("error", "The task with could not be added. Perhaps it is duplicated. Forwarding to index .");

				}
				
			} else {
				
				log.log(Level.WARNING, "Invalid form data");
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				response.put("error", "Invalid form data");
				
			}
			
		} else {
			
			log.log(Level.WARNING, "You must be authenticated in Todoist");
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			response.put("error", "You must be authenticated in Todoist");
			
		}
		
		out.print(response);
		out.flush();
		
	}

}
