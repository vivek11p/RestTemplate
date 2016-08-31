package com.jcg.example.bean;
import org.codehaus.jackson.annotate.JsonProperty;
public class User {
	
    private String name,full_name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
    
}
