package aiss.trello;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
	    "name",
	    "id",
	    "idBoard"
	})
public class List {
		@JsonProperty("name")
	    private String name;
	    @JsonProperty("id")
	    private String id;
	    @JsonProperty("idBoard")
	    private String idBoard;
	    
	    public List(String name, String idBoard) {
			super();
			this.name = name;
			this.idBoard = idBoard;
		}
		public List(String name, String id, String idBoard) {
			super();
			this.name = name;
			this.id = id;
			this.idBoard = idBoard;
		}

		@JsonProperty("name")
	    public String getName() {
	        return name;
	    }

	    @JsonProperty("name")
	    public void setName(String name) {
	        this.name = name;
	    }

	    @JsonProperty("id")
	    public String getId() {
	        return id;
	    }

	    @JsonProperty("id")
	    public void setId(String id) {
	        this.id = id;
	    }
	    
	    @JsonProperty("idBoard")
	    public String getIdBoard() {
	        return idBoard;
	    }

	    @JsonProperty("idBoard")
	    public void setIdBoard(String idBoard) {
	        this.idBoard = idBoard;
	    }

}
