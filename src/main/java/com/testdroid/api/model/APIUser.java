package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.dto.Context;
import com.testdroid.api.model.notification.APINotification;
import com.testdroid.api.util.TimeConverter;
import jakarta.xml.bind.annotation.XmlType;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.testdroid.api.dto.MappingKey.*;
import static java.util.Collections.emptyMap;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIUser extends APIEntity {

    @XmlType(namespace = "APIUser")
    public enum Status {
        INACTIVE("Inactive"),
        DISABLED("Disabled"),
        ENABLED("Enabled");

        private final String displayName;

        Status(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private Long accountId;

    private String accountName;

    private List<Long> accountServiceIds;

    private String address;

    private String apiKey;

    private String city;

    private String code;

    private String country;

    private Date createTime;

    private String createdByEmail;

    private Long createdById;

    private Date deleteTime;

    private String email;

    private Boolean enabled;

    private String firstName;

    private Boolean isAccountOwner;

    private Date lastLaunchedTestTime;

    private Date lastLoginTime;

    private String lastName;

    private String organization;

    private String phone;

    private String registrationIP;

    private List<APIRole> roles;

    private List<Long> serviceIds;

    private String state;

    private Status status;

    private String timeZone;

    private String userAccountName;

    public APIUser() {
    }

    public APIUser(Long id, String email) {
        super(id);
        this.email = email;
    }

    @SuppressWarnings("squid:S107")
    public APIUser(
            Long id, Long accountId, String accountName, String userAccountName, String email, String firstName,
            String lastName, String state, String country, String city, String code, String address, String phone,
            String organization, String timeZone, LocalDateTime createTime, LocalDateTime deleteTime,
            LocalDateTime lastLoginTime, LocalDateTime lastLaunchedTestTime, Boolean isAccountOwner, String apiKey,
            Status status, Long createdById, String createdByEmail, String registrationIP) {
        this(id, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.createTime = TimeConverter.toDate(createTime);
        this.deleteTime = TimeConverter.toDate(deleteTime);
        this.status = status;
        this.enabled = status == Status.ENABLED;
        this.state = state;
        this.country = country;
        this.city = city;
        this.code = code;
        this.address = address;
        this.phone = phone;
        this.organization = organization;
        this.timeZone = timeZone;
        this.lastLoginTime = TimeConverter.toDate(lastLoginTime);
        this.lastLaunchedTestTime = TimeConverter.toDate(lastLaunchedTestTime);
        this.accountId = accountId;
        this.accountName = accountName;
        this.userAccountName = userAccountName;
        this.isAccountOwner = isAccountOwner;
        this.apiKey = apiKey;
        this.createdById = createdById;
        this.createdByEmail = createdByEmail;
        this.registrationIP = registrationIP;
        this.selfURI = String.format("/users/%s", id);
    }

    public String getEmail() {
        return email;
    }

    public APIUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getIsAccountOwner() {
        return isAccountOwner;
    }

    public void setIsAccountOwner(Boolean accountOwner) {
        isAccountOwner = accountOwner;
    }

    public List<APIRole> getRoles() {
        return roles;
    }

    public void setRoles(List<APIRole> roles) {
        this.roles = roles;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.enabled = status == Status.ENABLED;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLaunchedTestTime() {
        return lastLaunchedTestTime;
    }

    public void setLastLaunchedTestTime(Date lastLaunchedTestTime) {
        this.lastLaunchedTestTime = lastLaunchedTestTime;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        this.createdByEmail = createdByEmail;
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

    private String getDeviceSessionURI(String id) {
        return createUri(selfURI, "/device-sessions/" + id);
    }

    private String getNotificationsURI() {
        return createUri(selfURI, "/notifications");
    }

    private String getNotificationURI(long id) {
        return createUri(selfURI, "/notifications/" + id);
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public List<Long> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<Long> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public List<Long> getAccountServiceIds() {
        return accountServiceIds;
    }

    public void setAccountServiceIds(List<Long> accountServiceIds) {
        this.accountServiceIds = accountServiceIds;
    }

    public String getRegistrationIP() {
        return registrationIP;
    }

    public void setRegistrationIP(String registrationIP) {
        this.registrationIP = registrationIP;
    }

    public String getUserAccountName() {
        return userAccountName;
    }

    public void setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
    }

    private Map<String, Object> getUpdateUserParams() {
        Map<String, Object> map = new HashMap<>();
        map.put(ADDRESS, address);
        map.put(CITY, city);
        map.put(CODE, code);
        map.put(COUNTRY, country);
        map.put(EMAIL, email);
        map.put(FIRST_NAME, firstName);
        map.put(LAST_NAME, lastName);
        map.put(ORGANIZATION, organization);
        map.put(PHONE, phone);
        map.put(STATE, state);
        map.put(TIME_ZONE, timeZone);
        return map;
    }

    private Map<String, Object> getCreateDeviceGroupParams(final String displayName, final APIDevice.OsType osType) {
        Map<String, Object> map = new HashMap<>();
        map.put(DISPLAY_NAME, displayName);
        map.put(OS_TYPE, osType);
        return map;
    }

    private Map<String, Object> getCreateProjectParams(String name) {
        Map<String, Object> result = new HashMap<>();
        result.put(NAME, name);
        return result;
    }

    private Map<String, Object> getUpdateDeviceSessionParams(
            final String name, final String clientSideId, final APIDeviceSession.State state) {
        Map<String, Object> map = new HashMap<>();
        map.put(NAME, name);
        map.put(CLIENT_SIDE_ID, clientSideId);
        map.put(STATE, state);
        return map;
    }

    public APIProject createProject(String name) throws APIException {
        return postResource(getProjectsURI(), getCreateProjectParams(name), APIProject.class);
    }

    public void update() throws APIException {
        APIUser user = postResource(selfURI, getUpdateUserParams(), APIUser.class);
        clone(user);
    }

    public APITestRun startTestRun(APITestRunConfig config) throws APIException {
        APITestRun apiTestRun = postResource(createUri(selfURI, "/runs"), config, APITestRun.class);
        apiTestRun.setSelfURI(createUri(selfURI, String.format("/projects/%s/runs/%s", apiTestRun.getProjectId(),
                apiTestRun.getId())));
        return apiTestRun;
    }

    public APITestRunConfig validateTestRunConfig(APITestRunConfig config) throws APIException {
        return postResource(createUri(selfURI, "/runs/config"), config, APITestRunConfig.class);
    }

    @JsonIgnore
    public APIUserFile uploadFile(File file) throws APIException {
        return postFile(createUri(selfURI, "/files"), file, emptyMap(), null, APIUserFile.class);
    }

    @JsonIgnore
    public APIUserFile getFile(Long id) throws APIException {
        return getResource(createUri(selfURI, String.format("/files/%s", id)), APIUserFile.class).getEntity();
    }

    @JsonIgnore
    public APIListResource<APINotification> getNotificationsResource() throws APIException {
        return getListResource(getNotificationsURI(), APINotification.class);
    }

    @JsonIgnore
    public APIListResource<APINotification> getNotificationsResource(Context<APINotification> context)
            throws APIException {
        return getListResource(getNotificationsURI(), context);
    }

    public APINotification getNotification(Long id) throws APIException {
        return getResource(getNotificationURI(id), APINotification.class).getEntity();
    }

    @JsonIgnore
    public APIListResource<APIProject> getProjectsResource() throws APIException {
        return getListResource(getProjectsURI(), APIProject.class);
    }

    @JsonIgnore
    public APIListResource<APIProject> getProjectsResource(Context<APIProject> context) throws APIException {
        return getListResource(getProjectsURI(), context);
    }

    public APIProject getProject(Long id) throws APIException {
        return getResource(getProjectURI(id), APIProject.class).getEntity();
    }

    @JsonIgnore
    public APIListResource<APIDeviceGroup> getDeviceGroupsResource() throws APIException {
        return getListResource(getDeviceGroupsURI(), APIDeviceGroup.class);
    }

    @JsonIgnore
    public APIListResource<APIDeviceGroup> getDeviceGroupsResource(Context<APIDeviceGroup> context)
            throws APIException {
        return getListResource(getDeviceGroupsURI(), context);
    }

    @JsonIgnore
    public APIListResource<APIFramework> getAvailableFrameworksResource(Context<APIFramework> context)
            throws APIException {
        return this.getListResource(createUri(selfURI, "/available-frameworks"), context);
    }

    @JsonIgnore
    public APIDeviceGroup createDeviceGroup(String displayName, APIDevice.OsType osType) throws APIException {
        return postResource(getDeviceGroupsURI(), getCreateDeviceGroupParams(displayName, osType),
                APIDeviceGroup.class);
    }

    @JsonIgnore
    public APIDeviceSession updateDeviceSession(
            String sessionId, String name, String clientSideId, APIDeviceSession.State state)
            throws APIException {
        return postResource(getDeviceSessionURI(sessionId), getUpdateDeviceSessionParams(name, clientSideId, state),
                APIDeviceSession.class);
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
        this.firstName = apiUser.firstName;
        this.lastName = apiUser.lastName;
        this.organization = apiUser.organization;
        this.phone = apiUser.phone;
        this.roles = apiUser.roles;
        this.state = apiUser.state;
        this.timeZone = apiUser.timeZone;
        this.createTime = apiUser.createTime;
        this.deleteTime = apiUser.deleteTime;
        this.isAccountOwner = apiUser.isAccountOwner;
        this.status = apiUser.status;
        this.lastLoginTime = apiUser.lastLoginTime;
        this.lastLaunchedTestTime = apiUser.lastLaunchedTestTime;
        this.enabled = apiUser.enabled;
        this.accountId = apiUser.accountId;
        this.accountName = apiUser.accountName;
        this.userAccountName = apiUser.userAccountName;
        this.apiKey = apiUser.apiKey;
        this.createdById = apiUser.createdById;
        this.createdByEmail = apiUser.createdByEmail;
        this.serviceIds = apiUser.serviceIds;
        this.accountServiceIds = apiUser.accountServiceIds;
        this.registrationIP = apiUser.registrationIP;
    }
}
