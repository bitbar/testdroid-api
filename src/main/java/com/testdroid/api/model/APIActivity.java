package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIActivity extends APIEntity {

    @XmlType(namespace = "APIActivity")
    public enum HttpMethod {
        GET,
        HEAD,
        POST,
        PUT,
        PATCH,
        DELETE,
        OPTIONS,
        TRACE
    }

    private Date createTime;

    private HttpMethod httpMethod;

    private String userAgent;

    private String parameters;

    private String uri;

    private String userEmail;

    private Long userId;

    private String body;

    private boolean deprecatedResource;

    public APIActivity() {
    }

    public APIActivity(
            Long id, LocalDateTime createTime, HttpMethod httpMethod, String userAgent, String parameters, String uri,
            String body, Long userId, String userEmail, boolean deprecatedResource) {
        super(id);
        this.createTime = TimeConverter.toDate(createTime);
        this.httpMethod = httpMethod;
        this.userAgent = userAgent;
        this.parameters = parameters;
        this.uri = uri;
        this.body = body;
        this.userId = userId;
        this.userEmail = userEmail;
        this.deprecatedResource = deprecatedResource;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isDeprecatedResource() {
        return deprecatedResource;
    }

    public void setDeprecatedResource(boolean deprecatedResource) {
        this.deprecatedResource = deprecatedResource;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIActivity apiActivity = (APIActivity) from;
        cloneBase(from);
        this.createTime = apiActivity.createTime;
        this.httpMethod = apiActivity.httpMethod;
        this.userAgent = apiActivity.userAgent;
        this.parameters = apiActivity.parameters;
        this.uri = apiActivity.uri;
        this.userId = apiActivity.userId;
        this.userEmail = apiActivity.userEmail;
        this.body = apiActivity.body;
        this.deprecatedResource = apiActivity.deprecatedResource;
    }
}
