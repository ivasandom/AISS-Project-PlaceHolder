
package aiss.model.gitLab;

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
    "title",
    "project_id",
    "action_name",
    "target_id",
    "target_type",
    "author_id",
    "target_title",
    "created_at",
    "author",
    "author_username"
})
public class Package2 {

    @JsonProperty("title")
    private Object title;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("action_name")
    private String actionName;
    @JsonProperty("target_id")
    private Integer targetId;
    @JsonProperty("target_type")
    private String targetType;
    @JsonProperty("author_id")
    private Integer authorId;
    @JsonProperty("target_title")
    private String targetTitle;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("author")
    private Author author;
    @JsonProperty("author_username")
    private String authorUsername;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public Object getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Object title) {
        this.title = title;
    }

    @JsonProperty("project_id")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("action_name")
    public String getActionName() {
        return actionName;
    }

    @JsonProperty("action_name")
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @JsonProperty("target_id")
    public Integer getTargetId() {
        return targetId;
    }

    @JsonProperty("target_id")
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    @JsonProperty("target_type")
    public String getTargetType() {
        return targetType;
    }

    @JsonProperty("target_type")
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    @JsonProperty("author_id")
    public Integer getAuthorId() {
        return authorId;
    }

    @JsonProperty("author_id")
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @JsonProperty("target_title")
    public String getTargetTitle() {
        return targetTitle;
    }

    @JsonProperty("target_title")
    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    @JsonProperty("author_username")
    public String getAuthorUsername() {
        return authorUsername;
    }

    @JsonProperty("author_username")
    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
