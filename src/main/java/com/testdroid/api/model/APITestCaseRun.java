package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APITestCaseRun extends APIEntity {

    @XmlType(namespace = "APITestCaseRun")
    public enum Result {
        PASSED,
        FAILED,
        SKIPPED,
        NOT_AVAILABLE
    }

    private String className;

    private Date createTime;

    private double duration;

    private String errorMessage;

    private String methodName;

    private Result result;

    private String stacktrace;

    private APIList<APITestCaseRunStep> steps;

    private String suiteName;

    public APITestCaseRun() {
    }

    public APITestCaseRun(
            Long id, double duration, Result result, String errorMessage, String stacktrace,
            Date createTime, APIList<APITestCaseRunStep> steps, String className, String methodName, String suiteName) {
        super(id);
        this.duration = duration;
        this.result = result;
        this.errorMessage = errorMessage;
        this.stacktrace = stacktrace;
        this.createTime = createTime;
        this.steps = steps;
        this.className = className;
        this.methodName = methodName;
        this.suiteName = suiteName;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public APIList<APITestCaseRunStep> getSteps() {
        return steps;
    }

    public void setSteps(APIList<APITestCaseRunStep> steps) {
        this.steps = steps;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APITestCaseRun apiTestCaseRun = (APITestCaseRun) from;
        cloneBase(from);
        this.className = apiTestCaseRun.className;
        this.createTime = apiTestCaseRun.createTime;
        this.duration = apiTestCaseRun.duration;
        this.errorMessage = apiTestCaseRun.errorMessage;
        this.methodName = apiTestCaseRun.methodName;
        this.result = apiTestCaseRun.result;
        this.stacktrace = apiTestCaseRun.stacktrace;
        this.steps = apiTestCaseRun.steps;
        this.suiteName = apiTestCaseRun.suiteName;
    }

}
