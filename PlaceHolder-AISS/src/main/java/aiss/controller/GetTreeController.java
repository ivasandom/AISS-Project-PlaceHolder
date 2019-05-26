package aiss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import aiss.model.harvest.Project;
import aiss.model.resource.GitHubResource;
import aiss.model.resource.GitLabResource;
import aiss.model.resource.HarvestResource;
import aiss.utility.ProjectConfig;

public class GetTreeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//		return;
		doPost(req, resp);
	}
		
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		log.log(Level.INFO, "Processing GetTreeController.");
		
		String projectId = req.getParameter("projectId");
		String repositoryHost = req.getParameter("repositoryHost");
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		
		log.log(Level.FINE, "Access Token Harvest: " + accessTokenHarvest);
		
		String accessTokenGithub = (String) req.getSession().getAttribute("GitHub-token");
		String accessTokenGitlab = (String) req.getSession().getAttribute("GitLab-token");
		
		log.log(Level.FINE, "Access Token GitHub: " + accessTokenGithub);
		log.log(Level.FINE, "Access Token GitLab: " + accessTokenGitlab);
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		List<String> hosts = new ArrayList<>(Arrays.asList("GitHub", "GitLab"));
		
		if (accessTokenHarvest != null) {
			if (hosts.contains(repositoryHost) && repositoryHost != null) {
				HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
				Project project = harvestResource.getProject(projectId);
				ProjectConfig config = new ProjectConfig(harvestResource, project);
				
				if (repositoryHost.equals("GitHub")) {
					log.log(Level.INFO, "Getting GitHub repository.");
					if ((String) req.getSession().getAttribute("GitHub-token") == null) {
						log.log(Level.WARNING, "Error. GitHub access token is null.");
						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
						resp.sendRedirect("/error.jsp");
						return;
					}
					GitHubResource githubResource = new GitHubResource(accessTokenGithub);
					
					String[] repository = config.getGithubRepository().split("/");
					String owner = repository[1];
					String repositoryName = repository[2];
					
					JSONObject jsonResponse = new JSONObject();
					aiss.model.github.RepositoryTree repositoryTree = githubResource.getRepositoryTree(owner, repositoryName);
					jsonResponse.put("sha", repositoryTree.getSha());
					jsonResponse.put("tree", repositoryTree.getTree());
					
					out.print(jsonResponse);
					out.flush();
					
				} else if (repositoryHost.equals("GitLab")) {
					
					log.log(Level.INFO, "Getting GitHub repository.");
					if ((String) req.getSession().getAttribute("GitLab-token") == null) {
						log.log(Level.WARNING, "Error. GitLab access token is null.");
						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
						resp.sendRedirect("/error.jsp");
						return;
					}
					GitLabResource gitlabResource = new GitLabResource(accessTokenGitlab);
					
					String[] repository = config.getGitlabRepository().split("/");
					String owner = repository[1];
					String repositoryName = repository[2];
					
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("tree", gitlabResource.getRepositoryTree(owner, repositoryName));
					
					log.log(Level.FINE, "GetRepositoryTreeController processed successfully.");
					
					out.print(jsonResponse);
					out.flush();
				} 
				
				
			}
			
		}
		
	}
}
