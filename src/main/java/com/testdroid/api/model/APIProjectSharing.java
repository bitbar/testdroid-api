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

    public APIProjectSharing() {}
    public APIProjectSharing(Long id, Long userId) {
        super(id);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIProjectSharing apiProjectSharing = (APIProjectSharing) from;
        cloneBase(from);
        this.userId = apiProjectSharing.userId;
    }
    
}
