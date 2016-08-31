package com.example;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

@Generated("org.jsonschema2pojo")
@org.codehaus.jackson.annotate.JsonIgnoreProperties(ignoreUnknown=true)
public class Repository {
	@JsonProperty("name")
	private String name;
	@JsonProperty("full_name")
	private String fullName;
	@JsonProperty("owner")
	private Owner owner;

	/**
	 * 
	 * @return The id
	 */

	/**
	 * 
	 * @param id
	 *            The id
	 */

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
	 * @return The fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 
	 * @param fullName
	 *            The full_name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 
	 * @return The owner
	 */
	public Owner getOwner() {
		return owner;
	}

	/**
	 * 
	 * @param owner
	 *            The owner
	 */
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	/**
	 * 
	 * @return The _private
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
