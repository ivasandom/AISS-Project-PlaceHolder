package aiss.api.resources;

import javax.ws.rs.Path;

import aiss.model.repository.MapProjectsListRepository;
import aiss.model.repository.ProjectsListRepository;


@Path("/tasks")
public class TareasResource {
	public static TareasResource _instance=null;
	ProjectsListRepository repository;
	
	private TareasResource(){
		repository=MapProjectsListRepository.getInstance();
	}
	
	public static TareasResource getInstance()
	{
		if(_instance==null)
			_instance=new TareasResource();
		return _instance; 
	}
}
