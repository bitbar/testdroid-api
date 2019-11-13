package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class APIDeviceSessionConfig extends APIEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String adbVersion;

    private Long deviceModelId;

    private APIDeviceSession.Type type = APIDeviceSession.Type.MANUAL;

    public String getAdbVersion() {
        return adbVersion;
    }

    public void setAdbVersion(String adbVersion) {
        this.adbVersion = adbVersion;
    }

    public Long getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(Long deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public void setType(APIDeviceSession.Type type) {
        this.type = type;
    }

    public APIDeviceSession.Type getType() {
        return type;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceSessionConfig prototype = (APIDeviceSessionConfig) from;
        cloneBase(from);
        this.adbVersion = prototype.adbVersion;
        this.type = prototype.type;
        this.deviceModelId = prototype.deviceModelId;
    }
}
