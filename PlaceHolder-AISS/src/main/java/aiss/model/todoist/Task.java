
package aiss.model.todoist;

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
    "comment_count",
    "completed",
    "content",
    "due",
    "id",
    "order",
    "indent",
    "priority",
    "project_id",
    "url"
})
public class Task {

    @JsonProperty("comment_count")
    private Long commentCount;
    @JsonProperty("completed")
    private Boolean completed;
    @JsonProperty("content")
    private String content;
    @JsonProperty("due")
    private Due due;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("order")
    private Long order;
    @JsonProperty("indent")
    private Long indent;
    @JsonProperty("priority")
    private Long priority;
    @JsonProperty("project_id")
    private Long projectId;
    @JsonProperty("url")
    private String url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("comment_count")
    public Long getCommentCount() {
        return commentCount;
    }

    @JsonProperty("comment_count")
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    @JsonProperty("completed")
    public Boolean getCompleted() {
        return completed;
    }

    @JsonProperty("completed")
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("due")
    public Due getDue() {
        return due;
    }

    @JsonProperty("due")
    public void setDue(Due due) {
        this.due = due;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
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

    @JsonProperty("project_id")
    public Long getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

}
