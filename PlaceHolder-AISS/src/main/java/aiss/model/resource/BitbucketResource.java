package aiss.model.resource;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.bitbucket.BitbucketRepository;

public class BitbucketResource {
	
	private final String accessToken;
	private final String BASE_URL = "https://bitbucket.org/2.0"; 
	
	public BitbucketResource(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public BitbucketRepository[] getMyRepos() {

		ClientResource cr = null;
		BitbucketRepository[] repositories = null;
		String resourceURL = BASE_URL + "/repositories?access_token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			repositories = cr.get(BitbucketRepository[].class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the repos: " + cr.getResponse().getStatus());
		}
		
		return repositories;

	}
}
