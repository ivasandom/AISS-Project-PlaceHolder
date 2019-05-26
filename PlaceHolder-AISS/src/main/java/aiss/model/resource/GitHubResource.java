package aiss.model.resource;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
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
		System.out.println(resourceURL);
		ClientResource cr = new ClientResource(resourceURL);
		JSONObject form = new JSONObject();
		
		//https://mdswanson.com/blog/2011/07/23/digging-around-the-github-api-take-2.html
		form.put("force", true);
		form.put("sha", commitSha);
		System.out.println(commitSha);
		
		try {
			cr.post(new JsonRepresentation(form.toString()), MediaType.APPLICATION_JSON);
			System.out.println(form.toString());
	
		} catch (ResourceException re){
			updated = false;
			System.err.println("Error when updated reference" + cr.getResponse().getStatus());
		}
		
		return updated;
	
	}

	public boolean createCommit(String owner, String repo, String treeSha) {
		
		boolean created = true;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/git/commits?access_token=" + this.accessToken;
		System.out.println(resourceURL);
		ClientResource cr = new ClientResource(resourceURL);
		JSONObject form = new JSONObject();
		
		form.put("message", "prueba commit desde editor");
		form.put("tree", treeSha);
		form.append("parents", "68b7847502da2cb22a9800838eb74c3acc58ca05");
		
		try {
			cr.post(new JsonRepresentation(form.toString()), MediaType.APPLICATION_JSON);
			System.out.println(form.toString());
			System.out.println("se ha creado correctamente 3");
			String[] ref = cr.getLocationRef().toString().split("/");
			
			updateMasterReference(owner, repo, ref[ref.length-1]);
		} catch (ResourceException re){
			created = false;
			System.err.println("Error when creating repository tree" + cr.getResponse().getStatus());
		}
		
		return created;
		
	}

	public RepositoryTree createRepositoryTree(String owner, String repo, String repositoryTree) {
		
		RepositoryTree res = null;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/git/trees?access_token=" + this.accessToken;
		ClientResource cr = new ClientResource(resourceURL);
		
		try {

			Representation rep = cr.post(new JsonRepresentation(repositoryTree), MediaType.APPLICATION_JSON);
			System.out.println("se ha creado correctamente");
			String[] ref = cr.getLocationRef().toString().split("/");
			
			createCommit(owner, repo, ref[ref.length-1]);
		} catch (ResourceException re){
			System.err.println("Error when creating repository tree" + cr.getResponse().getStatus());
		}
		
		return res;
		
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
