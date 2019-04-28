package aiss.model.resource;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.github.User;
import aiss.model.gitlab.GitLabRepository;

public class GitLabResource {
	
	private final String accessToken;
	private final String BASE_URL = "https://gitlab.com/api/v4/";
	
	public GitLabResource(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public GitLabRepository[] getMyRepos() {

		ClientResource cr = null;
		GitLabRepository[] projects = null;
		String resourceURL = BASE_URL + "/users/projects?access_token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			projects = cr.get(GitLabRepository[].class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the repos: " + cr.getResponse().getStatus());
		}
		
		return projects;

	}
	
	public User getGitLabUser() {
		
		ClientResource cr = null;
		User user = null;
		String resourceURL = BASE_URL + "/user?access_token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			user = cr.get(User.class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving authenticated user.");
		}
		
		return user;
	}
}
