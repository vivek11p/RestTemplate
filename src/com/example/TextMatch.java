
package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

@Generated("org.jsonschema2pojo")
@org.codehaus.jackson.annotate.JsonIgnoreProperties(ignoreUnknown=true)
public class TextMatch {
	@JsonProperty("object_url")
    private String objectUrl;
	@JsonProperty("object_type")
    private String objectType;
	@JsonProperty("property")
    private String property;
	@JsonProperty("fragment")
    private String fragment;
	@JsonProperty("matches")
    private List<Match> matches = new ArrayList<Match>();
    

    /**
     * 
     * @return
     *     The objectUrl
     */
    public String getObjectUrl() {
        return objectUrl;
    }

    /**
     * 
     * @param objectUrl
     *     The object_url
     */
    public void setObjectUrl(String objectUrl) {
        this.objectUrl = objectUrl;
    }

    /**
     * 
     * @return
     *     The objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * 
     * @param objectType
     *     The object_type
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     * 
     * @return
     *     The property
     */
    public String getProperty() {
        return property;
    }

    /**
     * 
     * @param property
     *     The property
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * 
     * @return
     *     The fragment
     */
    public String getFragment() {
        return fragment;
    }

    /**
     * 
     * @param fragment
     *     The fragment
     */
    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    /**
     * 
     * @return
     *     The matches
     */
    public List<Match> getMatches() {
        return matches;
    }

    /**
     * 
     * @param matches
     *     The matches
     */
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

   

}
