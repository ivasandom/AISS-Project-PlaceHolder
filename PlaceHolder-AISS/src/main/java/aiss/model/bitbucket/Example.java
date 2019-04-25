package aiss.model.bitbucket;

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
    "size",
    "limit",
    "isLastPage",
    "values",
    "start",
    "filter",
    "nextPageStart"
})
public class Example {

    @JsonProperty("size")
    private Integer size;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("isLastPage")
    private Boolean isLastPage;
    @JsonProperty("values")
    private List<Value> values = null;
    @JsonProperty("start")
    private Integer start;
    @JsonProperty("filter")
    private Object filter;
    @JsonProperty("nextPageStart")
    private Integer nextPageStart;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("isLastPage")
    public Boolean getIsLastPage() {
        return isLastPage;
    }

    @JsonProperty("isLastPage")
    public void setIsLastPage(Boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    @JsonProperty("values")
    public List<Value> getValues() {
        return values;
    }

    @JsonProperty("values")
    public void setValues(List<Value> values) {
        this.values = values;
    }

    @JsonProperty("start")
    public Integer getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(Integer start) {
        this.start = start;
    }

    @JsonProperty("filter")
    public Object getFilter() {
        return filter;
    }

    @JsonProperty("filter")
    public void setFilter(Object filter) {
        this.filter = filter;
    }

    @JsonProperty("nextPageStart")
    public Integer getNextPageStart() {
        return nextPageStart;
    }

    @JsonProperty("nextPageStart")
    public void setNextPageStart(Integer nextPageStart) {
        this.nextPageStart = nextPageStart;
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
