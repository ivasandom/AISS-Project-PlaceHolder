package aiss.controller;

import java.io.IOException;
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
		
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		String taskId = req.getParameter("id");
		
		if (Checkers.notNull(accessTokenTodoist, taskId)) {
			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			boolean deleted = todoistResource.deleteTask(taskId);
			if (deleted) {
				// Si se ha eliminado devolvemos a pagina inicio
				resp.sendRedirect("/");
			}
		}
		
		// Si no se ha eliminado devolvemos 404
		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
}
