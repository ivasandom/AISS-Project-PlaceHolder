package aiss.resources.pruebas;
import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.gitlab.GitLabRepository;
import aiss.model.resource.GitLabResource;
public class GitLabResourceTests {

	GitLabResource gh = new GitLabResource("98b9bde779f1a644341c612bcd8415605899d5faf7797f0753d1b1bb6119ae66");
	
	@Test
	public void testGetGitLabRepository() throws UnsupportedEncodingException{
		GitLabRepository[] repositorios = gh.getMyRepos();
		assertNotNull("La carga de repositorios devolvió null", repositorios);
		System.out.println("Carga testGetGitLabRepositoryTest() con éxito");
	}
	
}
