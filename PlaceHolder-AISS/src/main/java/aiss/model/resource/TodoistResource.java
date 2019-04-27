package aiss.model.resource;

import java.util.Map;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.todoist.Attachment;
import aiss.model.todoist.Comment;
import aiss.model.todoist.Label;
import aiss.model.todoist.Project;
import aiss.model.todoist.Task;
import aiss.model.todoist.TaskSimple;

public class TodoistResource {
	
	private final String accessToken;
	private final String BASE_URL = "https://beta.todoist.com/API/v8"; 
	
	public TodoistResource(String accessToken) {
        this.accessToken = accessToken;
    }
	
	public void setCustomHttpHeader(ClientResource client, String header, String value) { 
        final String RESTLET_HTTP_HEADERS = "org.restlet.http.headers"; 
        Map<String, Object> reqAttribs = client.getRequestAttributes(); 
        Form headers = (Form)reqAttribs.get(RESTLET_HTTP_HEADERS); 
        if (headers == null) { 
            headers = new Form(); 
            reqAttribs.put(RESTLET_HTTP_HEADERS, headers); 
        } 
        headers.add(header, value); 
    } 
	
	// Project CRUD
	
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
	
	public Project getProject(String id) {
		
		ClientResource cr = null;
		Project project = null;
		String resourceURL = BASE_URL + "/projects/" + id + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			project = cr.get(Project.class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving project");
		}
		
		return project;
		
	}
	
	public boolean deleteProject(String id) {
		
		ClientResource cr = null;
		boolean deleted = true;
		String resourceURL = BASE_URL + "/projects/" + id + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			cr.delete();
		} catch (ResourceException e) {
			System.err.println("Error when retrieving project");
			deleted = false;
		}
		
		return deleted;
		
	}
	
	public boolean updateProject(String id, String newName) {
		
		ClientResource cr = null;
		boolean updated = true;
		String resourceURL = BASE_URL + "/projects/" + id + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			Form form = new Form();
			form.add("name", newName);
			cr.post(form);
		} catch (ResourceException re) {
			System.err.println("Error when updating project");
			updated = false;
		}
		
		return updated;
		
	}
	
	public Project createProject(String name) {
	
		ClientResource cr = null;
		Project newProject = null;
		String resourceURL = BASE_URL + "/projects?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);				
			Form form = new Form();
			form.add("name", name);
			Representation rep = cr.post(form, MediaType.APPLICATION_JSON);
			newProject = cr.toObject(rep, Project.class);

		} catch (ResourceException re) {
			System.err.println("Error when adding the task: " + cr.getResponse().getStatus());
		}
		
		return newProject;
	
	}
	
	
	// Task CRUD
	
	public TaskSimple[] getActiveTasks(String idProject) {
		
		ClientResource cr = null;
		TaskSimple[] tasks = null;
		String resourceURL = BASE_URL + "/tasks?project_id=" + idProject + "&token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			tasks = cr.get(TaskSimple[].class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving active tasks.");
		}
		
		return tasks;
		
	}
	
	public Task getTask(String idTask) {
		
		ClientResource cr = null;
		Task task = null;
		String resourceURL = BASE_URL + "/tasks/" + idTask + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			task = cr.get(Task.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving active tasks.");
		}
		
		return task;
		
	}
	
	public Task createTask(Task tarea) {
		/**

			$.ajax({
			    data:{projectId:"2209568886", content:"Tarea ajax"},
			    type:"POST",
			    url:"/tasks/create",
			    success: function(result){
			        alert(result.content);
			    }
			})
			
		 */
		
		ClientResource cr = null;
		Task newTask = null;
		String resourceURL = BASE_URL + "/tasks?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			Representation rep = cr.post(tarea, MediaType.APPLICATION_JSON);
			newTask = cr.toObject(rep, Task.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when adding the task: " + cr.getResponse().getStatus());
		}
		
		return newTask;
	
	}
	
	public boolean updateTask(String idTask, String newContent) {
		
		ClientResource cr = null;
		boolean updated = true;
		String resourceURL = BASE_URL + "/tasks/" + idTask + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			Form form = new Form();
			form.add("content", newContent);
			cr.post(form);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving active tasks.");
			updated = false;
		}
		
		return updated;
		
	}
	
	public boolean deleteTask(String id) {
		
		ClientResource cr = null;
		boolean deleted = true;
		String resourceURL = BASE_URL + "/tasks/" + id + "?token=" + this.accessToken;
		
		try {
			cr = new ClientResource(resourceURL);
			cr.delete();
		} catch (ResourceException e) {
			System.err.println("Error when deleting task");
			deleted = false;
		}
		
		return deleted;
	
	}
	
	
	// CRUD comments
	
	public Comment[] getProjectComments(String projectId) {
		
		ClientResource cr = null;
		Comment[] comments = null;
		String resourceURL = BASE_URL + "/comments?project_id=" + projectId + "&token=" + this.accessToken;
	
		try {
			cr = new ClientResource(resourceURL);
			comments = cr.get(Comment[].class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving project comments.");
		}
		
		return comments;
	}
	
	public Comment[] getTaskComments(String taskId) {
		
		ClientResource cr = null;
		Comment[] comments = null;
		String resourceURL = BASE_URL + "/comments?task_id=" + taskId + "&token=" + this.accessToken;
	
		try {
			cr = new ClientResource(resourceURL);
			comments = cr.get(Comment[].class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving task comments.");
		}
		
		return comments;
	}
	
	public Comment getComment(String id) {
		
		ClientResource cr = null;
		Comment comment = null;
		String resourceURL = BASE_URL + "/comments/" + id + "?token=" + this.accessToken;
	
		try {
			cr = new ClientResource(resourceURL);
			comment = cr.get(Comment.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving comment.");
		}
		
		return comment;
		
	}
	
	public Comment createComment(String taskId, String projectId, String content, Attachment attachment) {
			
		ClientResource cr = null;
		Comment comment = null;
		String resourceURL = BASE_URL + "/comments?token=" + this.accessToken;
	
		try {
			cr = new ClientResource(resourceURL);
			Form form = new Form();
			if (taskId != null) {
				form.add("task_id", taskId);
			} else {
				form.add("project_id", projectId);
			}
			form.add("content", content);
			comment = cr.post(form, Comment.class);
		} catch (ResourceException re) {
			System.err.println("Error when create comment.");
		}
		
		return comment;

		
	}
	
	public boolean updateComment(String id, String content) {
		
		ClientResource cr = null;
		boolean updated = true;
		String resourceURL = BASE_URL + "/comments/" + id + "?token=" + this.accessToken;
	
		try {
			cr = new ClientResource(resourceURL);
			Form form = new Form();
			form.add("content", content);
			cr.post(form);
		} catch (ResourceException re) {
			System.err.println("Error when create comment.");
			updated = false;
		}
		
		return updated;
	}
	
	public boolean deleteComment(String id) {
		
		ClientResource cr = null;
		boolean deleted = true;
		String resourceURL = BASE_URL + "/comments/" + id + "?token=" + this.accessToken;
	
		try {
			cr = new ClientResource(resourceURL);
			cr.delete();
		} catch (ResourceException re) {
			System.err.println("Error when create comment.");
			deleted = false;
		}
		
		return deleted;
	}
	

	// CRUD Labels

	public Label[] getLabels(String taskId) {
		
		ClientResource cr = null;
		Label[] labels = null;
		String resourceURL = BASE_URL + "/labels?token=" + this.accessToken;
	
		try {
			cr = new ClientResource(resourceURL);
			labels = cr.get(Label[].class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving labels.");
		}
		
		return labels;
	}
	
	public Label getLabel(String id) {
		
		ClientResource cr = null;
		Label label = null;
		String resourceURL = BASE_URL + "/labels/" + id + "?token=" + this.accessToken;
	
		try {
			cr = new ClientResource(resourceURL);
			label = cr.get(Label.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving label.");
		}
		
		return label;
		
	}
	
	public Label createLabel(String name, String order) {
			
		ClientResource cr = null;
		Label label = null;
		String resourceURL = BASE_URL + "/labels?token=" + this.accessToken;
	
		try {
			cr = new ClientResource(resourceURL);
			Form form = new Form();
			form.add("name", name);
			form.add("order", order);
			label = cr.post(form, Label.class);
		} catch (ResourceException re) {
			System.err.println("Error when creating label.");
		}
		
		return label;

		
	}
	
	public boolean updateLabel(String id, String newName, String newOrder) {
		
		ClientResource cr = null;
		boolean updated = true;
		String resourceURL = BASE_URL + "/labels/" + id + "?token=" + this.accessToken;
	
		try {
			cr = new ClientResource(resourceURL);
			Form form = new Form();
			form.add("name", newName);
			form.add("order", newOrder);
			cr.post(form);
		} catch (ResourceException re) {
			System.err.println("Error when updating label.");
			updated = false;
		}
		
		return updated;
	}
	
	public boolean deleteLabel(String id) {
		
		ClientResource cr = null;
		boolean deleted = true;
		String resourceURL = BASE_URL + "/labels/" + id + "?token=" + this.accessToken;
	
		try {
			cr = new ClientResource(resourceURL);
			cr.delete();
		} catch (ResourceException re) {
			System.err.println("Error when deleting label.");
			deleted = false;
		}
		
		return deleted;
	}


}

