package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIList;
import com.testdroid.api.APIListResource;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
public class APIProject extends APIEntity {
    private Long id;
    private APITestRunConfig testRunConfig;

    private String getConfigURI() { return selfURI + "/config"; };
    private String getJobConfigURI() { return selfURI + "/job-config"; };
    private String getFilesURI() { return selfURI + "/files"; };
    private String getIconURI() { return selfURI + "/icon"; };
    private String getSharingsURI() { return selfURI + "/sharings"; };
    private String getTrendsURI() { return selfURI + "/trends"; };
    private String getReportsURI() { return selfURI + "/reports/%s"; };
    private String getRunsURI() { return selfURI + "/runs"; };
    
    @Override
    public Long getId() {
        return id;
    }
    
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
    public APIListResource<APIList.ProjectList> getTestRunsResource(long offset, long limit, String search) throws APIException {
        return getListResource(getRunsURI(), offset, limit, search, APIList.ProjectList.class);
    }

}
