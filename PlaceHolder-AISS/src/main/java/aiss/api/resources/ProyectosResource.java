package aiss.api.resources;

import javax.ws.rs.Path;

import aiss.model.repository.MapProjectsListRepository;
import aiss.model.repository.ProjectsListRepository;

@Path("/projects")
public class ProyectosResource {
	
	/* Singleton */
	private static ProyectosResource _instance=null;
	ProjectsListRepository repository;
	
	private ProyectosResource() {
		repository=MapProjectsListRepository.getInstance();

	}
	
	public static ProyectosResource getInstance()
	{
		if(_instance==null)
				_instance=new ProyectosResource();
		return _instance;
	}
}
