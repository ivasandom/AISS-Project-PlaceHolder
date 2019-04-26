package aiss.model;

public class Task {
	
	private String name;
	private String id;
	
	public Task(){
	}
	
	public Task(String name) {
		this.name = name;
	}
	
	public Task(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
