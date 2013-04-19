package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APISoftwareVersion extends APIEntity {
    private String releaseVersion;
    private Integer apiLevel;

    public APISoftwareVersion() {}

    public APISoftwareVersion(Long id, String releaseVersion, Integer apiLevel) {
        super(id);
        this.releaseVersion = releaseVersion;
        this.apiLevel = apiLevel;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public Integer getApiLevel() {
        return apiLevel;
    }

    public void setApiLevel(Integer apiLevel) {
        this.apiLevel = apiLevel;
    }
        
}
