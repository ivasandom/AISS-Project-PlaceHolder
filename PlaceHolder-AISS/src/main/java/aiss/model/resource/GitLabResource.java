package aiss.model.resource;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.gitLab.GitLabRepository;

public class GitLabResource {
	
	public GitLabRepository getMyRepos(String idUser) {

		ClientResource cr = null;
		GitLabRepository projects = null;
		try {
			cr = new ClientResource("https://gitlab.com/api/v4//users"+"/"+idUser+"/projects");
			projects = cr.get(GitLabRepository.class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the repos: " + cr.getResponse().getStatus());
		}
		return projects;

	}
}
