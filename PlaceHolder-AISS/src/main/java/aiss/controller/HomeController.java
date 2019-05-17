package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.bitbucket.BitbucketRepository;
import aiss.model.github.Repository;
import aiss.model.github.User;
import aiss.model.gitlab.GitLabRepository;
import aiss.model.harvest.Profile;
import aiss.model.harvest.Project;
import aiss.model.resource.BitbucketResource;
import aiss.model.resource.GitHubResource;
import aiss.model.resource.GitLabResource;
import aiss.model.resource.HarvestResource;
import aiss.model.resource.TodoistResource;


public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HomeController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		log.log(Level.INFO, "Processing HomeController.");

		String accessTokenGitHub = (String) req.getSession().getAttribute("GitHub-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		String accessTokenGitLab = (String) req.getSession().getAttribute("GitLab-token");
		String accessTokenBitbucket = (String) req.getSession().getAttribute("Bitbucket-token");
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		
		if (accessTokenGitHub != null) {
			
			GitHubResource githubResource = new GitHubResource(accessTokenGitHub);
			User currentUser = githubResource.getGithubUser();
			Repository[] repositories = githubResource.getMyRepositories();
			
			req.setAttribute("repositoriesGitHub", repositories);
			req.setAttribute("user", currentUser);
			
		}
		
		if (accessTokenGitLab != null) {
			
			GitLabResource gitLabResource = new GitLabResource(accessTokenGitLab);
			GitLabRepository[] repositories = gitLabResource.getMyRepos();
			
			req.setAttribute("repositoriesGitLab", repositories);
		}
		
		if (accessTokenBitbucket != null) {
			
			BitbucketResource bitbucketResource = new BitbucketResource(accessTokenBitbucket);
			BitbucketRepository[] repositories = bitbucketResource.getMyRepos();
			
			req.setAttribute("repositoriesBitbucket", repositories);
		}
		
		if (accessTokenTodoist != null) {
			
			TodoistResource todoistResource = new TodoistResource(accessTokenTodoist);
//			Project[] myProjects = todoistResource.getMyProjects();
//			req.setAttribute("myProjects", myProjects);
			
		}
		
		if (accessTokenHarvest != null) {
			HarvestResource harvestResource = new HarvestResource(accessTokenHarvest);
			
			Profile profile = harvestResource.getProfile();
			Project myProjects = harvestResource.getMyProjects();
			
//			System.out.println(myProjects.getProjects());
			req.setAttribute("myProjects", myProjects.getProjects());
			req.setAttribute("profile", profile);
		}
		
		log.log(Level.INFO, "HomeController obtained successfully. Forwarding to index");

 		req.getRequestDispatcher("index.jsp").forward(req, resp);
 		
	}
}