package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

@Path("/projects")
public class ProjectResource {
	
	/* Singleton */
	private static ProjectResource _instance=null;
	ProjectRepository repository;
	
	private ProjectResource() {
		repository=MapProjectRepository.getInstance();

	}
	
	public static ProjectResource getInstance()
	{
		if(_instance==null)
				_instance=new ProjectResource();
		return _instance;
	}
	
	@GET
	@Produces("application/json")
	public Collection<Project> getAll()
	{
		return repository.getAllProjects();
	}
	
	@GET
	@Produces("application/json")
	public Collection<Project> getAll(@QueryParam("name") String primeraLetra){
		if (primeraLetra == null) {
			return repository.getAllProjects();
		} else {
			List<Project> result = new ArrayList<Project>();
			for(Project project:repository.getAllProjects()) {
				if(project.getName().charAt(0)==primeraLetra.charAt(0)) {
					result.add(project);
				}
			}
			return result;
		}
	}
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Project get(@PathParam("id") String id)
	{
		Project list = repository.getProject(id);
		
		if (list == null) {
			throw new NotFoundException("The Project with id "+ id +" was not found");			
		}
		
		return list;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addProject(@Context UriInfo uriInfo, Project project) {
		if (project.getName() == null || "".equals(project.getName()))
			throw new BadRequestException("The name of the project must not be null");
		
		if (project.getTasks()!=null)
			throw new BadRequestException("The tasks are not editable.");

		repository.addProject(project);

		// Builds the response. Returns the project the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(project.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(project);			
		return resp.build();
	}

	
	@PUT
	@Consumes("application/json")
	public Response updateProject(Project project) {
		Project oldproject = repository.getProject(project.getId());
		if (oldproject == null) {
			throw new NotFoundException("The project with id "+ project.getId() +" was not found");			
		}
		
		if (project.getTasks()!=null)
			throw new BadRequestException("The tasks are not editable.");
		
		// Update name
		if (project.getName()!=null)
			oldproject.setName(project.getName());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeProject(@PathParam("id") String id) {
		Project toberemoved = repository.getProject(id);
		if (toberemoved == null)
			throw new NotFoundException("The project with id " + id + " was not found");
		else
			repository.deleteProject(id);
		
		return Response.noContent().build();
	}
	
	
	@POST	
	@Path("/{projectId}/{taskId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addTask(@Context UriInfo uriInfo,@PathParam("projectId") String projectId, @PathParam("taskId") String taskId)
	{				
		
		Project project = repository.getProject(projectId);
		Task task = repository.getTask(taskId);
		
		if (project==null)
			throw new NotFoundException("The project with id " + projectId + " was not found");
		
		if (task == null)
			throw new NotFoundException("The task with id " + taskId + " was not found");
		
		if (project.getTask(taskId)!=null)
			throw new BadRequestException("The task is already included in this project.");
			
		repository.addTask(projectId, taskId);		

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(projectId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(project);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{projectId}/{taskId}")
	public Response removeTask(@PathParam("projectId") String projectId, @PathParam("taskId") String taskId) {
		Project project = repository.getProject(projectId);
		Task task = repository.getTask(taskId);
		
		if (project==null)
			throw new NotFoundException("The project with id " + projectId + " was not found");
		
		if (task == null)
			throw new NotFoundException("The task with id " + taskId + " was not found");
		
		
		repository.removeTask(projectId, taskId);		
		
		return Response.noContent().build();
	}
}
