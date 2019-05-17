package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.harvest.Project_;
import aiss.model.resource.HarvestResource;
import aiss.utility.Checkers;

public class AddProjectController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.log(Level.INFO, "Processing AddProjectController.");

		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		
		if (accessTokenHarvest != null) {
			HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
			req.setAttribute("clients", harvestResource.getClients());
			req.getRequestDispatcher("/projects-create.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/");
		}
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		
		Long clientId = null;
		String name = null;
		boolean isBillable = false;;
		String billBy = null;
		String budgetBy = null;
		
		try {
			clientId = new Long(req.getParameter("client_id"));
			name = req.getParameter("name");
			isBillable = new Boolean(req.getParameter("is_billable"));
			billBy = req.getParameter("bill_by");
			budgetBy = req.getParameter("budget_by");
		} catch (Exception e) {
			
		}
		
		if (Checkers.notNull(accessTokenHarvest)) {
			HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
			Project_ nuevoProject = harvestResource.createProject(clientId, name,  isBillable, billBy, budgetBy);
			
			if (nuevoProject != null) {
				resp.sendRedirect("/projects?id="+nuevoProject.getId());
				log.log(Level.FINE, "Project added. Forwarding to new project page.");

			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				log.log(Level.SEVERE, "The project with could not be added. Perhaps it is duplicated. Forwarding to index .");

			}
			
		} else {
			// Si no está logueado entonces devolvemos error
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			log.log(Level.WARNING, "Error when adding the project.");

		}
		
	}

}
