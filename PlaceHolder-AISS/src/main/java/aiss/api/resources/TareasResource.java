package aiss.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.ProjectsList;
import aiss.model.Tasks;
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
	
	@GET
	@Produces("application/json")
	public Collection<ProjectsList> getAll()
	{
		return repository.getAllProjectsLists();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Tasks get(@PathParam("id") String taskId)
	{
		Tasks gtask = repository.getTasks(taskId);
		
		if(gtask == null)
		{
			throw new NotFoundException("The task with id " + taskId + "was not found");
		}
		
		return gtask;
	}
	
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addTasks(@Context UriInfo uriInfo, Tasks task) {
		if(task.getName() == null || "".equals(task.getName()))
			throw new BadRequestException("The name of the task must not be null");
		
		repository.addTasks(task);
		
		//Builds the response. Returns the task the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(task.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(task);
		
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateTasks(Tasks tasks) {
		Tasks oldTask = repository.getTasks(tasks.getId());
		if(oldTask == null) {
			throw new NotFoundException("The task with id= " +  tasks.getId() + " was not found");
		}
		
		if(tasks.getName() == null) {
			throw new NotFoundException("The task with id= " + tasks.getName() + " was not found");
		}
		
		if(tasks.getName() != null) {
			oldTask.setName(tasks.getName());
		}		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeTask(@PathParam("id") String taskId) {
		
		if(taskId == null) {
			throw new NotFoundException("The task whit id= " + taskId + " was not found");
		}
		
		_instance.removeTask(taskId);
		 
		 return Response.noContent().build();
	}
}
