package aiss.trello;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
	    "name",
	    "id",
	    "idList"
	})
public class Card {
	
		@JsonProperty("name")
	    private String name;
	    @JsonProperty("id")
	    private String id;
	    @JsonProperty("idList")
	    private String idList;
	    
		public Card(String name, String id, String idList) {
			super();
			this.name = name;
			this.id = id;
			this.idList = idList;
		}

		public Card(String name, String idList) {
			super();
			this.name = name;
			this.idList = idList;
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
	    
	    @JsonProperty("idList")
	    public String getIdList() {
	        return idList;
	    }

	    @JsonProperty("idList")
	    public void setIdList(String idList) {
	        this.idList = idList;
	    }

}
