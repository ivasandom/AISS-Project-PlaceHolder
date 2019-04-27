package aiss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import aiss.model.resource.TodoistResource;
import aiss.model.todoist.Task;
import aiss.utility.Checkers;

public class AddTaskController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddTaskController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		String newTaskContent = req.getParameter("content");
		String newTaskProjectId = req.getParameter("projectId");
		
		boolean parametrosRecibidos = Checkers.notNull(accessTokenTodoist, newTaskContent, newTaskProjectId);
		if (parametrosRecibidos) {
			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			
			Task tarea = new Task();
			tarea.setProjectId(Long.parseLong(newTaskProjectId));
			tarea.setContent(newTaskContent);
			
			Task nuevaTarea = todoistResource.createTask(tarea);
			
			if (nuevaTarea != null) {
				// AJAX
				// https://stackoverflow.com/questions/2010990/how-do-you-return-a-json-object-from-a-java-servlet
				resp.setContentType("application/json");
				PrintWriter out = resp.getWriter();
				out.print(new JSONObject(nuevaTarea));
				out.flush();
				
			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			
		} else {
			// Si no está logueado entonces devolvemos error
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
	}



}
