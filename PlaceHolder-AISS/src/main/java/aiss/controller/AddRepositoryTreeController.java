package aiss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import aiss.model.resource.GitHubResource;
import aiss.model.resource.GitLabResource;
import aiss.model.resource.HarvestResource;
import aiss.model.resource.TodoistResource;
import aiss.utility.Checkers;

public class AddRepositoryTreeController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.log(Level.SEVERE, "AddRepositoryTreeController Method not allowed");
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		req.getRequestDispatcher("/error.jsp").forward(req, resp);
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		log.log(Level.INFO, "Processing AddRepositoryTreeController.");
		
		// Response ContentType = application/json
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		JSONObject response = new JSONObject();
		JSONObject requestBody = new JSONObject(IOUtils.toString(req.getReader()));
		
		// OAuth2 Tokens
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");

		String accessTokenGitHub = (String) req.getSession().getAttribute("GitHub-token");
		String accessTokenGitLab = (String) req.getSession().getAttribute("GitLab-token");
		
		log.log(Level.FINE, "Access Token Harvest: " + accessTokenHarvest);
		log.log(Level.FINE, "Access Token Todoist: " + accessTokenTodoist);
		
		log.log(Level.FINE, "Access Token GitHub: " + accessTokenGitHub);
		log.log(Level.FINE, "Access Token GitLab: " + accessTokenGitLab);
		
		// Request Content Body (Request Content-Type = application/json)
		String gitHost = requestBody.getString("githost");
		String repositoryOwner = requestBody.getString("owner");
		String repositoryName = requestBody.getString("repo");
		
		String startedTime = requestBody.getString("startedTime");
		String endedTime = requestBody.getString("endedTime");
		String commitMessage = requestBody.getString("commitMessage");
		String taskId = requestBody.getString("taskId");
		String projectId = requestBody.getString("projectId");
		
		String tree = requestBody.get("tree").toString();
		
		List<String> availableGithosts = new ArrayList<>(Arrays.asList("GitHub", "GitLab"));
		
		if (Checkers.notNull(accessTokenHarvest, accessTokenTodoist)) {
			
			if (availableGithosts.contains(gitHost)) {
				
				// Resources
				TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
				HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
				
				aiss.model.todoist.Task task = todoistResource.getTask(taskId);
				
				// Create time entry
				harvestResource.createTimeEntry(projectId, task.getId().toString(), task.getConfig().getTaskParent(),
						LocalDate.now().toString(), startedTime, endedTime);
				
				if (gitHost.equals("GitHub")) {
					
					if (accessTokenGitHub != null) {
					
						log.log(Level.INFO, "Creating GitHub repository commit...");
						GitHubResource githubResource = new GitHubResource(accessTokenGitHub);
						String treeSha = githubResource.createRepositoryTree(repositoryOwner, repositoryName, tree);
						String lastCommitSha = githubResource.getMasterBranch(repositoryOwner, repositoryName).getCommit().getSha();
						String commitSha = githubResource.createCommit(repositoryOwner, repositoryName, treeSha, commitMessage, lastCommitSha);
						
						githubResource.updateMasterReference(repositoryOwner, repositoryName, commitSha);
						
						response.put("commitUrl", "https://github.com/" + repositoryOwner + "/" + repositoryName + "/commit/" + commitSha);
						
					} else {
						
						log.log(Level.WARNING, "You must be authenticated in Github to commit");
						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
						response.put("error", "You must be authenticated in Github to commit");
						
					}
					
				} else if (gitHost.equals("GitLab")) {
					
					if (accessTokenGitLab != null) {
						
						log.log(Level.INFO, "Creating GitLab repository commit...");
						
						GitLabResource gitlabResource = new GitLabResource(accessTokenGitLab);
						aiss.model.gitlab.Commit commit = gitlabResource.createCommit(repositoryOwner, repositoryName, tree);

						response.put("commitUrl", "https://gitlab.com/josgamdia1/repositorio-gitlab/commit/" + commit.getId());
						
					} else {
						
						log.log(Level.WARNING, "You must be authenticated in GitLab to commit");
						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
						response.put("error", "You must be authenticated in GitLab to commit");
						
					}
					
				} 
				
				log.log(Level.FINE, "AddRepositoryTreeController processed successfully.");
				
			} else {
				
				log.log(Level.WARNING, "Githost not valid.");
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				response.put("error", "Githost not valid.");
				
			}			
			
		} else {
			
			log.log(Level.WARNING, "You must be authenticated in Harvest and Todoist");
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			response.put("error", "You must be authenticated in Harvest and Todoist");
			
		}
		
		out.print(response);
		out.flush();
		
	}

}
