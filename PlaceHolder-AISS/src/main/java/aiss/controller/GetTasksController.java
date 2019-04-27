package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.TodoistResource;
import aiss.model.todoist.TaskSimple;

public class GetTasksController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetTasksController.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		log.log(Level.INFO, "Processing GetTasksController.");

		String accessToken=(String)req.getSession().getAttribute("Todoist-token");
		String idProject = req.getParameter("parentProjectId");
		
		if(accessToken!=null && !"".equals(accessToken)){
			log.log(Level.INFO, "Getting tasks");

			TodoistResource tdResource=new TodoistResource(accessToken);
			TaskSimple[] tasks=tdResource.getActiveTasks(idProject);
			if(tasks!=null){
				req.setAttribute("files", tasks);
				
				log.log(Level.INFO, "Tasks obtained successfully. Forwarding to index");

				req.getRequestDispatcher("/index.jsp").forward(req,resp);
			}else{
				log.info("The tasks returned are null... probably your token has experied. Redirecting to OAuth servlet.");
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
