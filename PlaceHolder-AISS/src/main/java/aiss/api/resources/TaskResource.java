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

import aiss.model.Project;
import aiss.model.Task;
import aiss.model.repository.MapProjectRepository;
import aiss.model.repository.ProjectRepository;


@Path("/tasks")
public class TaskResource {
	public static TaskResource _instance=null;
	ProjectRepository repository;
	
	private TaskResource(){
		repository=MapProjectRepository.getInstance();
	}
	
	public static TaskResource getInstance()
	{
		if(_instance==null)
			_instance=new TaskResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Project> getAll()
	{
		return repository.getAllProjects();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Task get(@PathParam("id") String taskId)
	{
		Task gtask = repository.getTask(taskId);
		
		if(gtask == null)
		{
			throw new NotFoundException("The task with id " + taskId + "was not found");
		}
		
		return gtask;
	}
	
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addTask(@Context UriInfo uriInfo, Task task) {
		if(task.getName() == null || "".equals(task.getName()))
			throw new BadRequestException("The name of the task must not be null");
		
		repository.addTask(task);
	
		//Builds the response. Returns the task the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(task.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(task);
		
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateTask(Task task) {
		Task oldTask = repository.getTask(task.getId());
		if(oldTask == null) {
			throw new NotFoundException("The task with id= " +  task.getId() + " was not found");
		}
		
		if(task.getName() == null) {
			throw new NotFoundException("The task with id= " + task.getName() + " was not found");
		}
		
		if(task.getName() != null) {
			oldTask.setName(task.getName());
		}		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeTask(@PathParam("id") String taskId) {
		
		if(taskId == null) {
			throw new NotFoundException("The task with id= " + taskId + " was not found");
		}
		
		_instance.removeTask(taskId);
		 
		 return Response.noContent().build();
	}
}
