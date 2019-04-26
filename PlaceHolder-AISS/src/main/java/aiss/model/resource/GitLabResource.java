package aiss.model.resource;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.gitlab.GitLabRepository;

public class GitLabResource {
	
	private final String accessToken;
	private final String BASE_URL = "https://gitlab.com/api/v4/";
	
	public GitLabResource(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public GitLabRepository getMyRepos(String idUser) {

		ClientResource cr = null;
		GitLabRepository projects = null;
		String resourceURL = BASE_URL + "/users/" + idUser + "/projects?access_token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			projects = cr.get(GitLabRepository.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the repos: " + cr.getResponse().getStatus());
		}
		
		return projects;

	}
}
