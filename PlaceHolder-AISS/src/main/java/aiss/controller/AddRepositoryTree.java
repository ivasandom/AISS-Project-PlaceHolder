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

public class AddRepositoryTree extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return;
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		log.log(Level.INFO, "Processing AddRepositoryTreeController.");
		
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		log.log(Level.FINE, "Access Token Harvest: " + accessTokenHarvest);
		log.log(Level.FINE, "Access Token Todoist: " + accessTokenTodoist);
		
		String accessTokenGitHub = (String) req.getSession().getAttribute("GitHub-token");
		String accessTokenGitLab = (String) req.getSession().getAttribute("GitLab-token");
		
		log.log(Level.FINE, "Access Token GitHub: " + accessTokenGitHub);
		log.log(Level.FINE, "Access Token GitLab: " + accessTokenGitLab);
		
		JSONObject requestBody = new JSONObject(IOUtils.toString(req.getReader()));
		
		String gitHost = requestBody.getString("githost");
		String repositoryOwner = requestBody.getString("owner");
		String repositoryName = requestBody.getString("repo");
		
		String startedTime = requestBody.getString("startedTime");
		String endedTime = requestBody.getString("endedTime");
		String commitMessage = requestBody.getString("commitMessage");
		String taskId = requestBody.getString("taskId");
		String projectId = requestBody.getString("projectId");
		
		String tree = requestBody.get("tree").toString();
		
		
		TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
		HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		JSONObject response = new JSONObject();
		
		aiss.model.todoist.Task task = todoistResource.getTask(taskId);
		
		List<String> availableGithosts = new ArrayList<>(Arrays.asList("GitHub", "GitLab", "Bitbucket"));
		
		if (Checkers.notNull(accessTokenHarvest, accessTokenTodoist) && availableGithosts.contains(gitHost)) {
			
			boolean createdTimeEntry = harvestResource.createTimeEntry(projectId, task.getId().toString(), task.getConfig().getTaskParent(),
					LocalDate.now().toString(), startedTime, endedTime);
			
			if (gitHost.equals("GitHub")) {
				log.log(Level.INFO, "Adding GitHub repository.");
				GitHubResource githubResource = new GitHubResource(accessTokenGitHub);
				String treeSha = githubResource.createRepositoryTree(repositoryOwner, repositoryName, tree);
				String lastCommitSha = githubResource.getMasterBranch(repositoryOwner, repositoryName).getCommit().getSha();
				String commitSha = githubResource.createCommit(repositoryOwner, repositoryName, treeSha, commitMessage, lastCommitSha);
				
				githubResource.updateMasterReference(repositoryOwner, repositoryName, commitSha);
				
				response.put("commitUrl", "https://github.com/" + repositoryOwner + "/" + repositoryName + "/commit/" + commitSha);
				
			} else if (gitHost.equals("GitLab")) {
				
				log.log(Level.INFO, "Adding GitLab repository.");
				
				GitLabResource gitlabResource = new GitLabResource(accessTokenGitLab);
				aiss.model.gitlab.RepositoryTree newRepositoryTree = new aiss.model.gitlab.RepositoryTree();
				List<aiss.model.github.Tree> trees = new ArrayList<>();
				
				// newRepositoryTree.(baseTree);
				// newRepositoryTree.setTree(trees);
				
				// gitlabResource.createRepositoryTree(repositoryOwner, repositoryName, newRepositoryTree);
				
			} 
			log.log(Level.FINE, "AddRepositoryTreeController processed successfully.");
			
			out.print(response);
			out.flush();
			
	
		} else {
			log.log(Level.WARNING, "Error when adding a repository tree.");
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			resp.sendRedirect("/error.jsp");
			
		}
		
	}

}
