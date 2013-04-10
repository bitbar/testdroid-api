package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIList;
import com.testdroid.api.APIListResource;
import com.testdroid.api.APISort;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APIProject extends APIEntity {
    
    @XmlType(name = "projectType")
    public static enum Type { ANDROID, CTS, IOS, UIAUTOMATOR, REMOTECONTROL }
    
    private String name;
    private String description;
    private Type type;
    private boolean common;
    private Long sharedById;

    public APIProject() {}
    public APIProject(Long id, String name, String description, Type type, Long sharedById, boolean common) {
        super(id);
        this.name = name;
        this.description = description;
        this.type = type;
        this.sharedById = sharedById;
        this.common = common;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isCommon() {
        return common;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }

    /**
     * Returns user ID sharing this project or null if project is owned or common.
     */
    public Long getSharedById() {
        return sharedById;
    }

    public void setSharedById(Long sharedById) {
        this.sharedById = sharedById;
    }


    
    private APITestRunConfig testRunConfig;

    private String getConfigURI() { return selfURI + "/config"; };
    private String getJobConfigURI() { return selfURI + "/job-config"; };
    private String getFilesURI() { return selfURI + "/files"; };
    private String getIconURI() { return selfURI + "/icon"; };
    private String getSharingsURI() { return selfURI + "/sharings"; };
    private String getTrendsURI() { return selfURI + "/trends"; };
    private String getReportsURI() { return selfURI + "/reports/%s"; };
    private String getRunsURI() { return selfURI + "/runs"; };

    @JsonIgnore
    public APITestRunConfig getTestRunConfig() throws APIException {
        if(testRunConfig == null) {
            testRunConfig = getResource(getConfigURI(), APITestRunConfig.class).getEntity();
        }
        return testRunConfig;
    }
    
    public APITestRun run() throws APIException {
        return postResource(getRunsURI(), null, APITestRun.class);
    }
    
    public void update() throws APIException {
        APIProject project = postResource(selfURI, null, APIProject.class);
        // rewrite values
    }

    @JsonIgnore
    public APIListResource<APIList.TestRunList> getTestRunsResource() throws APIException {
        return getListResource(getRunsURI(), APIList.TestRunList.class);
    }
    
    @JsonIgnore
    public APIListResource<APIList.ProjectList> getTestRunsResource(long offset, long limit, String search, APISort sort) throws APIException {
        return getListResource(getRunsURI(), offset, limit, search, sort, APIList.ProjectList.class);
    }

}
