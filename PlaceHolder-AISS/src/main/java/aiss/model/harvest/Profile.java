
package aiss.model.harvest;

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
    "id",
    "first_name",
    "last_name",
    "email",
    "telephone",
    "timezone",
    "has_access_to_all_future_projects",
    "is_contractor",
    "is_admin",
    "is_project_manager",
    "can_see_rates",
    "can_create_projects",
    "can_create_invoices",
    "is_active",
    "created_at",
    "updated_at",
    "weekly_capacity",
    "default_hourly_rate",
    "cost_rate",
    "roles",
    "avatar_url"
})
public class Profile {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("telephone")
    private String telephone;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("has_access_to_all_future_projects")
    private Boolean hasAccessToAllFutureProjects;
    @JsonProperty("is_contractor")
    private Boolean isContractor;
    @JsonProperty("is_admin")
    private Boolean isAdmin;
    @JsonProperty("is_project_manager")
    private Boolean isProjectManager;
    @JsonProperty("can_see_rates")
    private Boolean canSeeRates;
    @JsonProperty("can_create_projects")
    private Boolean canCreateProjects;
    @JsonProperty("can_create_invoices")
    private Boolean canCreateInvoices;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("weekly_capacity")
    private Long weeklyCapacity;
    @JsonProperty("default_hourly_rate")
    private Double defaultHourlyRate;
    @JsonProperty("cost_rate")
    private Double costRate;
    @JsonProperty("roles")
    private List<String> roles = null;
    @JsonProperty("avatar_url")
    private String avatarUrl;
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

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("telephone")
    public String getTelephone() {
        return telephone;
    }

    @JsonProperty("telephone")
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("has_access_to_all_future_projects")
    public Boolean getHasAccessToAllFutureProjects() {
        return hasAccessToAllFutureProjects;
    }

    @JsonProperty("has_access_to_all_future_projects")
    public void setHasAccessToAllFutureProjects(Boolean hasAccessToAllFutureProjects) {
        this.hasAccessToAllFutureProjects = hasAccessToAllFutureProjects;
    }

    @JsonProperty("is_contractor")
    public Boolean getIsContractor() {
        return isContractor;
    }

    @JsonProperty("is_contractor")
    public void setIsContractor(Boolean isContractor) {
        this.isContractor = isContractor;
    }

    @JsonProperty("is_admin")
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    @JsonProperty("is_admin")
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @JsonProperty("is_project_manager")
    public Boolean getIsProjectManager() {
        return isProjectManager;
    }

    @JsonProperty("is_project_manager")
    public void setIsProjectManager(Boolean isProjectManager) {
        this.isProjectManager = isProjectManager;
    }

    @JsonProperty("can_see_rates")
    public Boolean getCanSeeRates() {
        return canSeeRates;
    }

    @JsonProperty("can_see_rates")
    public void setCanSeeRates(Boolean canSeeRates) {
        this.canSeeRates = canSeeRates;
    }

    @JsonProperty("can_create_projects")
    public Boolean getCanCreateProjects() {
        return canCreateProjects;
    }

    @JsonProperty("can_create_projects")
    public void setCanCreateProjects(Boolean canCreateProjects) {
        this.canCreateProjects = canCreateProjects;
    }

    @JsonProperty("can_create_invoices")
    public Boolean getCanCreateInvoices() {
        return canCreateInvoices;
    }

    @JsonProperty("can_create_invoices")
    public void setCanCreateInvoices(Boolean canCreateInvoices) {
        this.canCreateInvoices = canCreateInvoices;
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

    @JsonProperty("weekly_capacity")
    public Long getWeeklyCapacity() {
        return weeklyCapacity;
    }

    @JsonProperty("weekly_capacity")
    public void setWeeklyCapacity(Long weeklyCapacity) {
        this.weeklyCapacity = weeklyCapacity;
    }

    @JsonProperty("default_hourly_rate")
    public Double getDefaultHourlyRate() {
        return defaultHourlyRate;
    }

    @JsonProperty("default_hourly_rate")
    public void setDefaultHourlyRate(Double defaultHourlyRate) {
        this.defaultHourlyRate = defaultHourlyRate;
    }

    @JsonProperty("cost_rate")
    public Double getCostRate() {
        return costRate;
    }

    @JsonProperty("cost_rate")
    public void setCostRate(Double costRate) {
        this.costRate = costRate;
    }

    @JsonProperty("roles")
    public List<String> getRoles() {
        return roles;
    }

    @JsonProperty("roles")
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @JsonProperty("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @JsonProperty("avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    // FULL NAME
    public String getFullName() {
    	return firstName + " " + lastName;
    }
    
}
