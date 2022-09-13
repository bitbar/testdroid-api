package com.testdroid.api.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class ClientSideReadyMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String BROWSER_VERSION = "browserVersion";

    public static final String PLATFORM = "platform";

    private String address;

    private String app;

    private String brokerSessionId;

    private Map<String, String> overrides;

    private int port;

    public ClientSideReadyMessage() {
    }

    public ClientSideReadyMessage(
            String address, String app, String brokerSessionId, Map<String, String> overrides, int port) {
        this.address = address;
        this.app = app;
        this.brokerSessionId = brokerSessionId;
        this.overrides = overrides;
        this.port = port;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getBrokerSessionId() {
        return brokerSessionId;
    }

    public void setBrokerSessionId(String sessionId) {
        this.brokerSessionId = sessionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Map<String, String> getOverrides() {
        return overrides;
    }

    public void setOverrides(Map<String, String> overrides) {
        this.overrides = overrides;
    }

}
