package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APINotificationEmail extends APIEntity {
    
    @XmlType(name = "notificationEmailType")
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public APIProject getProject() {
        return project;
    }

    public void setProject(APIProject project) {
        this.project = project;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
}
