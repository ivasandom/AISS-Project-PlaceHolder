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

import org.apache.commons.io.IOUtils;

import aiss.model.resource.BitbucketResource;
import aiss.model.resource.GitHubResource;
import aiss.model.resource.GitLabResource;
import aiss.utility.Checkers;

public class AddRepositoryTree extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddProjectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return;
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String accessTokenHarvest = (String) req.getSession().getAttribute("Harvest-token");
		String accessTokenTodoist = (String) req.getSession().getAttribute("Todoist-token");
		
		String accessTokenGitHub = (String) req.getSession().getAttribute("GitHub-token");
		String accessTokenGitLab = (String) req.getSession().getAttribute("GitLab-token");
		String accessTokenBitbucket = (String) req.getSession().getAttribute("Bitbucket-token");
		
		String gitHost = req.getParameter("githost");
		String baseTree = req.getParameter("baseTree");
		String repositoryOwner = req.getParameter("owner");
		String repositoryName = req.getParameter("repo");
		
		gitHost = "GitHub";
		baseTree = "adasda";
		repositoryOwner = "diazdevs";
		repositoryName = "prueba";
		
		
		List<String> availableGithosts = new ArrayList<>(Arrays.asList("GitHub", "GitLab", "Bitbucket"));
		
		if (Checkers.notNull(accessTokenHarvest, accessTokenTodoist, baseTree) && availableGithosts.contains(gitHost)) {
			
			if (gitHost.equals("GitHub")) {
				
				GitHubResource githubResource = new GitHubResource(accessTokenGitHub);
				
				githubResource.createRepositoryTree(repositoryOwner, repositoryName, IOUtils.toString(req.getReader()));				
				
			} else if (gitHost.equals("GitLab")) {

				GitLabResource gitlabResource = new GitLabResource(accessTokenGitLab);
				aiss.model.gitlab.RepositoryTree newRepositoryTree = new aiss.model.gitlab.RepositoryTree();
				List<aiss.model.github.Tree> trees = new ArrayList<>();
				
				// newRepositoryTree.(baseTree);
				// newRepositoryTree.setTree(trees);
				
				// gitlabResource.createRepositoryTree(repositoryOwner, repositoryName, newRepositoryTree);
				
			} else {
				
				BitbucketResource bitbucketResource = new BitbucketResource(accessTokenBitbucket);
				
				// TODO crear los modelos necesarios
			
			}
			
			
	
			
		} else {
			
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			
		}
		
	}

}
