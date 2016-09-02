package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

@Generated("org.jsonschema2pojo")
@org.codehaus.jackson.annotate.JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

	private String name;
	private String path;
	private String sha;
	private String url;
	private String gitUrl;
	private String htmlUrl;
	@JsonProperty("repository")
	private Repository repository;
	private Double score;
	@JsonProperty("text_matches")
	private List<TextMatch> textMatches = new ArrayList<TextMatch>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 
	 * @param path
	 *            The path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 
	 * @return The sha
	 */
	public String getSha() {
		return sha;
	}

	/**
	 * 
	 * @param sha
	 *            The sha
	 */
	public void setSha(String sha) {
		this.sha = sha;
	}

	/**
	 * 
	 * @return The url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url
	 *            The url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 
	 * @return The gitUrl
	 */
	public String getGitUrl() {
		return gitUrl;
	}

	/**
	 * 
	 * @param gitUrl
	 *            The git_url
	 */
	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	/**
	 * 
	 * @return The htmlUrl
	 */
	public String getHtmlUrl() {
		return htmlUrl;
	}

	/**
	 * 
	 * @param htmlUrl
	 *            The html_url
	 */
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	/**
	 * 
	 * @return The repository
	 */
	public Repository getRepository() {
		return repository;
	}

	/**
	 * 
	 * @param repository
	 *            The repository
	 */
	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	/**
	 * 
	 * @return The score
	 */
	public Double getScore() {
		return score;
	}

	/**
	 * 
	 * @param score
	 *            The score
	 */
	public void setScore(Double score) {
		this.score = score;
	}

	/**
	 * 
	 * @return The textMatches
	 */
	public List<TextMatch> getTextMatches() {
		return textMatches;
	}

	/**
	 * 
	 * @param textMatches
	 *            The text_matches
	 */
	public void setTextMatches(List<TextMatch> textMatches) {
		this.textMatches = textMatches;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
