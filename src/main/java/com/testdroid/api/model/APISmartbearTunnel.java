package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Krzysztof Mościchowski <krzysztof.moscichowski@smartbear.com>
 */
@XmlRootElement
public class APISmartbearTunnel extends APIEntity {

    private Boolean active;

    private String externalId;

    private String user;

    private String domain;

    private String state;

    private Date createdAt;

    private String tunnelType;

    private String staticServerDirectory;

    private String proxyIp;

    private String proxyPort;

    private String tunnelSource;

    private Boolean bypassPublicHosts;

    private Boolean acceptAllCerts;

    public APISmartbearTunnel() {
    }

    public APISmartbearTunnel(
            Boolean active, String externalId, String user, String domain, String state, Date createdAt,
            String tunnelType, String staticServerDirectory, String proxyIp, String proxyPort,
            String tunnelSource, Boolean bypassPublicHosts, Boolean acceptAllCerts) {
        this.active = active;
        this.externalId = externalId;
        this.user = user;
        this.domain = domain;
        this.state = state;
        this.createdAt = createdAt;
        this.tunnelType = tunnelType;
        this.staticServerDirectory = staticServerDirectory;
        this.proxyIp = proxyIp;
        this.proxyPort = proxyPort;
        this.tunnelSource = tunnelSource;
        this.bypassPublicHosts = bypassPublicHosts;
        this.acceptAllCerts = acceptAllCerts;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTunnelType() {
        return tunnelType;
    }

    public void setTunnelType(String tunnelType) {
        this.tunnelType = tunnelType;
    }

    public String getStaticServerDirectory() {
        return staticServerDirectory;
    }

    public void setStaticServerDirectory(String staticServerDirectory) {
        this.staticServerDirectory = staticServerDirectory;
    }

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getTunnelSource() {
        return tunnelSource;
    }

    public void setTunnelSource(String tunnelSource) {
        this.tunnelSource = tunnelSource;
    }

    public Boolean isBypassPublicHosts() {
        return bypassPublicHosts;
    }

    public void setBypassPublicHosts(Boolean bypassPublicHosts) {
        this.bypassPublicHosts = bypassPublicHosts;
    }

    public Boolean isAcceptAllCerts() {
        return acceptAllCerts;
    }

    public void setAcceptAllCerts(Boolean acceptAllCerts) {
        this.acceptAllCerts = acceptAllCerts;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APISmartbearTunnel tunnel = (APISmartbearTunnel) from;
        cloneBase(from);
        this.externalId = tunnel.externalId;
        this.active = tunnel.active;
        this.user = tunnel.user;
        this.domain = tunnel.domain;
        this.state = tunnel.state;
        this.createdAt = tunnel.createdAt;
        this.tunnelType = tunnel.tunnelType;
        this.staticServerDirectory = tunnel.staticServerDirectory;
        this.proxyIp = tunnel.proxyIp;
        this.proxyPort = tunnel.proxyPort;
        this.tunnelSource = tunnel.tunnelSource;
        this.bypassPublicHosts = tunnel.bypassPublicHosts;
        this.acceptAllCerts = tunnel.acceptAllCerts;
    }

}
