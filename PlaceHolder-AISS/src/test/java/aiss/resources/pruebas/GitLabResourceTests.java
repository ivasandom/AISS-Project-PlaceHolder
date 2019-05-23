package aiss.resources.pruebas;
import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.gitlab.GitLabRepository;
import aiss.model.resource.GitLabResource;
public class GitLabResourceTests {

	GitLabResource gh = new GitLabResource("59b3940eef1d08a3916d11afbb48c8a4f48781b1");
	
	@Test
	public void testGetGitLabRepository() throws UnsupportedEncodingException{
		GitLabRepository[] repositorios = gh.getMyRepos();
		assertNotNull("La carga de repositorios devolvió null", repositorios);
		System.out.println("Carga testGetGitLabRepositoryTest() con éxito");
	}
	
}
