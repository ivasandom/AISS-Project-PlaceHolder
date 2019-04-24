
package aiss.model.todoist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "date_added",
    "assigned_by_uid",
    "priority",
    "user_id",
    "all_day",
    "responsible_uid",
    "labels",
    "date_string",
    "checked",
    "day_order",
    "project_id",
    "due_date_utc",
    "item_order",
    "collapsed",
    "in_history",
    "indent",
    "content",
    "sync_id",
    "is_archived",
    "date_lang",
    "id",
    "is_deleted"
})
public class Item {

    @JsonProperty("date_added")
    private String dateAdded;
    @JsonProperty("assigned_by_uid")
    private Integer assignedByUid;
    @JsonProperty("priority")
    private Integer priority;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("all_day")
    private Boolean allDay;
    @JsonProperty("responsible_uid")
    private Object responsibleUid;
    @JsonProperty("labels")
    private List<Object> labels = null;
    @JsonProperty("date_string")
    private String dateString;
    @JsonProperty("checked")
    private Integer checked;
    @JsonProperty("day_order")
    private Integer dayOrder;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("due_date_utc")
    private String dueDateUtc;
    @JsonProperty("item_order")
    private Integer itemOrder;
    @JsonProperty("collapsed")
    private Integer collapsed;
    @JsonProperty("in_history")
    private Integer inHistory;
    @JsonProperty("indent")
    private Integer indent;
    @JsonProperty("content")
    private String content;
    @JsonProperty("sync_id")
    private Object syncId;
    @JsonProperty("is_archived")
    private Integer isArchived;
    @JsonProperty("date_lang")
    private String dateLang;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("is_deleted")
    private Integer isDeleted;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("date_added")
    public String getDateAdded() {
        return dateAdded;
    }

    @JsonProperty("date_added")
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    @JsonProperty("assigned_by_uid")
    public Integer getAssignedByUid() {
        return assignedByUid;
    }

    @JsonProperty("assigned_by_uid")
    public void setAssignedByUid(Integer assignedByUid) {
        this.assignedByUid = assignedByUid;
    }

    @JsonProperty("priority")
    public Integer getPriority() {
        return priority;
    }

    @JsonProperty("priority")
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("all_day")
    public Boolean getAllDay() {
        return allDay;
    }

    @JsonProperty("all_day")
    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    @JsonProperty("responsible_uid")
    public Object getResponsibleUid() {
        return responsibleUid;
    }

    @JsonProperty("responsible_uid")
    public void setResponsibleUid(Object responsibleUid) {
        this.responsibleUid = responsibleUid;
    }

    @JsonProperty("labels")
    public List<Object> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<Object> labels) {
        this.labels = labels;
    }

    @JsonProperty("date_string")
    public String getDateString() {
        return dateString;
    }

    @JsonProperty("date_string")
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @JsonProperty("checked")
    public Integer getChecked() {
        return checked;
    }

    @JsonProperty("checked")
    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    @JsonProperty("day_order")
    public Integer getDayOrder() {
        return dayOrder;
    }

    @JsonProperty("day_order")
    public void setDayOrder(Integer dayOrder) {
        this.dayOrder = dayOrder;
    }

    @JsonProperty("project_id")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("due_date_utc")
    public String getDueDateUtc() {
        return dueDateUtc;
    }

    @JsonProperty("due_date_utc")
    public void setDueDateUtc(String dueDateUtc) {
        this.dueDateUtc = dueDateUtc;
    }

    @JsonProperty("item_order")
    public Integer getItemOrder() {
        return itemOrder;
    }

    @JsonProperty("item_order")
    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    @JsonProperty("collapsed")
    public Integer getCollapsed() {
        return collapsed;
    }

    @JsonProperty("collapsed")
    public void setCollapsed(Integer collapsed) {
        this.collapsed = collapsed;
    }

    @JsonProperty("in_history")
    public Integer getInHistory() {
        return inHistory;
    }

    @JsonProperty("in_history")
    public void setInHistory(Integer inHistory) {
        this.inHistory = inHistory;
    }

    @JsonProperty("indent")
    public Integer getIndent() {
        return indent;
    }

    @JsonProperty("indent")
    public void setIndent(Integer indent) {
        this.indent = indent;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("sync_id")
    public Object getSyncId() {
        return syncId;
    }

    @JsonProperty("sync_id")
    public void setSyncId(Object syncId) {
        this.syncId = syncId;
    }

    @JsonProperty("is_archived")
    public Integer getIsArchived() {
        return isArchived;
    }

    @JsonProperty("is_archived")
    public void setIsArchived(Integer isArchived) {
        this.isArchived = isArchived;
    }

    @JsonProperty("date_lang")
    public String getDateLang() {
        return dateLang;
    }

    @JsonProperty("date_lang")
    public void setDateLang(String dateLang) {
        this.dateLang = dateLang;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("is_deleted")
    public Integer getIsDeleted() {
        return isDeleted;
    }

    @JsonProperty("is_deleted")
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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
