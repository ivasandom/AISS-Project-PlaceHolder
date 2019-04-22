package aiss.model.resource;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.gitHub.Blob;
import aiss.model.gitHub.Branch;
import aiss.model.gitHub.Repository;
import aiss.model.gitHub.RepositoryTree;
import aiss.model.gitHub.User;

public class GitHubResource {
	
	private static final String BASE_URL = "https://api.github.com"; 
	
	public static Repository[] getMyRepositories(String accessToken) {
		
		ClientResource cr = null;
		Repository[] repositories = null;
		
		try {
			String url = BASE_URL + "/user/repos?access_token=" + accessToken;
			cr = new ClientResource(url);
			repositories = cr.get(Repository[].class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving repositories.");
		}
		
		return repositories;
		
	}
	
	public static User getGithubUser(String accessToken) {
		
		ClientResource cr = null;
		User user = null;
		
		try {
			String url = BASE_URL + "/user?access_token=" + accessToken;
//			System.out.println(url);
			cr = new ClientResource(url);
			user = cr.get(User.class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving authenticated user.");
		}
		
		return user;
	}
	
	public static Branch getMasterBranch(String owner, String repo, String accessToken) {
		
		ClientResource cr = null;
		Branch master = null;
		
		try {
			String url = BASE_URL + "/repos/" + owner + "/" + repo + "/branches/master?access_token=" + accessToken;
//			System.out.println("URL peticion " + url);
			cr = new ClientResource(url);
			master = cr.get(Branch.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving master branch.");
		}
		
		return master;

	}
	
	public static RepositoryTree getRepositoryTree(String owner, String repo, String accessToken) {
		
		System.out.println("Datos" +  owner + repo);
		String treeSha = getMasterBranch(owner, repo, accessToken).getCommit().getSha();
		
		ClientResource cr = null;
		RepositoryTree tree = null;
		
		try {
			String url = BASE_URL + "/repos/" + owner + "/" + repo + "/git/trees/" + treeSha + "?recursive=1&access_token=" + accessToken;
			cr = new ClientResource(url);
			tree = cr.get(RepositoryTree.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving tree.");
		}
		
		return tree;
	}
	
	public static Blob getBlob(String urlBlob, String accessToken) {
		/**
		 * Carga un archivo codificado en base64
		 * despues se decodifica en javascript y para
		 * ser mostrado en el editor
		 * 
		 * pensado para utilizarse peticiones ajax......
		 */
		String resource = urlBlob + "?access_token=" + accessToken;
		ClientResource cr = null;
		Blob blob = null;
		
		try {
			cr = new ClientResource(resource);
			blob = cr.get(Blob.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving blob");
		}
		
		return blob;
	}
}
