package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Damian Sniezek <damian.sniezek@smartbear.com>
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIDeviceModelPoolStatistics extends APIEntity {

    private Integer numberOfOnlineDeviceModels;

    private Long numberOfWaitingTests;

    private Long numberOfAvailableBrowsers;

    public APIDeviceModelPoolStatistics() {

    }

    public APIDeviceModelPoolStatistics(
            Integer numberOfOnlineDeviceModels, Long numberOfWaitingTests, Long numberOfAvailableBrowsers) {
        this.numberOfOnlineDeviceModels = numberOfOnlineDeviceModels;
        this.numberOfWaitingTests = numberOfWaitingTests;
        this.numberOfAvailableBrowsers = numberOfAvailableBrowsers;
    }

    public Integer getNumberOfOnlineDeviceModels() {
        return numberOfOnlineDeviceModels;
    }

    public Long getNumberOfAvailableBrowsers() {
        return numberOfAvailableBrowsers;
    }

    public Long getNumberOfWaitingTests() {
        return numberOfWaitingTests;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceModelPoolStatistics original = (APIDeviceModelPoolStatistics) from;
        cloneBase(from);
        this.numberOfOnlineDeviceModels = original.numberOfOnlineDeviceModels;
        this.numberOfWaitingTests = original.numberOfWaitingTests;
        this.numberOfAvailableBrowsers = original.numberOfAvailableBrowsers;
    }
}
