package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import javax.xml.bind.annotation.XmlRootElement;

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
    
}
