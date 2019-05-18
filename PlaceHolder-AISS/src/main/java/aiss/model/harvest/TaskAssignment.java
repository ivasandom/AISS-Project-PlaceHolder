
package aiss.model.harvest;

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
    "billable",
    "is_active",
    "created_at",
    "updated_at",
    "hourly_rate",
    "budget",
    "project",
    "task"
})
public class TaskAssignment {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("billable")
    private Boolean billable;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("hourly_rate")
    private Double hourlyRate;
    @JsonProperty("budget")
    private Object budget;
    @JsonProperty("project")
    private Project project;
    @JsonProperty("task")
    private Task task;
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

    @JsonProperty("billable")
    public Boolean getBillable() {
        return billable;
    }

    @JsonProperty("billable")
    public void setBillable(Boolean billable) {
        this.billable = billable;
    }

    @JsonProperty("is_active")
    public Boolean getIsActive() {
        return isActive;
    }

    @JsonProperty("is_active")
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("hourly_rate")
    public Double getHourlyRate() {
        return hourlyRate;
    }

    @JsonProperty("hourly_rate")
    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @JsonProperty("budget")
    public Object getBudget() {
        return budget;
    }

    @JsonProperty("budget")
    public void setBudget(Object budget) {
        this.budget = budget;
    }

    @JsonProperty("project")
    public Project getProject() {
        return project;
    }

    @JsonProperty("project")
    public void setProject(Project project) {
        this.project = project;
    }

    @JsonProperty("task")
    public Task getTask() {
        return task;
    }

    @JsonProperty("task")
    public void setTask(Task task) {
        this.task = task;
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
