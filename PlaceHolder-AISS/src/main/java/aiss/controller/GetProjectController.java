package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import aiss.model.resource.TodoistResource;
import aiss.model.todoist.Project;



public class GetProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String accessToken = (String) req.getSession().getAttribute("Todoist-token");
		
		if (accessToken != null) {
			
			Project[] projects = TodoistResource.getMyProjects(accessToken);
			

			req.setAttribute("projects", projects);
			
		}
		
		
 		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
}