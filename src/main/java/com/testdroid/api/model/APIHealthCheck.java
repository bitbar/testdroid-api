package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIHealthCheck extends APIEntity {

    private boolean dbHealthy;

    private boolean rabbitMQHealthy;

    private int status;

    public APIHealthCheck() {
    }

    public APIHealthCheck(boolean dbHealthy, boolean rabbitmqHealthy, int status) {
        this.dbHealthy = dbHealthy;
        this.rabbitMQHealthy = rabbitmqHealthy;
        this.status = status;
    }

    public boolean isDbHealthy() {
        return dbHealthy;
    }

    public void setDbHealthy(boolean dbHealthy) {
        this.dbHealthy = dbHealthy;
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
        this.rabbitMQHealthy = original.rabbitMQHealthy;
        this.status = original.status;
    }
}
