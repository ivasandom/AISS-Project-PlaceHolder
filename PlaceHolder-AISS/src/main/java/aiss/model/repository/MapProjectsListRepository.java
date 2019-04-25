package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import aiss.model.ProjectsList;
import aiss.model.Tasks;



public class MapProjectsListRepository implements ProjectsListRepository{
	Map<String, ProjectsList> projectsListMap;
	Map<String, Tasks> taskMap;
	private static MapProjectsListRepository instance=null;
	private int index=0;			// Index to create projectslists and tasks' identifiers.


	public static MapProjectsListRepository getInstance() {

		if (instance==null) {
			instance = new MapProjectsListRepository();
			instance.init();
		}

		return instance;
	}
	public void init() {

		projectsListMap = new HashMap<String,ProjectsList>();
		taskMap = new HashMap<String,Tasks>();
		
		//Create tasks
		
		
		
	}
	@Override
	public void addTasks(Tasks t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Collection<Tasks> getAllTasks() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Tasks getTasks(String tasksId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateTasks(Tasks t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteTasks(String tasksId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addProjectsList(ProjectsList p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Collection<ProjectsList> getAllProjectsLists() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ProjectsList getProjectsList(String projectslistId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateVenuesList(ProjectsList p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteProjectsList(String projectslistId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Collection<Tasks> getAll(String projectslistId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addTasks(String projectslistId, String tasksId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeTsaks(String projectslistId, String tasksId) {
		// TODO Auto-generated method stub
		
	}

}
