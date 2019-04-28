package aiss.model.gitlab;

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
	"type",
	"path",
	"mode",
	"sha",
	"email",
	"commits",
	"additions",
	"deletions",
	"file_name",
	"file_path",
	"size",
	"encoding",
	"content",
	"content_sha256",
	"ref",
	"blob_id",
	"commit_id",
	"last_commit_id"
})
public class GitLabRepository {
	@JsonProperty("id")
	public String id;
	@JsonProperty("name")
	public String name;
	@JsonProperty("type")
	public String type;
	@JsonProperty("path")
	public String path;
	@JsonProperty("mode")
	public String mode;
	@JsonProperty("sha")
	public String sha;
	@JsonProperty("email")
	public String email;
	@JsonProperty("commits")
	public Integer commits;
	@JsonProperty("additions")
	public Integer additions;
	@JsonProperty("deletions")
	public Integer deletions;
	@JsonProperty("file_name")
	public String fileName;
	@JsonProperty("file_path")
	public String filePath;
	@JsonProperty("size")
	public Integer size;
	@JsonProperty("encoding")
	public String encoding;
	@JsonProperty("content")
	public String content;
	@JsonProperty("content_sha256")
	public String contentSha256;
	@JsonProperty("ref")
	public String ref;
	@JsonProperty("blob_id")
	public String blobId;
	@JsonProperty("commit_id")
	public String commitId;
	@JsonProperty("last_commit_id")
	public String lastCommitId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();


	@JsonProperty("id")
	public String getId() {
		return id;
	}
	@JsonProperty("id")
	public void setId(String id) {
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
	@JsonProperty("type")
	public String getType() {
		return type;
	}
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}
	@JsonProperty("path")
	public String getPath() {
		return path;
	}
	@JsonProperty("path")
	public void setPath(String path) {
		this.path = path;
	}
	@JsonProperty("mode")
	public String getMode() {
		return mode;
	}
	@JsonProperty("mode")
	public void setMode(String mode) {
		this.mode = mode;
	}
	@JsonProperty("sha")
	public String getSha() {
		return sha;
	}
	@JsonProperty("sha")
	public void setSha(String sha) {
		this.sha = sha;
	}
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}
	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonProperty("commits")
	public Integer getCommits() {
		return commits;
	}
	@JsonProperty("commits")
	public void setCommits(Integer commits) {
		this.commits = commits;
	}
	@JsonProperty("additions")
	public Integer getAdditions() {
		return additions;
	}
	@JsonProperty("additions")
	public void setAdditions(Integer additions) {
		this.additions = additions;
	}
	@JsonProperty("deletions")
	public Integer getDeletions() {
		return deletions;
	}
	@JsonProperty("deletions")
	public void setDeletions(Integer deletions) {
		this.deletions = deletions;
	}
	@JsonProperty("file_name")
	public String getFileName() {
		return fileName;
	}
	@JsonProperty("file_name")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@JsonProperty("file_path")
	public String getFilePath() {
		return filePath;
	}
	@JsonProperty("file_path")
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@JsonProperty("size")
	public Integer getSize() {
		return size;
	}
	@JsonProperty("size")
	public void setSize(Integer size) {
		this.size = size;
	}
	@JsonProperty("encoding")
	public String getEncoding() {
		return encoding;
	}
	@JsonProperty("encoding")
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	@JsonProperty("content")
	public String getContent() {
		return content;
	}
	@JsonProperty("content")
	public void setContent(String content) {
		this.content = content;
	}
	@JsonProperty("content_sha_256")
	public String getContentSha256() {
		return contentSha256;
	}
	@JsonProperty("content_sha_256")
	public void setContentSha256(String contentSha256) {
		this.contentSha256 = contentSha256;
	}
	@JsonProperty("ref")
	public String getRef() {
		return ref;
	}
	@JsonProperty("ref")
	public void setRef(String ref) {
		this.ref = ref;
	}
	@JsonProperty("blob_id")
	public String getBlobId() {
		return blobId;
	}
	@JsonProperty("blob_id")
	public void setBlobId(String blobId) {
		this.blobId = blobId;
	}
	@JsonProperty("commit_id")
	public String getCommitId() {
		return commitId;
	}
	@JsonProperty("commit_id")
	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}
	@JsonProperty("last_commit_id")
	public String getLastCommitId() {
		return lastCommitId;
	}
	@JsonProperty("last_commit_id")
	public void setLastCommitId(String lastCommitId) {
		this.lastCommitId = lastCommitId;
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


