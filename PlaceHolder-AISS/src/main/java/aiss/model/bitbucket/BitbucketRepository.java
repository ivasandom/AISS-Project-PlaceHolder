package aiss.model.bitbucket;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
	

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"id",
	"name",
	"slug",
	"scmId",
	"publico",
	"cloneUrl"
})
public class BitbucketRepository {
//	@property {number}       id - An identifier for the repository.
//	 * @property {string}       name - The name of the repository.
//	 * @property {string}       slug - The slug of the repository which is a URL-friendly variant of its name.
//	 * @property {JSON.ProjectJSON}  project - The project the repository belongs to.
//	 * @property {string}       scmId - The identifier of the repository's SCM.
//	 * @property {boolean}      public - True if the repository is publicly accessible.
//	 * @property {string}       cloneUrl - The repository's HTTP clone URL.
	
	@JsonProperty("id")
	public Integer id;
	@JsonProperty("name")
	public String name;
	@JsonProperty("slug")
	public String slug;
	@JsonProperty("scmId")
	public String scmId;
	@JsonProperty("publico")
	public Boolean publico;
	@JsonProperty("cloneUrl")
	public String cloneUrl;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}
	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty("slug")
	public String getSlug() {
		return slug;
	}
	@JsonProperty("slug")
	public void setSlug(String slug) {
		this.slug = slug;
	}
	@JsonProperty("scmId")
	public String getScmId() {
		return scmId;
	}
	@JsonProperty("scmId")
	public void setScmId(String scmId) {
		this.scmId = scmId;
	}
	@JsonProperty("publico")
	public Boolean getPublico() {
		return publico;
	}
	@JsonProperty("publico")
	public void setPublico(Boolean publico) {
		this.publico = publico;
	}
	@JsonProperty("cloneUrl")
	public String getCloneUrl() {
		return cloneUrl;
	}
	@JsonProperty("cloneUrl")
	public void setCloneUrl(String cloneUrl) {
		this.cloneUrl = cloneUrl;
	}
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
	@JsonAnySetter
	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	
	
	
}
