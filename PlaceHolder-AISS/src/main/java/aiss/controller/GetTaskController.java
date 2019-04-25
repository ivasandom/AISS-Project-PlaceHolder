package aiss.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.TodoistResource;
import aiss.model.todoist.Event;

public class GetTaskController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetTaskController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String access_token = (String) req.getSession().getAttribute("Todoist-token");
		TodoistResource todoistResource = new TodoistResource(access_token);
		String id = req.getParameter("id");
		if (access_token != null) {
			
			Event task = todoistResource.getTask(id);
			req.setAttribute("task", task);
		}
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
}
