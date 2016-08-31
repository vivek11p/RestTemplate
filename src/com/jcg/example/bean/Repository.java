package com.jcg.example.bean;

import org.codehaus.jackson.annotate.JsonProperty;

@org.codehaus.jackson.annotate.JsonIgnoreProperties(ignoreUnknown=true)
public class Repository {
   private Integer id;
   private String name;
   @JsonProperty("full_name")
   private String fullName;
@Override
public String toString() {
	return "Repository [id=" + id + ", name=" + name + ", fullName=" + fullName
			+ "]";
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
}
