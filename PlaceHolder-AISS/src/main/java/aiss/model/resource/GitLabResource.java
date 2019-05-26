package aiss.model.resource;

import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.gitlab.Commit;
//import aiss.model.gitlab.User;
import aiss.model.gitlab.GitLabRepository;
import aiss.model.gitlab.RepositoryTree;

public class GitLabResource {
	
	private final String accessToken;
	private final String BASE_URL = "https://gitlab.com/api/v4";
	
	public GitLabResource(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public GitLabRepository[] getMyRepos() {

		ClientResource cr = null;
		GitLabRepository[] projects = null;
		String resourceURL = BASE_URL + "/projects?access_token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			projects = cr.get(GitLabRepository[].class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the repos: " + cr.getResponse().getStatus());
		}
		
		return projects;

	}
	
	
	public Commit createCommit(String owner, String repo, String actionCommit) {
		
		Commit commit = null;
		String path = owner + "%2F" + repo;
		String resourceURL = BASE_URL + "/projects/" + path + "/repository/commits?access_token=" + accessToken;;
	
		ClientResource cr = new ClientResource(resourceURL);
				
		try {
			Representation rep = cr.post(new JsonRepresentation(actionCommit), MediaType.APPLICATION_JSON);
			commit = cr.toObject(rep, Commit.class);
		} catch (ResourceException re){
			System.err.println("Error when creating commit" + cr.getResponse().getStatus());
		}
		
		return commit;

	}
	
	public RepositoryTree[] getRepositoryTree(String owner, String repo) {
		/*
		 * gitlab.com docs...
		 * If using namespaced API calls, make sure that the NAMESPACE/PROJECT_NAME is URL-encoded.
		 * For example, / is represented by %2F:
		 */
		
		RepositoryTree[] tree = null;
		String path = owner + "%2F" + repo;
		String resourceURL = BASE_URL + "/projects/" + path + "/repository/tree" + "?recursive=1&per_page=1000000&access_token=" + this.accessToken;
		ClientResource cr = new ClientResource(resourceURL);
		System.out.println(resourceURL);
		
		try {
			tree = cr.get(RepositoryTree[].class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving tree.");
		}
		
		return tree;
	} 
}
