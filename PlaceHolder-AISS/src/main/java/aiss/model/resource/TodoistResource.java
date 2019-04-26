package aiss.model.resource;



import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.todoist.Event;
import aiss.model.todoist.Project;


public class TodoistResource {
	
	private final String accessToken;
	private final String BASE_URL = "https://beta.todoist.com/API/v8"; 
	
	public TodoistResource(String accessToken) {
        this.accessToken = accessToken;
    }
	
	public Project[] getMyProjects() {
		
		ClientResource cr = null;
		Project[] projects = null;
		String resourceURL = BASE_URL + "/projects?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			projects = cr.get(Project[].class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving projects.");
		}
		
		return projects;
		
	}
	
	public Event[] getActiveTasks(String idProject) {
		
		ClientResource cr = null;
		Event[] events = null;
		String resourceURL = BASE_URL + "/tasks"+idProject+"?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			events = cr.get(Event[].class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving eventss.");
		}
		
		return events;
		
	}
	public Project getProject(String id) {

		ClientResource cr = null;
		Project project = null;
		String resourceURL = BASE_URL + "/projects/" + id + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			project = cr.get(Project.class);

		} catch (ResourceException re) {
			System.err.println("Error when retrieving the project: " + cr.getResponse().getStatus());
		}

		return project;

	}
	public Event getTask(String id) {

		ClientResource cr = null;
		Event task = null;
		String resourceURL = BASE_URL + "/tasks/" + id + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			task = cr.get(Event.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the board: " + cr.getResponse().getStatus());
		}

		return task;

	}
	
	public boolean addProject(String id) {
		
		ClientResource cr = null;
		boolean success = true;
		String resourceURL = BASE_URL + "/tasks/" + id + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			cr.setEntityBuffering(true);
			cr.post(" ");
		} catch (ResourceException re) {
			System.err.println("Error when adding the task: " + cr.getResponse().getStatus());
			success = false;
		}
		return success;
	}


	public boolean addTask(String name, String idProject) {
		
		ClientResource cr = null;
		boolean success = true;
		String resourceURL = BASE_URL + "/tasks/" + idProject + "/" + name + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
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
		String resourceURL = BASE_URL + "/projects/" + id + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			cr.setEntityBuffering(true);
			cr.delete();
		} catch (ResourceException re) {
			System.err.println("Error when deleting the project: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
	}

	public boolean deleteTask(String id) {
		
		ClientResource cr = null;
		boolean success = true;
		String resourceURL = BASE_URL + "/tasks/" + id + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			cr.setEntityBuffering(true);
			cr.delete();
		} catch (ResourceException re) {
			System.err.println("Error when deleting the task: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
	}

	public boolean updateProject(String id, String name) {
		
		ClientResource cr = null;
		boolean success = true;
		String resourceURL = BASE_URL + "/projects/" + id + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
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
		String resourceURL = BASE_URL + "/tasks/" + idProject + "/" + name + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			cr.setEntityBuffering(true);
			cr.put(name);
		} catch (ResourceException re) {
			System.err.println("Error when updating the task: " +name +" " + cr.getResponse().getStatus());
			success = false;
		}

		return success;
	}


}

