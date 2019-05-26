package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.HarvestResource;
import aiss.utility.Checkers;

public class DeleteProjectController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DeleteTaskController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Redirects to login if accessTokenHarvest or accessTokenTodoist do not exist.
		if(Checkers.checkAuthenticatedOrRedirect(req.getSession(), resp)) return;
				
		log.log(Level.INFO, "Processing DeleteProjectController.");

		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String projectId = req.getParameter("id");
		

		log.log(Level.INFO, "Deleting project.");
			
		HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
		boolean deleted = harvestResource.deleteProject(projectId);
			
		if (deleted) {
				
			// Si se ha eliminado devolvemos a pagina inicio
			resp.sendRedirect("/");
			log.log(Level.FINE, "Project deleted. Forwarding to index.");

		} else {
				
			// Si no se ha eliminado devolvemos 404
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			log.log(Level.SEVERE, "The project with could not be deleted. Perhaps it doesn´t exists. Forwarding to index .");
			resp.sendRedirect("/error.jsp");

		}
	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req,resp);
	}
	
}
