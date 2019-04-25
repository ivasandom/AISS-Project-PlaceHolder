package aiss.model;

public class Tasks {
	
	private String name;
	private String id;
	
	public Tasks(){
	}
	
	public Tasks(String name) {
		this.name = name;
	}
	
	public Tasks(String id, String name) {
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
