package aiss.model.resource;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.github.Blob;
import aiss.model.github.Branch;
import aiss.model.github.Repository;
import aiss.model.github.RepositoryTree;
import aiss.model.github.User;

public class GitHubResource {
	
	private final String accessToken;
	private final String BASE_URL = "https://api.github.com"; 
	
	public GitHubResource(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public Repository[] getMyRepositories() {
		
		ClientResource cr = null;
		Repository[] repositories = null;
		String resourceURL = BASE_URL + "/user/repos?access_token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			repositories = cr.get(Repository[].class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving repositories.");
		}
		
		return repositories;
		
	}
	
	public User getGithubUser() {
		
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
	
	public Branch getMasterBranch(String owner, String repo) {
		
		ClientResource cr = null;
		Branch master = null;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/branches/master?access_token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			master = cr.get(Branch.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving master branch.");
		}
		
		return master;

	}
	
	public RepositoryTree getRepositoryTree(String owner, String repo) {
		
		System.out.println("Datos" +  owner + repo);
		String treeSha = this.getMasterBranch(owner, repo).getCommit().getSha();
		
		ClientResource cr = null;
		RepositoryTree tree = null;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/git/trees/" + treeSha + "?recursive=1&access_token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			tree = cr.get(RepositoryTree.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving tree.");
		}
		
		return tree;
	}
	
	public Blob getBlob(String urlBlob) {
		/**
		 * Carga un archivo codificado en base64
		 * despues se decodifica en javascript y para
		 * ser mostrado en el editor
		 * 
		 * pensado para utilizarse peticiones ajax......
		 */
		
		ClientResource cr = null;
		Blob blob = null;
		String resourceURL = urlBlob + "?access_token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			blob = cr.get(Blob.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving blob");
		}
		
		return blob;
	}
}
