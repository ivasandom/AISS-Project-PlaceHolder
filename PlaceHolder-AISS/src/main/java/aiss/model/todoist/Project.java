
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
    "is_deleted",
    "indent",
    "item_order",
    "name",
    "collapsed",
    "shared",
    "id",
    "is_archived",
    "color"
})
public class Project {

    @JsonProperty("is_deleted")
    private Integer isDeleted;
    @JsonProperty("indent")
    private Integer indent;
    @JsonProperty("item_order")
    private Integer itemOrder;
    @JsonProperty("name")
    private String name;
    @JsonProperty("collapsed")
    private Integer collapsed;
    @JsonProperty("shared")
    private Boolean shared;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("is_archived")
    private Integer isArchived;
    @JsonProperty("color")
    private Integer color;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("is_deleted")
    public Integer getIsDeleted() {
        return isDeleted;
    }

    @JsonProperty("is_deleted")
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @JsonProperty("indent")
    public Integer getIndent() {
        return indent;
    }

    @JsonProperty("indent")
    public void setIndent(Integer indent) {
        this.indent = indent;
    }

    @JsonProperty("item_order")
    public Integer getItemOrder() {
        return itemOrder;
    }

    @JsonProperty("item_order")
    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("collapsed")
    public Integer getCollapsed() {
        return collapsed;
    }

    @JsonProperty("collapsed")
    public void setCollapsed(Integer collapsed) {
        this.collapsed = collapsed;
    }

    @JsonProperty("shared")
    public Boolean getShared() {
        return shared;
    }

    @JsonProperty("shared")
    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("is_archived")
    public Integer getIsArchived() {
        return isArchived;
    }

    @JsonProperty("is_archived")
    public void setIsArchived(Integer isArchived) {
        this.isArchived = isArchived;
    }

    @JsonProperty("color")
    public Integer getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(Integer color) {
        this.color = color;
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
