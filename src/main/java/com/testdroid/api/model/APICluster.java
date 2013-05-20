package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APICluster extends APIEntity {
    
    @XmlType(namespace = "APICluster", name = "APIClusterState")
    public static enum State { OFFLINE, ONLINE };
    
    private String name;
      
    private String url;    
  
    private State state = State.OFFLINE;
    
    private Date stateTime = new Date();
    
    private Date stateChangeTime = new Date();        
    
    private Boolean enabled = true;

    public APICluster() {        
    }
    
    public APICluster(String name) {
        this.name = name;
    }
    
    public APICluster(Long id, String name, String url, State state, Date stateTime, Date stateChangeTime, Boolean enabled) {
        super(id);
        this.name = name;
        this.url = url;
        this.state = state;
        this.stateTime = stateTime;
        this.stateChangeTime = stateChangeTime;
        this.enabled = enabled;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getStateTime() {
        return stateTime;
    }

    public void setStateTime(Date stateTime) {
        this.stateTime = stateTime;
    }

    public Date getStateChangeTime() {
        return stateChangeTime;
    }

    public void setStateChangeTime(Date stateChangeTime) {
        this.stateChangeTime = stateChangeTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    
}
