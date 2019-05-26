package aiss.model.resource;

import org.json.JSONObject;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
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
		
		Repository[] repositories = null;
		String resourceURL = BASE_URL + "/user/repos";
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			repositories = cr.get(Repository[].class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving repositories.");
		}
		
		return repositories;
		
	}
	
	public User getGithubUser() {
		
		User user = null;
		String resourceURL = BASE_URL + "/user";
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			user = cr.get(User.class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving authenticated user.");
		}
		
		return user;
	}
	
	public Branch getMasterBranch(String owner, String repo) {
		
		Branch master = null;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/branches/master";
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {;
			master = cr.get(Branch.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving master branch." + cr.getResponse().getStatus());
		}
		
		return master;

	}
	
	public RepositoryTree getRepositoryTree(String owner, String repo) {
		
		String treeSha = this.getMasterBranch(owner, repo).getCommit().getSha();
		RepositoryTree tree = null;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/git/trees/" + treeSha + "?recursive=1";
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			tree = cr.get(RepositoryTree.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving tree.");
		}
		
		return tree;
	}
	
	
	public boolean updateMasterReference(String owner, String repo, String commitSha) {
	
		boolean updated = true;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/git/refs/heads/master";
		JSONObject form = new JSONObject();
		ClientResource cr = new ClientResource(resourceURL);
		
		//https://mdswanson.com/blog/2011/07/23/digging-around-the-github-api-take-2.html
		form.put("force", true);
		form.put("sha", commitSha);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			cr.post(new JsonRepresentation(form.toString()), MediaType.APPLICATION_JSON);
		} catch (ResourceException re){
			updated = false;
			System.err.println("Error when updated reference" + cr.getResponse().getStatus());
		}
		
		return updated;
	
	}

	public String createCommit(String owner, String repo, String treeSha, String commitMessage, String parentSha) {
		
		String commitSha = null;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/git/commits";

		ClientResource cr = new ClientResource(resourceURL);
		JSONObject form = new JSONObject();
		
		form.put("message", commitMessage);
		form.put("tree", treeSha);
		form.append("parents", parentSha);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
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
		
		String treeSha = null;
		String resourceURL = BASE_URL + "/repos/" + owner + "/" + repo + "/git/trees";
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
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
		
		Blob blob = null;
		String resourceURL = urlBlob;
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			blob = cr.get(Blob.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving blob");
		}
		
		return blob;
	}
}
