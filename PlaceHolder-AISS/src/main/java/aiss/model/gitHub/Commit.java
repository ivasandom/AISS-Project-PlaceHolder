
package aiss.model.gitHub;

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
    "sha",
    "node_id",
    "commit",
    "author",
    "parents",
    "url",
    "committer"
})
public class Commit {

    @JsonProperty("sha")
    private String sha;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("commit")
    private Commit_ commit;
    @JsonProperty("author")
    private Author_ author;
    @JsonProperty("parents")
    private List<Parent> parents = null;
    @JsonProperty("url")
    private String url;
    @JsonProperty("committer")
    private Committer_ committer;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sha")
    public String getSha() {
        return sha;
    }

    @JsonProperty("sha")
    public void setSha(String sha) {
        this.sha = sha;
    }

    @JsonProperty("node_id")
    public String getNodeId() {
        return nodeId;
    }

    @JsonProperty("node_id")
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @JsonProperty("commit")
    public Commit_ getCommit() {
        return commit;
    }

    @JsonProperty("commit")
    public void setCommit(Commit_ commit) {
        this.commit = commit;
    }

    @JsonProperty("author")
    public Author_ getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author_ author) {
        this.author = author;
    }

    @JsonProperty("parents")
    public List<Parent> getParents() {
        return parents;
    }

    @JsonProperty("parents")
    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("committer")
    public Committer_ getCommitter() {
        return committer;
    }

    @JsonProperty("committer")
    public void setCommitter(Committer_ committer) {
        this.committer = committer;
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
