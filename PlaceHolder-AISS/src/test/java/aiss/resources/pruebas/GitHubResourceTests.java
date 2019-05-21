package aiss.resources.pruebas;
import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.github.Repository;
import aiss.model.resource.GitHubResource;
public class GitHubResourceTests {

	GitHubResource gh = new GitHubResource("59b3940eef1d08a3916d11afbb48c8a4f48781b1");
	
	@Test
	public void testGetGitHubRepository() throws UnsupportedEncodingException{
		Repository[] repositorios = gh.getMyRepositories();
		assertNotNull("La carga de repositorios devolvió null", repositorios);
		System.out.println("Carga testGetGitHubRepositoryTest() con éxito");
	}
	
}
