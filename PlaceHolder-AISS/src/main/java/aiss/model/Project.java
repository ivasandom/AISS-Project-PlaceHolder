package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class Project {
	
	private String id;
	private String name;
	private List<Task> tasks;
	
	public Project() {
	}
	
	public Project(String name) {
		this.name = name;
	}
	
	protected void setTasks(List<Task> t) {
		this.tasks = t;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}
	
	public Task getTask(String id) {
		if (tasks == null)
			return null;
		
		Task task = null;
		for(Task p: tasks)
			if (p.getId().equals(id))
			{
				task=p;
				break;
			}
		
		return task;
	}
	
	public void addTask(Task t) {
		if (tasks==null)
			tasks = new ArrayList<Task>();
		tasks.add(t);
	}
	
	public void deleteTask(Task p) {
		tasks.remove(p);
	}
	
	public void deleteTask(String id) {
		Task t = getTask(id);
		if (t!=null)
			tasks.remove(t);
	}
}
