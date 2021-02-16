package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@smartbear.com>
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIDeviceModelPool extends APIEntity {

    private String name;

    private String osVersion;

    private String location;

    private Short numberOfBrowsers;

    private Short minAvailable;

    private Short maxTotal;

    private Short running;

    private Date createTime;

    public APIDeviceModelPool() {

    }

    public APIDeviceModelPool(
            Long id, String name, String osVersion, String location,
            Short numberOfBrowsers, Short minAvailable, Short maxTotal, Short running, LocalDateTime createTime) {
        super(id);
        this.name = name;
        this.osVersion = osVersion;
        this.location = location;
        this.numberOfBrowsers = numberOfBrowsers;
        this.minAvailable = minAvailable;
        this.maxTotal = maxTotal;
        this.running = running;
        this.createTime = TimeConverter.toDate(createTime);
    }

    public String getName() {
        return name;
    }

    public Short getRunning() {
        return running;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public Short getNumberOfBrowsers() {
        return numberOfBrowsers;
    }

    public Short getMinAvailable() {
        return minAvailable;
    }

    public Short getMaxTotal() {
        return maxTotal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getLocation() {
        return location;
    }

    public void setMinAvailable(Short minAvailable) {
        this.minAvailable = minAvailable;
    }

    public void setMaxTotal(Short maxTotal) {
        this.maxTotal = maxTotal;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceModelPool original = (APIDeviceModelPool) from;
        cloneBase(from);
        this.name = original.name;
        this.osVersion = original.osVersion;
        this.location = original.location;
        this.numberOfBrowsers = original.numberOfBrowsers;
        this.minAvailable = original.minAvailable;
        this.maxTotal = original.maxTotal;
        this.running = original.running;
        this.createTime = original.createTime;
    }
}
