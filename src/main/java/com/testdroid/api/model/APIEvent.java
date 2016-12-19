package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIEvent extends APIEntity {

    @XmlType(namespace = "APIEvent")
    public static enum ApplicationType {

        RECORDER
    }

    @XmlType(namespace = "APIEvent")
    public static enum EventType {

        RECORDER_OPEN_WIZARD,
        RECORDER_RUN_IN_CLOUD,
        RECORDER_FINISH_WIZARD,
        RECORDER_RUN_APPCRAWLER
    }

    private ApplicationType applicationType;

    private EventType eventType;

    public APIEvent() {
    }

    public APIEvent(ApplicationType applicationType, EventType eventType) {
        this.applicationType = applicationType;
        this.eventType = eventType;
    }

    public APIEvent(Long id, ApplicationType applicationType, EventType eventType) {
        super(id);
        this.applicationType = applicationType;
        this.eventType = eventType;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIEvent apiEvent = (APIEvent) from;
        cloneBase(from);
        this.applicationType = apiEvent.applicationType;
        this.eventType = apiEvent.eventType;
    }
}
