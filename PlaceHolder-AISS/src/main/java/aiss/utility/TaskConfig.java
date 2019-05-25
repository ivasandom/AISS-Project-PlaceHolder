package aiss.utility;

import org.json.JSONObject;
import org.json.JSONPropertyName;

import aiss.model.todoist.TaskSimple;

public class TaskConfig {
	
	private String taskName;
	private String taskParent;

	
	public TaskConfig(String taskName, String taskParent) {
		this.taskName = taskName;
		this.taskParent = taskParent;
	}
	
	public TaskConfig(TaskSimple task) {
		
		try {
			JSONObject config = new JSONObject(task.getContent());
			
			if (config.has("task_name")) this.taskName = config.get("task_name").toString();
			if (config.has("task_parent")) this.taskParent = config.get("task_parent").toString();
			
			
		} catch (Exception e) {

			new TaskConfig(null, null);
		}		
	}
	
	
	// Getters
	
	@JSONPropertyName("task_name")
	public String getTaskName() {
		return taskName;
	}
	
	@JSONPropertyName("task_parent")
	public String getTaskParent() {
		return taskParent;
	}
	
	
	// Setters
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public void setTaskParent(String taskParent) {
		this.taskParent = taskParent;
	}
	
	
}
