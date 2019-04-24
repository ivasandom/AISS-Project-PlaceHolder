package aiss.model.resource;



import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.todoist.Event;
import aiss.model.todoist.Project;


public class TodoistResource {
	private final String access_token;
	public TodoistResource(String access_token) {
        this.access_token = access_token;
    }
private static final String BASE_URL = "https://beta.todoist.com/API/v8"; 
	
	public static Project[] getMyProjects(String access_token) {
		
		ClientResource cr = null;
		Project[] projects = null;
		
		try {
			String url = BASE_URL + "/projects?access_token=" + access_token;
			cr = new ClientResource(url);
			projects = cr.get(Project[].class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving projects.");
		}
		
		return projects;
		
	}
	
	public static Event[] getActiveTasks(String access_token, String idProject) {
		
		ClientResource cr = null;
		Event[] events = null;
		
		try {
			String url = BASE_URL + "/tasks"+idProject+"?access_token=" + access_token;
			cr = new ClientResource(url);
			events = cr.get(Event[].class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving eventss.");
		}
		
		return events;
		
	}
	public Project getProject(String id) {

		ClientResource cr = null;
		Project project = null;
		try {
			cr = new ClientResource(BASE_URL + "/projects"+"/"+id+"?access_token=" + access_token);
			project = cr.get(Project.class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the project: " + cr.getResponse().getStatus());
		}

		return project;

	}
	public Event getTask(String id) {

		ClientResource cr = null;
		Event task = null;
		try {
			cr = new ClientResource(BASE_URL + "/tasks"+"/"+id+"?access_token=" + access_token);
			task = cr.get(Event.class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the board: " + cr.getResponse().getStatus());
		}

		return task;

	}
	
	public Project addProject(String name) {
		ClientResource cr = null;
		Project resultProject = null;
		try {
			cr = new ClientResource(BASE_URL + "?access_token=" + access_token);
			cr.setEntityBuffering(true);
			resultProject = cr.post(name, Project.class);
		} catch (ResourceException re) {
			System.err.println("Error when adding the project: " + name +" " + cr.getResponse().getStatus());
		}
		return resultProject;
	}


	public boolean addTask(String name, String idProject) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(BASE_URL + "/tasks"+"/"+idProject+"/"+name+"?access_token=" + access_token);
			cr.setEntityBuffering(true);
			cr.post(" ");
		} catch (ResourceException re) {
			System.err.println("Error when adding the task: " + name +" " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}

	public boolean deleteProject(String id) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(BASE_URL + "/projects"+"/"+id+"?access_token=" + access_token);
			cr.setEntityBuffering(true);
			cr.delete();
		}catch (ResourceException re) {
			System.err.println("Error when deleting the project: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		return success;
	}

	public boolean deleteTask(String id) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(BASE_URL + "/tasks"+"/"+id+"?access_token=" + access_token);
			cr.setEntityBuffering(true);
			cr.delete();
		}catch (ResourceException re) {
			System.err.println("Error when deleting the task: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		return success;
	}

	public boolean updateProject(String id, String name) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(BASE_URL + "/projects"+"/"+id+"?access_token=" + access_token);
			cr.setEntityBuffering(true);
			cr.put(name);
		} catch (ResourceException re) {
			System.err.println("Error when updating the project: " +id +" " + cr.getResponse().getStatus());
			success = false;
		}

		return success;
	}

	public boolean updateTask(String name, String idProject) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(BASE_URL + "/tasks"+"/"+idProject+"/"+name+"?access_token=" + access_token);
			cr.setEntityBuffering(true);
			cr.put(name);
		} catch (ResourceException re) {
			System.err.println("Error when updating the task: " +name +" " + cr.getResponse().getStatus());
			success = false;
		}

		return success;
	}


}

