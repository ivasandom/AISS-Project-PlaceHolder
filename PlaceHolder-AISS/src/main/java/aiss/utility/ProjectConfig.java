package aiss.utility;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.json.JSONPropertyName;

import aiss.controller.GetProjectController;
import aiss.model.harvest.Project;
import aiss.model.resource.HarvestResource;

public class ProjectConfig {
	
	private String todoistProjectId;
	private String githubRepository;
	private String gitlabRepository;
	private String bitbucketRepository;

	private static final Logger log = Logger.getLogger(GetProjectController.class.getName());

	// Constructor
	
	public ProjectConfig(HarvestResource resource, Project project) {
		
		try {
			JSONObject config = new JSONObject(project.getNotes());
			
			// Initialize properties
			if (config.has("todoist_project_id")) this.todoistProjectId = config.get("todoist_project_id").toString();
			if (config.has("github_repository")) this.githubRepository = config.get("github_repository").toString();
			if (config.has("gitlab_repository")) this.gitlabRepository = config.get("gitlab_repository").toString();
			if (config.has("bitbucket_repository")) this.bitbucketRepository = config.get("bitbucket_repository").toString();

		} catch (Exception e) {

			log.log(Level.WARNING, "Project configuration not initializated or corrupt, creating new config.");
			// Update with base configuration
			this.updateConfig(resource, project);
			
		}
		
	}
	
	// Update project notes with JSON config object
	
	public boolean updateConfig(HarvestResource resource, Project project) {
		return resource.updateProjectConfiguration(project.getId(), new JSONObject(this));
	}
	
	
	// Getters
	
	@JSONPropertyName("todoist_project_id")
	public String getTodoistProjectId() {
		return todoistProjectId;
	}
	
	@JSONPropertyName("github_repository")
	public String getGithubRepository() {
		return githubRepository;
	}
	
	@JSONPropertyName("gitlab_repository")
	public String getGitlabRepository() {
		return gitlabRepository;
	}
	
	@JSONPropertyName("bitbucket_repository")
	public String getBitbucketRepository() {
		return bitbucketRepository;
	}
	
	// Setters
	
	public void setTodoistProjectId(String todoistProjectId) {
		this.todoistProjectId = todoistProjectId;
	}

	public void setGithubRepository(String githubRepository) {
		this.githubRepository = githubRepository;
	}

	public void setGitlabRepository(String gitlabRepository) {
		this.gitlabRepository = gitlabRepository;
	}

	public void setBitbucketRepository(String bitbucketRepository) {
		this.bitbucketRepository = bitbucketRepository;
	}
	
	
}
