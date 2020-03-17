package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIHealthCheck extends APIEntity {

    private boolean dbHealthy;

    private boolean jmsHealthy;

    private boolean rabbitMQHealthy;

    private int status;

    public APIHealthCheck() {
    }

    public APIHealthCheck(boolean dbHealthy, boolean jmsHealthy, boolean rabbitmqHealthy, int status) {
        this.dbHealthy = dbHealthy;
        this.jmsHealthy = jmsHealthy;
        this.rabbitMQHealthy = rabbitmqHealthy;
        this.status = status;
    }

    public boolean isDbHealthy() {
        return dbHealthy;
    }

    public void setDbHealthy(boolean dbHealthy) {
        this.dbHealthy = dbHealthy;
    }

    public boolean isJmsHealthy() {
        return jmsHealthy;
    }

    public void setJmsHealthy(boolean jmsHealthy) {
        this.jmsHealthy = jmsHealthy;
    }

    public boolean isRabbitMQHealthy() {
        return rabbitMQHealthy;
    }

    public void setRabbitMQHealthy(boolean rabbitmqHealthy) {
        this.rabbitMQHealthy = rabbitmqHealthy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIHealthCheck original = (APIHealthCheck) from;
        cloneBase(from);
        this.dbHealthy = original.dbHealthy;
        this.jmsHealthy = original.jmsHealthy;
        this.rabbitMQHealthy = original.rabbitMQHealthy;
        this.status = original.status;
    }
}
