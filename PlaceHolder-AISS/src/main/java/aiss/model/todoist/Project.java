
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
    "id",
    "indent",
    "name",
    "order"
})
public class Project {

    @JsonProperty("comment_count")
    private Long commentCount;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("indent")
    private Long indent;
    @JsonProperty("name")
    private String name;
    @JsonProperty("order")
    private Long order;
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

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("indent")
    public Long getIndent() {
        return indent;
    }

    @JsonProperty("indent")
    public void setIndent(Long indent) {
        this.indent = indent;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("order")
    public Long getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(Long order) {
        this.order = order;
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
