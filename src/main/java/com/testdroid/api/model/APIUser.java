package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIList.ClusterList;
import com.testdroid.api.APIListResource;
import com.testdroid.api.APIList.ProjectList;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APIUser extends APIEntity {
    private String email;
    private String name;

    public APIUser() {}
    public APIUser(Long id, String email, String name) {
        super(id);
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private String getProjectsURI() { return selfURI + "/projects"; };
    
    @JsonIgnore
    public APIListResource<ProjectList> getProjectsResource() throws APIException {
        return getListResource(getProjectsURI(), ProjectList.class);
    }
    
    @JsonIgnore
    public APIListResource<ProjectList> getProjectsResource(long offset, long limit, String search) throws APIException {
        return getListResource(getProjectsURI(), offset, limit, search, ProjectList.class);
    }
    
    public APIProject getProject(Long id) throws APIException {
        return getResource(selfURI + "/projects/" + id, APIProject.class).getEntity();
    }
    
    @JsonIgnore
    public APIListResource<ClusterList> getClustersResource() throws APIException {
        return getListResource(getProjectsURI(), ClusterList.class);
    }
    
    @JsonIgnore
    public APIListResource<ClusterList> getClustersResource(long offset, long limit, String search) throws APIException {
        return getListResource(getProjectsURI(), offset, limit, search, ClusterList.class);
    }
}
