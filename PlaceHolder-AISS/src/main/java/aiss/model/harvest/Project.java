
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
    "name",
    "code",
    "is_active",
    "bill_by",
    "budget",
    "budget_by",
    "budget_is_monthly",
    "notify_when_over_budget",
    "over_budget_notification_percentage",
    "over_budget_notification_date",
    "show_budget_to_all",
    "created_at",
    "updated_at",
    "starts_on",
    "ends_on",
    "is_billable",
    "is_fixed_fee",
    "notes",
    "client",
    "cost_budget",
    "cost_budget_include_expenses",
    "hourly_rate",
    "fee"
})
public class Project {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("bill_by")
    private String billBy;
    @JsonProperty("budget")
    private Double budget;
    @JsonProperty("budget_by")
    private String budgetBy;
    @JsonProperty("budget_is_monthly")
    private Boolean budgetIsMonthly;
    @JsonProperty("notify_when_over_budget")
    private Boolean notifyWhenOverBudget;
    @JsonProperty("over_budget_notification_percentage")
    private Double overBudgetNotificationPercentage;
    @JsonProperty("over_budget_notification_date")
    private Object overBudgetNotificationDate;
    @JsonProperty("show_budget_to_all")
    private Boolean showBudgetToAll;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("starts_on")
    private String startsOn;
    @JsonProperty("ends_on")
    private String endsOn;
    @JsonProperty("is_billable")
    private Boolean isBillable;
    @JsonProperty("is_fixed_fee")
    private Boolean isFixedFee;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("client")
    private Client_ client;
    @JsonProperty("cost_budget")
    private Object costBudget;
    @JsonProperty("cost_budget_include_expenses")
    private Boolean costBudgetIncludeExpenses;
    @JsonProperty("hourly_rate")
    private Double hourlyRate;
    @JsonProperty("fee")
    private Object fee;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("is_active")
    public Boolean getIsActive() {
        return isActive;
    }

    @JsonProperty("is_active")
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @JsonProperty("bill_by")
    public String getBillBy() {
        return billBy;
    }

    @JsonProperty("bill_by")
    public void setBillBy(String billBy) {
        this.billBy = billBy;
    }

    @JsonProperty("budget")
    public Double getBudget() {
        return budget;
    }

    @JsonProperty("budget")
    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @JsonProperty("budget_by")
    public String getBudgetBy() {
        return budgetBy;
    }

    @JsonProperty("budget_by")
    public void setBudgetBy(String budgetBy) {
        this.budgetBy = budgetBy;
    }

    @JsonProperty("budget_is_monthly")
    public Boolean getBudgetIsMonthly() {
        return budgetIsMonthly;
    }

    @JsonProperty("budget_is_monthly")
    public void setBudgetIsMonthly(Boolean budgetIsMonthly) {
        this.budgetIsMonthly = budgetIsMonthly;
    }

    @JsonProperty("notify_when_over_budget")
    public Boolean getNotifyWhenOverBudget() {
        return notifyWhenOverBudget;
    }

    @JsonProperty("notify_when_over_budget")
    public void setNotifyWhenOverBudget(Boolean notifyWhenOverBudget) {
        this.notifyWhenOverBudget = notifyWhenOverBudget;
    }

    @JsonProperty("over_budget_notification_percentage")
    public Double getOverBudgetNotificationPercentage() {
        return overBudgetNotificationPercentage;
    }

    @JsonProperty("over_budget_notification_percentage")
    public void setOverBudgetNotificationPercentage(Double overBudgetNotificationPercentage) {
        this.overBudgetNotificationPercentage = overBudgetNotificationPercentage;
    }

    @JsonProperty("over_budget_notification_date")
    public Object getOverBudgetNotificationDate() {
        return overBudgetNotificationDate;
    }

    @JsonProperty("over_budget_notification_date")
    public void setOverBudgetNotificationDate(Object overBudgetNotificationDate) {
        this.overBudgetNotificationDate = overBudgetNotificationDate;
    }

    @JsonProperty("show_budget_to_all")
    public Boolean getShowBudgetToAll() {
        return showBudgetToAll;
    }

    @JsonProperty("show_budget_to_all")
    public void setShowBudgetToAll(Boolean showBudgetToAll) {
        this.showBudgetToAll = showBudgetToAll;
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

    @JsonProperty("starts_on")
    public String getStartsOn() {
        return startsOn;
    }

    @JsonProperty("starts_on")
    public void setStartsOn(String startsOn) {
        this.startsOn = startsOn;
    }

    @JsonProperty("ends_on")
    public String getEndsOn() {
        return endsOn;
    }

    @JsonProperty("ends_on")
    public void setEndsOn(String endsOn) {
        this.endsOn = endsOn;
    }

    @JsonProperty("is_billable")
    public Boolean getIsBillable() {
        return isBillable;
    }

    @JsonProperty("is_billable")
    public void setIsBillable(Boolean isBillable) {
        this.isBillable = isBillable;
    }

    @JsonProperty("is_fixed_fee")
    public Boolean getIsFixedFee() {
        return isFixedFee;
    }

    @JsonProperty("is_fixed_fee")
    public void setIsFixedFee(Boolean isFixedFee) {
        this.isFixedFee = isFixedFee;
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonProperty("client")
    public Client_ getClient() {
        return client;
    }

    @JsonProperty("client")
    public void setClient(Client_ client) {
        this.client = client;
    }

    @JsonProperty("cost_budget")
    public Object getCostBudget() {
        return costBudget;
    }

    @JsonProperty("cost_budget")
    public void setCostBudget(Object costBudget) {
        this.costBudget = costBudget;
    }

    @JsonProperty("cost_budget_include_expenses")
    public Boolean getCostBudgetIncludeExpenses() {
        return costBudgetIncludeExpenses;
    }

    @JsonProperty("cost_budget_include_expenses")
    public void setCostBudgetIncludeExpenses(Boolean costBudgetIncludeExpenses) {
        this.costBudgetIncludeExpenses = costBudgetIncludeExpenses;
    }

    @JsonProperty("hourly_rate")
    public Double getHourlyRate() {
        return hourlyRate;
    }

    @JsonProperty("hourly_rate")
    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @JsonProperty("fee")
    public Object getFee() {
        return fee;
    }

    @JsonProperty("fee")
    public void setFee(Object fee) {
        this.fee = fee;
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
