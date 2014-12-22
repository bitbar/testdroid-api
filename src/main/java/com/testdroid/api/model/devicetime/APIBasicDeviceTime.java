package com.testdroid.api.model.devicetime;

import com.testdroid.api.APIEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class APIBasicDeviceTime extends APIEntity {

    private Long deviceTime;

    public APIBasicDeviceTime() {

    }

    public APIBasicDeviceTime(Long deviceTime) {
        this.deviceTime = deviceTime;
    }

    public Long getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(Long deviceTime) {
        this.deviceTime = deviceTime;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIBasicDeviceTime apiUserDeviceTime = (APIBasicDeviceTime) from;
        cloneBase(from);
        this.deviceTime = apiUserDeviceTime.deviceTime;
    }
}
