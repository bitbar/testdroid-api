package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIList;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APITestCaseRunStep extends APIEntity {

    @XmlType(namespace = "APITestCaseRunStep")
    public static enum Type {
        ASSERTION,
        CLICK,
        CONFIG,
        DRAG,
        INPUT,
        NAVIGATION,
        OTHER,
        SCROLL,
        UTIL,
        WAIT
    }

    private String description;

    private Long duration;

    private String errorMessage;

    private String fromActivity;

    private APIList<APIScreenshot> screenshots;

    private Type type;

    public APITestCaseRunStep() {
    }

    public APITestCaseRunStep(
            Long id, String description, String fromActivity, String errorMessage, Long duration,
            Type type, APIList<APIScreenshot> screenshots) {
        super(id);
        this.description = description;
        this.fromActivity = fromActivity;
        this.errorMessage = errorMessage;
        this.duration = duration;
        this.type = type;
        this.screenshots = screenshots;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromActivity() {
        return fromActivity;
    }

    public void setFromActivity(String fromActivity) {
        this.fromActivity = fromActivity;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public APIList<APIScreenshot> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(APIList<APIScreenshot> screenshots) {
        this.screenshots = screenshots;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APITestCaseRunStep apiTestCaseRunStep = (APITestCaseRunStep) from;
        cloneBase(from);
        this.description = apiTestCaseRunStep.description;
        this.duration = apiTestCaseRunStep.duration;
        this.errorMessage = apiTestCaseRunStep.errorMessage;
        this.fromActivity = apiTestCaseRunStep.fromActivity;
        this.screenshots = apiTestCaseRunStep.screenshots;
        this.type = apiTestCaseRunStep.type;
    }

}
