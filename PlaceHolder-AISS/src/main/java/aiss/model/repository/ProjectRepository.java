package aiss.model.repository;

import java.util.Collection;

import aiss.model.Project;
import aiss.model.Task;



public interface ProjectRepository {
		// Task
		public void addTask(Task t);
		public Collection<Task> getAllTasks();
		public Task getTask(String taskId);
		public void updateTask(Task t);
		public void deleteTask(String taskId);
		
		// Project
		public void addProject(Project p);
		public Collection<Project> getAllProjects();
		public Project getProject(String projectId);
		public void updateProject(Project p);
		public void deleteProject(String projectId);
		public Collection<Task> getAllTasks(String projectId);
		public void addTask(String projectId, String taskId);
		public void removeTask(String projectId, String taskId); 
}
