package aiss.model.resource;

import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
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
			System.err.println("Error when retrieving master branch." + cr.getResponse().getStatus());
		}
		
		return master;

	}
	
	public RepositoryTree getRepositoryTree(String owner, String repo) {
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
	
	
	public boolean updateMasterReference(String owner, String repo, String commitSha) {
	
		boolean updated = true;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/git/refs/heads/master?access_token=" + this.accessToken;
		ClientResource cr = new ClientResource(resourceURL);
		JSONObject form = new JSONObject();
		
		//https://mdswanson.com/blog/2011/07/23/digging-around-the-github-api-take-2.html
		form.put("force", true);
		form.put("sha", commitSha);
		
		try {
			cr.post(new JsonRepresentation(form.toString()), MediaType.APPLICATION_JSON);
	
		} catch (ResourceException re){
			updated = false;
			System.err.println("Error when updated reference" + cr.getResponse().getStatus());
		}
		
		return updated;
	
	}

	public String createCommit(String owner, String repo, String treeSha, String commitMessage, String parentSha) {
		// Returns Commit SHA
		
		String commitSha = null;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/git/commits?access_token=" + this.accessToken;

		ClientResource cr = new ClientResource(resourceURL);
		JSONObject form = new JSONObject();
		
		form.put("message", commitMessage);
		form.put("tree", treeSha);
		form.append("parents", parentSha);
		
		try {
			cr.post(new JsonRepresentation(form.toString()), MediaType.APPLICATION_JSON);
			String[] ref = cr.getLocationRef().toString().split("/");
			
			commitSha = ref[ref.length-1];
		} catch (ResourceException re){
			System.err.println("Error when creating repository tree" + cr.getResponse().getStatus());
		}
		
		return commitSha;
		
	}

	public String createRepositoryTree(String owner, String repo, String repositoryTree) {
		// Returns Tree SHA
		String treeSha = null;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/git/trees?access_token=" + this.accessToken;
		ClientResource cr = new ClientResource(resourceURL);
		
		try {

			cr.post(new JsonRepresentation(repositoryTree), MediaType.APPLICATION_JSON);
			String[] ref = cr.getLocationRef().toString().split("/");
			treeSha = ref[ref.length-1];
			
		} catch (ResourceException re){
			
			System.err.println("Error when creating repository tree" + cr.getResponse().getStatus());
			
		}
		
		return treeSha;
		
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
