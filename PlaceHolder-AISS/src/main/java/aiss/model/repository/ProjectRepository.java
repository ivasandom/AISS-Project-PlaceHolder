package aiss.model.repository;

import java.util.Collection;

import aiss.model.Project;
import aiss.model.Task;



public interface ProjectRepository {

	void addTask(Task t);

	Collection<Task> getAllTasks();

	Collection<Task> getAllTasksFilterByFirstLetter(String firstLetter);

	Task getTask(String taskId);

	void updateTask(Task t);

	void deleteTask(String taskId);

	void addProject(Project p);

	Collection<Project> getAllProjects();

	Collection<Project> getAllProjectsFilterByFirstLetter(String firstLetter);

	Project getProject(String projectid);

	void updateProject(Project p);

	void deleteProject(String projectId);

	Collection<Task> getAllTasks(String projectId);

	void addTask(String projectId, String taskId);

	void removeTask(String projectId, String taskId);

	Collection<Project> getAllProjectsPagination(int start, int size);


}
