package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.TodoistResource;
import aiss.model.todoist.Project;



public class GetProjectsController extends HttpServlet {
	private static final Logger log = Logger.getLogger(GetProjectsController.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		log.log(Level.INFO, "Processing GetProjectsController.");

		String accessToken=(String)req.getSession().getAttribute("Todoist-token");
		
		if(accessToken!=null && !"".equals(accessToken)){
			
			TodoistResource tdResource=new TodoistResource(accessToken);
			Project[] projects=tdResource.getMyProjects();
			
			if(projects!=null){
				log.log(Level.INFO, "Getting projects.");

				req.setAttribute("files", projects);
				req.getRequestDispatcher("/index.jsp").forward(req,resp);
			}else{
				log.info("The projects returned are null... probably your token has experied. Redirecting to OAuth servlet.");
				req.getRequestDispatcher("/AuthController/Todoist").forward(req,resp);
			}
		}else{
			log.info("Trying to acces to todoist without an acces token, redirecting to OAuth servlet");
			req.getRequestDispatcher("/AuthController/Todoist").forward(req,resp);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
}
