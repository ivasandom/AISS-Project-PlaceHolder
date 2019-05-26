package aiss.model.resource;

import java.util.List;

import org.json.JSONObject;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.appengine.repackaged.com.google.gson.JsonObject;

import aiss.model.harvest.Client;
import aiss.model.harvest.Client_;
import aiss.model.harvest.Profile;
import aiss.model.harvest.Project;
import aiss.model.harvest.Projects;
import aiss.model.harvest.TaskAssignment;
import aiss.model.harvest.TaskAssignments;
import aiss.model.harvest.User;

public class HarvestResource {
	
	private final String accessToken;
	private final Long accountId;
	private final String BASE_URL = "https://api.harvestapp.com/v2";
	
	
	public HarvestResource(String accessToken) {
		this.accessToken = accessToken;
		this.accountId = getUser(accessToken).getAccounts().get(0).getId();
	}
	
	public Profile getProfile() {

		Profile profile = null;
		String resourceURL = BASE_URL + "/users/me?account_id=" + accountId;
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			profile = cr.get(Profile.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving user profile");
		}
		
		return profile;

	}
	
	public Projects getMyProjects() {
		
		Projects projects = null;
		String resourceURL = BASE_URL + "/projects?account_id=" + accountId;
		ClientResource cr = new ClientResource(resourceURL);
	
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			projects = cr.get(Projects.class);
		} catch (ResourceException re) {
			System.out.println(re);
			System.err.println("Error when retrieving user projects");
		}
		
		return projects;
		
	}
	
	// ADMIN
	public Project createProject(Long clientId, String name, boolean isBillable, String billBy, String budgetBy) {
		
		Project createdProject = null;
		JsonObject form = new JsonObject();
		String resourceURL = BASE_URL + "/projects?account_id=" + accountId;
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		// Post Data
		form.addProperty("client_id", clientId);
		form.addProperty("name", name);
		form.addProperty("is_billable", isBillable);
		form.addProperty("bill_by", billBy);
		form.addProperty("budget_by", budgetBy);
		
		try {
			Representation repr = cr.post(new JsonRepresentation(form.toString()), MediaType.APPLICATION_JSON);
			createdProject = cr.toObject(repr, Project.class);
		} catch (ResourceException re) {
			System.out.println(re);
			System.err.println("Error when creating projects");
		}
		
		return createdProject;
		
	}
	
	public boolean updateProject(String projectId, Long clientId, String name,
			boolean isBillable, String billBy, String budgetBy) {
		
		boolean updated = true;
		JsonObject form = new JsonObject();
		String resourceURL = BASE_URL + "/projects/" + projectId + "?account_id=" + accountId;
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		// Post Data
		form.addProperty("client_id", clientId);
		form.addProperty("name", name);
		form.addProperty("is_billable", isBillable);
		form.addProperty("bill_by", billBy);
		form.addProperty("budget_by", budgetBy);
		
		try {
			cr.patch(new JsonRepresentation(form.toString()), MediaType.APPLICATION_JSON);
		} catch (ResourceException re) {
			System.err.println("Error when updating project with id: " + projectId);
			updated = false;
		}
		
		return updated;
		
	}
	
	
	public List<TaskAssignment> getProjectTasks(String projectId){
		
		List<TaskAssignment> tasks = null;
		String resourceURL = BASE_URL + "/projects/" + projectId + "/task_assignments?account_id=" + accountId;
		ClientResource cr = new ClientResource(resourceURL);
	
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			tasks = cr.get(TaskAssignments.class).getTaskAssignments();
		} catch (ResourceException re) {
			System.err.println("Error when project task assignments");
		}
		
		return tasks;
		
	}
	
	public List<Client_> getClients(){
		
		List<Client_> clients = null;
		String resourceURL = BASE_URL + "/clients?account_id=" + accountId;
		ClientResource cr = new ClientResource(resourceURL);
	
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			clients = cr.get(Client.class).getClients();
		} catch (ResourceException re) {
			System.out.println(re);
			System.err.println("Error when retrieving clients");
		}
		
		return clients;
	}
	
	public Project getProject(String id) {
		
		Project project = null;
		String resourceURL = BASE_URL + "/projects/" + id + "?account_id=" + accountId;
		ClientResource cr = new ClientResource(resourceURL);
	
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			project = cr.get(Project.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving project");
		}
		
		return project;
	}
	
	public boolean deleteProject(String id) {
		
		boolean deleted = true;
		String resourceURL = BASE_URL + "/projects/" + id + "?account_id=" + accountId;
		
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			cr.delete();
		} catch (ResourceException re) {
			deleted = false;
			System.err.println("Error when deleting project");
		}
		
		return deleted;
		
		
	}
	
	public boolean updateProjectConfiguration(Long projectId, JSONObject config) {
		
		boolean updated = true;
		String resourceURL = BASE_URL + "/projects/" + projectId + "?account_id=" + accountId;
		System.out.println(resourceURL);
		ClientResource cr = new ClientResource(resourceURL);
	
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		Form form = new Form();;
		form.add("notes", config.toString());
		
		try {
			cr.patch(form, MediaType.APPLICATION_JSON);
		} catch (ResourceException re) {
			updated = false;
			System.err.println("Error updating project configuration.");
			System.out.println(re);
		}
		
		return updated;
	}
	
	public User getUser(String accessToken) {
		
		User user = null;
		String resourceURL = "https://id.getharvest.com/api/v2/accounts";
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		try {
			user = cr.get(User.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving user " + re.getResponse().getStatus());
		}
		
		return user;
        
	}
	
	public boolean createTimeEntry(String projectId, String todoistTaskId, String harvestTaskId,
			String spentDate, String startedTime, String endedTime) {
		
		boolean created = true;
		String resourceURL = BASE_URL + "/time_entries?account_id=" + accountId;
		ClientResource cr = new ClientResource(resourceURL);
		
		// Token OAuth2
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		chr.setRawValue(accessToken);
		cr.setChallengeResponse(chr);
		
		JsonObject form = new JsonObject();
		
		form.addProperty("project_id", Long.valueOf(projectId));
		form.addProperty("task_id", Long.valueOf(harvestTaskId));
		form.addProperty("started_time", startedTime);
		form.addProperty("ended_time", endedTime);
		form.addProperty("task_id", Long.valueOf(harvestTaskId));
		form.addProperty("spent_date", spentDate);
	
		try {
			cr.post(new JsonRepresentation(form.toString()), MediaType.APPLICATION_JSON);
			
		} catch (ResourceException re) {
			created = false;
			System.err.println("Error when creating time entry" + cr.getResponse().getStatus());
		}
		
		return created;
		
	}
	
}
