
package aiss.model.todoist;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import aiss.utility.TaskConfig;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "project_id",
    "content",
    "comment_count",
    "order",
    "indent",
    "priority",
    "url"
})
public class TaskSimple {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("project_id")
    private Long projectId;
    @JsonProperty("content")
    private String content;
    @JsonProperty("comment_count")
    private Long commentCount;
    @JsonProperty("order")
    private Long order;
    @JsonProperty("indent")
    private Long indent;
    @JsonProperty("priority")
    private Long priority;
    @JsonProperty("url")
    private String url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("project_id")
    public Long getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("comment_count")
    public Long getCommentCount() {
        return commentCount;
    }

    @JsonProperty("comment_count")
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    @JsonProperty("order")
    public Long getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(Long order) {
        this.order = order;
    }

    @JsonProperty("indent")
    public Long getIndent() {
        return indent;
    }

    @JsonProperty("indent")
    public void setIndent(Long indent) {
        this.indent = indent;
    }

    @JsonProperty("priority")
    public Long getPriority() {
        return priority;
    }

    @JsonProperty("priority")
    public void setPriority(Long priority) {
        this.priority = priority;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    public TaskConfig getConfig() {
    	return new TaskConfig(this);
    }

}
