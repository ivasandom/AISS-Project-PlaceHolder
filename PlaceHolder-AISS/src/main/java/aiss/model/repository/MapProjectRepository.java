package aiss.model.repository;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Project;
import aiss.model.Task;


public class MapProjectRepository implements ProjectRepository {
	
	private Map<String, Project> projectMap = new HashMap<String,Project>();
	private Map<String, Task> taskMap = new HashMap<String,Task>();
	
	private static MapProjectRepository instance = null;
	private int indexTask = 0; // Index to create projects and tasks' identifiers.
	private int indexProject = 0;

	public static MapProjectRepository getInstance() {

		if (instance==null) {
			instance = new MapProjectRepository();
			instance.init();
		}

		return instance;
	}
	
	public void init() {
		
		// Create tasks
		Task hacerCena = new Task("Hacer la cena");
		addTask(hacerCena);
		
		Task sacarBasura = new Task("Sacar la basura");
		addTask(sacarBasura);
		
		Task hacerMockups = new Task("Hacer los mockups");
		addTask(hacerMockups);
		
		Task hacerDiagramas = new Task("Hacer los diagramas");
		addTask(hacerDiagramas);
		
		// Create projects
		Project cosasDeCasa = new Project("Cosas de casa");
		addProject(cosasDeCasa);
		
		Project tareasAISS = new Project("Tareas AISS");
		addProject(tareasAISS);
		
		// Add tasks to projects
		addTask(cosasDeCasa.getId(), hacerCena.getId());
		System.out.println("id project: " + cosasDeCasa.getId() + " id task: " + hacerCena.getId());
		addTask(cosasDeCasa.getId(), sacarBasura.getId());
		
		addTask(tareasAISS.getId(), hacerMockups.getId());
		addTask(tareasAISS.getId(), hacerDiagramas.getId());
		
	}
	
	// Tasks related operations
	
	@Override
	public void addTask(Task t) {
		// TODO Auto-generated method stub
		String id = "t" + indexTask++;
		t.setId(id);
		taskMap.put(id, t);
		
	}
	@Override
	public Collection<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return taskMap.values();
	}
	@Override
	public Collection<Task> getAllTasksFilterByFirstLetter(String firstLetter){
		return taskMap.values();
	}
	@Override
	public Task getTask(String taskId) {
		// TODO Auto-generated method stub
		return taskMap.get(taskId);
	}
	@Override
	public void updateTask(Task t) {
		// TODO Auto-generated method stub
		Task task = taskMap.get(t.getId());
		task.setName(t.getName());
	}
	@Override
	public void deleteTask(String taskId) {
		// TODO Auto-generated method stub
		taskMap.remove(taskId);
	}
	
	// Projects related operations
	@Override
	public void addProject(Project p) {
		// TODO Auto-generated method stub
		String id = "p" + indexProject++;	
		p.setId(id);
		projectMap.put(id,p);
		
	}
	@Override
	public Collection<Project> getAllProjects() {
		// TODO Auto-generated method stub
		return projectMap.values();
	}
	@Override
	public Collection<Project> getAllProjectsFilterByFirstLetter(String firstLetter){
		return projectMap.values();
	}
	@Override
	public Collection<Project> getAllProjectsPagination(int start, int size){
		return projectMap.values();
	}
	@Override
	public Project getProject(String projectid) {
		// TODO Auto-generated method stub
		System.out.println("proyecto obtenido: " + projectMap.get(projectid));
		return projectMap.get(projectid);
	}
	@Override
	public void updateProject(Project p) {
		// TODO Auto-generated method stub
		projectMap.put(p.getId(),p);
	}
	@Override
	public void deleteProject(String projectId) {
		// TODO Auto-generated method stub
		projectMap.remove(projectId);
	}
	@Override
	public Collection<Task> getAllTasks(String projectId) {
		// TODO Auto-generated method stub
		return getProject(projectId).getTasks();
	}
	@Override
	public void addTask(String projectId, String taskId) {
		// TODO Auto-generated method stub
		Project project = getProject(projectId);
		System.out.println("obteniendo projectId:" + projectId);
		System.out.println("tareaOBTENIDA: " + taskMap.get(taskId));
		project.addTask(taskMap.get(taskId));
		
	}
	@Override
	public void removeTask(String projectId, String taskId) {
		// TODO Auto-generated method stub
		getProject(projectId).deleteTask(taskId);
	}


}
