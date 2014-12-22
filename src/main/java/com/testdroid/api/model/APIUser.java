package com.testdroid.api.model;

import com.testdroid.api.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIUser extends APIEntity {

    private String address;

    private String city;

    private String code;

    private String country;

    private String email;

    private boolean enabled;

    private String name;

    private String organization;

    private String phone;

    private APIRole[] roles;

    private String state;

    private String timeZone;

    private String vatID;

    private Date createTime;

    private EmailNotification emailNotification;

    private Boolean isMainUser;

    @XmlType(namespace = "APIUser", name = "APIUserEmailNotification")
    public static enum EmailNotification {
        ALWAYS,
        NEVER,
        ON_FAILURE
    }

    public APIUser() {
    }

    public APIUser(
            Long id, String email, String name, String state, String country, String city, String code,
            String address, String phone, String organization, String vatID, String timeZone, boolean enabled,
            EmailNotification emailNotification, Date createTime, APIRole... roles) {
        super(id);
        this.email = email;
        this.name = name;
        this.state = state;
        this.country = country;
        this.city = city;
        this.code = code;
        this.address = address;
        this.phone = phone;
        this.organization = organization;
        this.vatID = vatID;
        this.timeZone = timeZone;
        this.enabled = enabled;
        this.emailNotification = emailNotification;
        this.roles = roles;
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getVatID() {
        return vatID;
    }

    public void setVatID(String vatID) {
        this.vatID = vatID;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getIsMainUser() {
        return isMainUser;
    }

    public void setIsMainUser(Boolean isMainUser) {
        this.isMainUser = isMainUser;
    }

    public APIRole[] getRoles() {
        return roles;
    }

    public void setRoles(APIRole[] roles) {
        this.roles = roles;
    }

    public EmailNotification getEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(EmailNotification emailNotification) {
        this.emailNotification = emailNotification;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private String getProjectsURI() {
        return createUri(selfURI, "/projects");
    }

    private String getProjectURI(Long id) {
        return createUri(selfURI, "/projects/" + id);
    }

    private String getDeviceGroupsURI() {
        return createUri(selfURI, "/device-groups");
    }

    private String getNotificationsURI() {
        return createUri(selfURI, "/notifications");
    }

    private String getNotificationURI(long id) {
        return createUri(selfURI, "/notifications/" + id);
    }

    private String getAvailbleProjectTypesURI() {
        return createUri(selfURI, "/available-project-types");
    }

    private Map<String, Object> getNotificationEmailParams(final APINotificationEmail.Type type) {
        return new HashMap<String, Object>() {{
            put("type", type);
        }};
    }

    private Map<String, Object> getNotificationEmailParams(String email, APINotificationEmail.Type type) {
        Map<String, Object> result = getNotificationEmailParams(type);
        result.put("email", email);
        return result;
    }

    private Map<String, Object> getUpdateUserParams(
            final String address, final String city, final String code,
            final String country, final String email,
            final String name, final String organization, final String phone, final String state, final String timeZone,
            final String vatId) {
        return new HashMap<String, Object>() {{
            put("address", address);
            put("city", city);
            put("code", code);
            put("country", country);
            put("email", email);
            put("name", name);
            put("organization", organization);
            put("phone", phone);
            put("state", state);
            put("timeZone", timeZone);
            put("vatId", vatId);
        }};
    }

    private Map<String, Object> getCreateDeviceGroupParams(final String name, final APIDevice.OsType osType) {
        return new HashMap<String, Object>() {{
            put("name", name);
            put("osType", osType);
        }};
    }

    private Map<String, Object> getCreateProjectParams(final APIProject.Type type) {
        return new HashMap<String, Object>() {{
            put("type", type);
        }};
    }

    private Map<String, Object> getCreateProjectParams(APIProject.Type type, String name) {
        Map<String, Object> result = getCreateProjectParams(type);
        result.put("name", name);
        return result;
    }

    public APIProject createProject(APIProject.Type projectType) throws APIException {
        return postResource(getProjectsURI(), getCreateProjectParams(projectType), APIProject.class);
    }

    public APIProject createProject(APIProject.Type type, String name) throws APIException {
        return postResource(getProjectsURI(), getCreateProjectParams(type, name), APIProject.class);
    }

    public void update() throws APIException {
        APIUser user = postResource(selfURI,
                getUpdateUserParams(address, city, code, country, email, name, organization, phone, state, timeZone,
                        vatID), APIUser.class
        );
        clone(user);
    }

    @JsonIgnore
    public APIListResource<APIProject> getProjectsResource() throws APIException {
        return getListResource(getProjectsURI());
    }

    /**
     * @param queryBuilder
     * @return
     * @throws APIException
     * @since 1.3.34
     */
    @JsonIgnore
    public APIListResource<APIProject> getProjectsResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getProjectsURI(), queryBuilder);
    }

    /**
     * @param offset
     * @param limit
     * @param search
     * @param sort
     * @return
     * @throws APIException
     * @deprecated
     */
    @JsonIgnore
    public APIListResource<APIProject> getProjectsResource(long offset, long limit, String search, APISort sort)
            throws APIException {
        return getListResource(getProjectsURI(), offset, limit, search, sort, APIProject.class);
    }

    public APIProject getProject(Long id) throws APIException {
        return getResource(getProjectURI(id), APIProject.class).getEntity();
    }

    @JsonIgnore
    public APIListResource<APIDeviceGroup> getDeviceGroupsResource() throws APIException {
        return getListResource(getDeviceGroupsURI());
    }

    /**
     * @param queryBuilder
     * @return
     * @throws APIException
     * @since 1.3.34
     */
    @JsonIgnore
    public APIListResource<APIDeviceGroup> getDeviceGroupsResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getDeviceGroupsURI(), queryBuilder);
    }

    /**
     * @param offset
     * @param limit
     * @param search
     * @param sort
     * @return
     * @throws APIException
     * @deprecated
     */
    @JsonIgnore
    public APIListResource<APIDeviceGroup> getDeviceGroupsResource(long offset, long limit, String search, APISort sort)
            throws APIException {
        return getListResource(getDeviceGroupsURI(), offset, limit, search, sort, APIDeviceGroup.class);
    }

    @JsonIgnore
    public APIDeviceGroup createDeviceGroup(String name, APIDevice.OsType osType) throws APIException {
        return postResource(getDeviceGroupsURI(), getCreateDeviceGroupParams(name, osType), APIDeviceGroup.class);
    }

    @JsonIgnore
    public APINotificationEmail createNotificationEmail(String email, APINotificationEmail.Type type)
            throws APIException {
        return postResource(getNotificationsURI(), getNotificationEmailParams(email, type), APINotificationEmail.class);
    }

    @JsonIgnore
    public APIListResource<APINotificationEmail> getNotificationEmails() throws APIException {
        return getListResource(getNotificationsURI());
    }

    /**
     * @param queryBuilder
     * @return
     * @throws APIException
     * @since 1.3.34
     */
    @JsonIgnore
    public APIListResource<APINotificationEmail> getNotificationEmails(APIQueryBuilder queryBuilder)
            throws APIException {
        return getListResource(getNotificationsURI(), queryBuilder);
    }

    /**
     * @param offset
     * @param limit
     * @param search
     * @param sort
     * @return
     * @throws APIException
     * @deprecated
     */
    @JsonIgnore
    public APIListResource<APINotificationEmail> getNotificationEmails(
            long offset, long limit, String search,
            APISort sort) throws APIException {
        return getListResource(getNotificationsURI(), offset, limit, search, sort, APINotificationEmail.class);
    }

    @JsonIgnore
    public APINotificationEmail updateNotificationEmail(long id, APINotificationEmail.Type emailType)
            throws APIException {
        return postResource(getNotificationURI(id), getNotificationEmailParams(emailType),
                APINotificationEmail.class);
    }

    @JsonIgnore
    public void deleteNotificationEmail(long id) throws APIException {
        deleteResource(getNotificationURI(id));
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIUser apiUser = (APIUser) from;
        cloneBase(from);
        this.address = apiUser.address;
        this.city = apiUser.city;
        this.code = apiUser.code;
        this.country = apiUser.country;
        this.email = apiUser.email;
        this.enabled = apiUser.enabled;
        this.name = apiUser.name;
        this.organization = apiUser.organization;
        this.phone = apiUser.phone;
        this.roles = apiUser.roles;
        this.state = apiUser.state;
        this.timeZone = apiUser.timeZone;
        this.vatID = apiUser.vatID;
        this.emailNotification = apiUser.emailNotification;
        this.createTime = apiUser.createTime;
        this.isMainUser = apiUser.isMainUser;
    }
}
