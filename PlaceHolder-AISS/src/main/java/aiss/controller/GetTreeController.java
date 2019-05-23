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
import aiss.model.resource.BitbucketResource;
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
		log.log(Level.INFO, "Get repository tree..");
		
		String projectId = req.getParameter("projectId");
		String repositoryHost = req.getParameter("repositoryHost");
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		
		String accessTokenGithub = (String) req.getSession().getAttribute("GitHub-token");
		String accessTokenGitlab = (String) req.getSession().getAttribute("GitLab-token");
		String accessTokenBitbucket = (String) req.getSession().getAttribute("Bitbucket-token");
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		List<String> hosts = new ArrayList<>(Arrays.asList("GitHub", "GitLab", "Bitbucket"));
		
		if (accessTokenHarvest != null) {
			if (hosts.contains(repositoryHost) && repositoryHost != null) {
				HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
				Project project = harvestResource.getProject(projectId);
				ProjectConfig config = new ProjectConfig(harvestResource, project);
				
				if (repositoryHost.equals("GitHub")) {
					if ((String) req.getSession().getAttribute("GitHub-token") == null) {
						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
						return;
					}
					GitHubResource githubResource = new GitHubResource(accessTokenGithub);
					
					String[] repository = config.getGithubRepository().split("/");
					String owner = repository[1];
					String repositoryName = repository[2];
					
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("tree", githubResource.getRepositoryTree(owner, repositoryName).getTree());
					
					out.print(jsonResponse);
					out.flush();
					
				} else if (repositoryHost.equals("GitLab")) {
					if ((String) req.getSession().getAttribute("GitLab-token") == null) {
						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
						return;
					}
					GitLabResource gitlabResource = new GitLabResource(accessTokenGitlab);
					
					String[] repository = config.getGitlabRepository().split("/");
					String owner = repository[1];
					String repositoryName = repository[2];
					
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("tree", gitlabResource.getRepositoryTree(owner, repositoryName));
					
					out.print(jsonResponse);
					out.flush();
					
				} else if (repositoryHost.equals("bitbucket")) {
					BitbucketResource bitbucketResource = new BitbucketResource(accessTokenBitbucket);
					
					String[] repository = config.getBitbucketRepository().split("/");
					String owner = repository[1];
					String repositoryName = repository[2];
					
					JSONObject jsonResponse = new JSONObject();
					//jsonResponse.append("tree", bitbucketResource.getRepositoryTree(owner, repositoryName));
					out.print(jsonResponse);
					out.flush();
				}
				
				
				
			}
			
		}
		
	}
}
