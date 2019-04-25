package aiss.model.repository;

import java.util.Collection;

import aiss.model.ProjectsList;
import aiss.model.Tasks;



public interface ProjectsListRepository {
		// Tasks
		public void addTasks(Tasks t);
		public Collection<Tasks> getAllTasks();
		public Tasks getTasks(String tasksId);
		public void updateTasks(Tasks t);
		public void deleteTasks(String tasksId);
		
		// ProjectsList
		public void addProjectsList(ProjectsList p);
		public Collection<ProjectsList> getAllProjectsLists();
		public ProjectsList getProjectsList(String projectslistId);
		public void updateVenuesList(ProjectsList p);
		public void deleteProjectsList(String projectslistId);
		public Collection<Tasks> getAll(String projectslistId);
		public void addTasks(String projectslistId, String tasksId);
		public void removeTasks(String projectslistId, String tasksId); 
}
