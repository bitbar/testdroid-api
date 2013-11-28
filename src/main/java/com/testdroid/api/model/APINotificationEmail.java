package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIView;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonView;

/**
 *
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APINotificationEmail extends APIEntity {
    
    @XmlType(namespace = "APINotificationEmail")
    public static enum Type {
        ALWAYS, ON_FAILURE;
        
        public String getDisplayName() {
            switch(this) {
                case ALWAYS: return "always";
                case ON_FAILURE: return "on failure";
                default: return "";
            }
        }
    }
    
    private String email;
    private APIProject project;
    private Type type;

    public APINotificationEmail() {}

    public APINotificationEmail(Long id, String email, APIProject project, Type type) {
        super(id);
        this.email = email;
        this.project = project;
        this.type = type;
    }

    @JsonView(value = { APIView.ProjectNotificationView.class, APIView.UserNotificationView.class })
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonView(APIView.UserNotificationView.class)
    public APIProject getProject() {
        return project;
    }

    public void setProject(APIProject project) {
        this.project = project;
    }

    @JsonView(value = { APIView.ProjectNotificationView.class, APIView.UserNotificationView.class })
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @JsonIgnore
    public void delete() throws APIException {
        deleteResource(selfURI);
    }
    
    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APINotificationEmail apiNotificationEmail = (APINotificationEmail) from;
        cloneBase(from);
        this.email = apiNotificationEmail.email;
        this.project = apiNotificationEmail.project;
        this.type = apiNotificationEmail.type;
    }
}
