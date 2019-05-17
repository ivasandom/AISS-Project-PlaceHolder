
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
    "clients",
    "per_page",
    "total_pages",
    "total_entries",
    "next_page",
    "previous_page",
    "page",
    "links"
})
public class Client {

    @JsonProperty("clients")
    private List<Client_> clients = null;
    @JsonProperty("per_page")
    private Long perPage;
    @JsonProperty("total_pages")
    private Long totalPages;
    @JsonProperty("total_entries")
    private Long totalEntries;
    @JsonProperty("next_page")
    private Object nextPage;
    @JsonProperty("previous_page")
    private Object previousPage;
    @JsonProperty("page")
    private Long page;
    @JsonProperty("links")
    private Links links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("clients")
    public List<Client_> getClients() {
        return clients;
    }

    @JsonProperty("clients")
    public void setClients(List<Client_> clients) {
        this.clients = clients;
    }

    @JsonProperty("per_page")
    public Long getPerPage() {
        return perPage;
    }

    @JsonProperty("per_page")
    public void setPerPage(Long perPage) {
        this.perPage = perPage;
    }

    @JsonProperty("total_pages")
    public Long getTotalPages() {
        return totalPages;
    }

    @JsonProperty("total_pages")
    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    @JsonProperty("total_entries")
    public Long getTotalEntries() {
        return totalEntries;
    }

    @JsonProperty("total_entries")
    public void setTotalEntries(Long totalEntries) {
        this.totalEntries = totalEntries;
    }

    @JsonProperty("next_page")
    public Object getNextPage() {
        return nextPage;
    }

    @JsonProperty("next_page")
    public void setNextPage(Object nextPage) {
        this.nextPage = nextPage;
    }

    @JsonProperty("previous_page")
    public Object getPreviousPage() {
        return previousPage;
    }

    @JsonProperty("previous_page")
    public void setPreviousPage(Object previousPage) {
        this.previousPage = previousPage;
    }

    @JsonProperty("page")
    public Long getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Long page) {
        this.page = page;
    }

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links links) {
        this.links = links;
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
