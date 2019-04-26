package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Project;
import aiss.model.Task;



public class MapProjectRepository implements ProjectRepository{
	Map<String, Project> projectMap;
	Map<String, Task> taskMap;
	private static MapProjectRepository instance=null;
	private int index=0;			// Index to create projects and tasks' identifiers.


	public static MapProjectRepository getInstance() {

		if (instance==null) {
			instance = new MapProjectRepository();
			instance.init();
		}

		return instance;
	}
	public void init() {

		projectMap = new HashMap<String,Project>();
		taskMap = new HashMap<String,Task>();
		
		//Create tasks
		Task hacerCena = new Task();
		hacerCena.setName("Hacer la cena");
		addTask(hacerCena);
		
		Task sacarBasura = new Task();
		hacerCena.setName("Sacar la basura");
		addTask(sacarBasura);
		
		Task hacerMockups = new Task();
		hacerCena.setName("Hacer los mockups");
		addTask(hacerMockups);
		
		Task hacerDiagramas = new Task();
		hacerCena.setName("Hacer los diagramas");
		addTask(hacerDiagramas);
		
		// Create projects
		Project cosasDeCasa = new Project();
		cosasDeCasa.setName("Cosas de casa");
		addProject(cosasDeCasa);
		
		Project tareasAISS = new Project();
		cosasDeCasa.setName("Tareas AISS");
		addProject(tareasAISS);
		
		// Add tasks to projects
		addTask(cosasDeCasa.getId(), hacerCena.getId());
		addTask(cosasDeCasa.getId(), sacarBasura.getId());
		
		addTask(hacerMockups.getId(), tareasAISS.getId());
		addTask(hacerDiagramas.getId(), tareasAISS.getId());
		
	}
	
	// Tasks related operations
	@Override
	public void addTask(Task t) {
		// TODO Auto-generated method stub
		String id = "t" + index++;
		t.setId(id);
		taskMap.put(id, t);
		
	}
	@Override
	public Collection<Task> getAllTasks() {
		// TODO Auto-generated method stub
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
		String id = "p" + index++;	
		p.setId(id);
		projectMap.put(id,p);
		
	}
	@Override
	public Collection<Project> getAllProjects() {
		// TODO Auto-generated method stub
		return projectMap.values();
	}
	@Override
	public Project getProject(String projectid) {
		// TODO Auto-generated method stub
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
		project.addTask(taskMap.get(taskId));
		
	}
	@Override
	public void removeTask(String projectId, String taskId) {
		// TODO Auto-generated method stub
		getProject(projectId).deleteTask(taskId);
	}


}
