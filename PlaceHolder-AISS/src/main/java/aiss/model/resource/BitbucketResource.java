package aiss.model.resource;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.bitbucket.BitbucketRepository;



public class BitbucketResource {

	public BitbucketRepository getMyRepos(String username) {

		ClientResource cr = null;
		BitbucketRepository repositories = null;
		try {
			cr = new ClientResource("https://bitbucket.org/2.0/users"+"/"+username+"/repositories");
			repositories = cr.get(BitbucketRepository.class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the repos: " + cr.getResponse().getStatus());
		}
		return repositories;

	}
}
