package aiss.model.resource;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;


import aiss.model.todoist.Project;

public class TodoistResource {
	private final String accessToken;
	public TodoistResource(String accessToken) {
        this.accessToken = accessToken;
    }
public static Project[] getMyProjects(String accessToken) {
		
		ClientResource cr = null;
		Project[] projects = null;
		
		try {
			String url = "https://beta.todoist.com/API/v8/projects" + "?access_token=" + accessToken;
			cr = new ClientResource(url);
			projects = cr.get(Project[].class);
		} catch (ResourceException e) {
			System.err.println("Error when retrieving repositories.");
		}
		
		return projects;
		
	}
}
