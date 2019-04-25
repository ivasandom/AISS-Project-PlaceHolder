package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectsList {
	
	private String id;
	private String name;
	private List<Tasks> tasks;
	
	public ProjectsList() {
	}
	
	public ProjectsList(String name) {
		this.name = name;
	}
	
	protected void setTasks(List<Tasks> t) {
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

	public List<Tasks> getTasks() {
		return tasks;
	}
	
	public Tasks getTasks(String id) {
		if (tasks == null)
			return null;
		
		Tasks task = null;
		for(Tasks p: tasks)
			if (p.getId().equals(id))
			{
				task=p;
				break;
			}
		
		return task;
	}
	
	public void addTasks(Tasks t) {
		if (tasks==null)
			tasks = new ArrayList<Tasks>();
		tasks.add(t);
	}
	
	public void deleteTasks(Tasks p) {
		tasks.remove(p);
	}
	
	public void deleteTasks(String id) {
		Tasks t = getTasks(id);
		if (t!=null)
			tasks.remove(t);
	}
}
