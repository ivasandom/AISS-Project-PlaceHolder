
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
    "resource_type",
    "file_url",
    "file_type",
    "file_name"
})
public class Attachment {

    @JsonProperty("resource_type")
    private String resourceType;
    @JsonProperty("file_url")
    private String fileUrl;
    @JsonProperty("file_type")
    private String fileType;
    @JsonProperty("file_name")
    private String fileName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("resource_type")
    public String getResourceType() {
        return resourceType;
    }

    @JsonProperty("resource_type")
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @JsonProperty("file_url")
    public String getFileUrl() {
        return fileUrl;
    }

    @JsonProperty("file_url")
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @JsonProperty("file_type")
    public String getFileType() {
        return fileType;
    }

    @JsonProperty("file_type")
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @JsonProperty("file_name")
    public String getFileName() {
        return fileName;
    }

    @JsonProperty("file_name")
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
