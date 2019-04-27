package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.TodoistResource;
import aiss.model.todoist.Project;
import aiss.model.todoist.TaskSimple;

public class GetProjectController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		String projectId = req.getParameter("id");
		
		if (accessTokenTodoist != null && projectId != null) {
			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
			Project[] myProjects = todoistResource.getMyProjects();
			Project project = todoistResource.getProject(projectId);
			if (project == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			TaskSimple[] tasks = todoistResource.getActiveTasks(projectId);
			
			req.setAttribute("myProjects", myProjects);
			req.setAttribute("project", project);
			req.setAttribute("projectTasks", tasks);
		}
	
		
		req.getRequestDispatcher("project.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// ajax
		doGet(req,resp);
	}
}
