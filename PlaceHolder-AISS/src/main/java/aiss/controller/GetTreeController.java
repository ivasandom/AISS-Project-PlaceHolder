package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.harvest.Project;
import aiss.model.resource.HarvestResource;
import aiss.utility.ProjectConfig;

public class GetTreeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		return;
	}
		
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		
		String projectId = req.getParameter("projectId");
		String repositoryHost = req.getParameter("repositoryHost");
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		
		List<String> hosts = new ArrayList<>(Arrays.asList("github", "gitlab", "bitbucket"));
		
		if (accessTokenHarvest != null) {
			
			if (hosts.contains(repositoryHost) && repositoryHost != null) {
				HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
				Project project = harvestResource.getProject(projectId);
				ProjectConfig config = new ProjectConfig(harvestResource, project);
				
				if (repositoryHost.equals("github")) {
					String[] repository = config.getGithubRepository().split("/");
					String owner = repository[1];
					String repositoryName = repository[2];
					
				} else if (repositoryHost.equals("gitlab")) {
					String[] repository = config.getGithubRepository().split("/");
					
				} else if (repositoryHost.equals("bitbucket")) {
					String[] repository = config.getGithubRepository().split("/");
					
				}
				
				
				
			}
			
		}
		
	}
}
