package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class Project {
	
	private String id;
	private String name;
	private List<Task> task;
	
	public Project() {
	}
	
	public Project(String name) {
		this.name = name;
	}
	
	protected void setTasks(List<Task> t) {
		this.task = t;
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
		return task;
	}
	
	public Task getTask(String id) {
		if (task == null)
			return null;
		
		Task task = null;
		for(Task p: this.task)
			if (p.getId().equals(id))
			{
				task=p;
				break;
			}
		
		return task;
	}
	
	public void addTask(Task t) {
		if (task==null)
			task = new ArrayList<Task>();
		task.add(t);
	}
	
	public void deleteTask(Task p) {
		task.remove(p);
	}
	
	public void deleteTask(String id) {
		Task t = getTask(id);
		if (t!=null)
			task.remove(t);
	}
}
