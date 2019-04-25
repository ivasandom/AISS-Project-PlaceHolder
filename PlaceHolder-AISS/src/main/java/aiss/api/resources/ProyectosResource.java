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
	
	@GET
	@Produces("application/json")
	public Collection<ProjectsList> getAll()
	{
		return repository.getAllProjectsLists();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ProjectsList get(@PathParam("id") String id)
	{
		ProjectsList list = repository.getProjectsList(id);
		
		if (list == null) {
			throw new NotFoundException("The Projects List with id "+ id +" was not found");			
		}
		
		return list;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addProjectsList(@Context UriInfo uriInfo, ProjectsList projectslist) {
		if (projectslist.getName() == null || "".equals(projectslist.getName()))
			throw new BadRequestException("The name of the project must not be null");
		
		if (projectslist.getTasks()!=null)
			throw new BadRequestException("The tasks is not editable.");

		repository.addProjectsList(projectslist);

		// Builds the response. Returns the projectslist the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(projectslist.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(projectslist);			
		return resp.build();
	}

	
	@PUT
	@Consumes("application/json")
	public Response updateVenuesList(ProjectsList projectslist) {
		ProjectsList oldprojectslist = repository.getProjectsList(projectslist.getId());
		if (oldprojectslist == null) {
			throw new NotFoundException("The projects list with id "+ projectslist.getId() +" was not found");			
		}
		
		if (projectslist.getTasks()!=null)
			throw new BadRequestException("The tasks is not editable.");
		
		// Update name
		if (projectslist.getName()!=null)
			oldprojectslist.setName(projectslist.getName());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeProjectsList(@PathParam("id") String id) {
		ProjectsList toberemoved = repository.getProjectsList(id);
		if (toberemoved == null)
			throw new NotFoundException("The projects list with id " + id + " was not found");
		else
			repository.deleteProjectsList(id);
		
		return Response.noContent().build();
	}
	
	
	@POST	
	@Path("/{projectslistId}/{tasksId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addPlaces(@Context UriInfo uriInfo,@PathParam("projectslistId") String projectslistId, @PathParam("tasksId") String tasksId)
	{				
		
		ProjectsList projectslist = repository.getProjectsList(projectslistId);
		Tasks tasks = repository.getTasks(tasksId);
		
		if (projectslist==null)
			throw new NotFoundException("The project list with id " + projectslistId + " was not found");
		
		if (tasks == null)
			throw new NotFoundException("The task with id " + tasksId + " was not found");
		
		if (projectslist.getTasks(tasksId)!=null)
			throw new BadRequestException("The task is already included in this project list.");
			
		repository.addTasks(projectslistId, tasksId);		

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(projectslistId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(projectslist);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{projectslistId}/{tasksId}")
	public Response removePlaces(@PathParam("projectslistId") String projectslistId, @PathParam("tasksId") String tasksId) {
		ProjectsList projectslist = repository.getProjectsList(projectslistId);
		Tasks tasks = repository.getTasks(tasksId);
		
		if (projectslist==null)
			throw new NotFoundException("The projects list with id " + projectslistId + " was not found");
		
		if (tasks == null)
			throw new NotFoundException("The task with id " + tasksId + " was not found");
		
		
		repository.removeTasks(projectslistId, tasksId);		
		
		return Response.noContent().build();
	}
}
