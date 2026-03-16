package com.testdroid.api.model.recorder.processed;

import com.testdroid.api.model.APIDevice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class TestScript {

    private Long deviceSessionId;

    private APIDevice.Platform platform;

    private String device;

    private String app;

    private String hubUrl;

    private Integer screenWidth;

    private Integer screenHeight;

    private List<RecordedAction> actions = new ArrayList<>();

    public List<RecordedAction> getActions() {
        return actions;
    }

    public void setActions(List<RecordedAction> actions) {
        this.actions = actions;
    }

    public void addAction(RecordedAction action) {
        this.actions.add(action);
    }

    public void setApp(String app) {
        this.app = app;
    }

    public APIDevice.Platform getPlatform() {
        return platform;
    }

    public void setPlatform(APIDevice.Platform platform) {
        this.platform = platform;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getApp() {
        return app;
    }

    public String getHubUrl() {
        return hubUrl;
    }

    public void setHubUrl(String hubUrl) {
        this.hubUrl = hubUrl;
    }

    public Long getDeviceSessionId() {
        return deviceSessionId;
    }

    public void setDeviceSessionId(Long deviceSessionId) {
        this.deviceSessionId = deviceSessionId;
    }

    public Integer getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(Integer screenWidth) {
        this.screenWidth = screenWidth;
    }

    public Integer getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(Integer screenHeight) {
        this.screenHeight = screenHeight;
    }
}
