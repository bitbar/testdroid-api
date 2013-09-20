package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APIProjectSharing extends APIEntity {
    private Long userId;
    private String userEmail;

    public APIProjectSharing() {}
    public APIProjectSharing(Long id, Long userId, String userEmail) {
        super(id);
        this.userId = userId;
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIProjectSharing apiProjectSharing = (APIProjectSharing) from;
        cloneBase(from);
        this.userId = apiProjectSharing.userId;
        this.userEmail = apiProjectSharing.userEmail;
    }
    
}
