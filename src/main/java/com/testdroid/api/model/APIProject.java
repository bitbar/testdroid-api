package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.dto.Context;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.testdroid.api.dto.MappingKey.*;
import static com.testdroid.api.model.APIDevice.OsType;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIProject extends APIEntity {

    @XmlType(namespace = "APIProject")
    public enum APIArchivingStrategy {
        NEVER,
        DAYS,
        RUNS
    }

    private Integer archivingItemCount;

    private APIArchivingStrategy archivingStrategy;

    private boolean common;

    private String description;

    private byte[] icon;

    private String name;

    @Deprecated // use userEmail
    private String sharedByEmail;

    @Deprecated // use userId
    private Long sharedById;

    private APITestRunConfig testRunConfig;

    private Date createTime;

    private Date archiveTime;

    private boolean isShared;

    private boolean isSharedWithCaller;

    private Double successRatio;

    private APIDevice.OsType osType;

    private boolean readOnly;

    private Long userId;

    private String userEmail;

    public APIProject() {
    }

    public APIProject(
            Long id, LocalDateTime createTime, LocalDateTime archiveTime, String name, String description,
            Long sharedById, String sharedByEmail, boolean common, APIArchivingStrategy archivingStrategy,
            Integer archivingItemCount, Boolean isShared, APIDevice.OsType osType, boolean readOnly) {
        super(id);
        this.createTime = TimeConverter.toDate(createTime);
        this.archiveTime = TimeConverter.toDate(archiveTime);
        this.name = name;
        this.description = description;
        this.sharedById = sharedById;
        this.sharedByEmail = sharedByEmail;
        this.common = common;
        this.archivingStrategy = archivingStrategy;
        this.archivingItemCount = archivingItemCount;
        this.isShared = isShared;
        this.osType = osType;
        this.readOnly = readOnly;
        this.userEmail = sharedByEmail;
        this.userId = sharedById;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCommon() {
        return common;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(Date archiveTime) {
        this.archiveTime = archiveTime;
    }

    public Long getSharedById() {
        return sharedById;
    }

    public void setSharedById(Long sharedById) {
        this.sharedById = sharedById;
    }

    public String getSharedByEmail() {
        return sharedByEmail;
    }

    public void setSharedByEmail(String sharedByEmail) {
        this.sharedByEmail = sharedByEmail;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean isShared) {
        this.isShared = isShared;
    }

    public boolean isSharedWithCaller() {
        return isSharedWithCaller;
    }

    public void setSharedWithCaller(boolean sharedWithCaller) {
        isSharedWithCaller = sharedWithCaller;
    }

    public APIArchivingStrategy getArchivingStrategy() {
        return archivingStrategy;
    }

    public void setArchivingStrategy(APIArchivingStrategy archivingStrategy) {
        this.archivingStrategy = archivingStrategy;
    }

    public Integer getArchivingItemCount() {
        return archivingItemCount;
    }

    public void setArchivingItemCount(Integer archivingItemCount) {
        this.archivingItemCount = archivingItemCount;
    }

    public Double getSuccessRatio() {
        return successRatio;
    }

    public void setSuccessRatio(Double successRatio) {
        this.successRatio = successRatio;
    }

    public OsType getOsType() {
        return osType;
    }

    public void setOsType(OsType osType) {
        this.osType = osType;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
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

    private String getRunsURI() {
        return createUri(selfURI, "/runs");
    }

    private String getRunURI(Long id) {
        return createUri(selfURI, "/runs/" + id);
    }

    public void delete() throws APIException {
        deleteResource(selfURI);
    }

    @JsonIgnore
    public APIListResource<APITestRun> getTestRunsResource() throws APIException {
        return getListResource(getRunsURI(), APITestRun.class);
    }

    @JsonIgnore
    public APIListResource<APITestRun> getTestRunsResource(Context<APITestRun> context) throws APIException {
        return getListResource(getRunsURI(), context);
    }

    public APITestRun getTestRun(Long id) throws APIException {
        return getResource(getRunURI(id), APITestRun.class).getEntity();
    }

    public void update() throws APIException {
        Map<String, Object> body = new HashMap<>();
        body.put(NAME, name);
        body.put(DESCRIPTION, description);
        body.put(COMMON, common);
        body.put(ARCHIVING_STRATEGY, archivingStrategy.name());
        body.put(ARCHIVING_ITEM_COUNT, archivingItemCount);
        APIProject project = postResource(selfURI, body, APIProject.class);
        clone(project);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIProject apiProject = (APIProject) from;
        cloneBase(from);
        this.common = apiProject.common;
        this.createTime = apiProject.createTime;
        this.archiveTime = apiProject.archiveTime;
        this.description = apiProject.description;
        this.icon = apiProject.icon;
        this.name = apiProject.name;
        this.sharedById = apiProject.sharedById;
        this.isShared = apiProject.isShared;
        this.isSharedWithCaller = apiProject.isSharedWithCaller;
        this.testRunConfig = apiProject.testRunConfig;
        this.archivingStrategy = apiProject.archivingStrategy;
        this.archivingItemCount = apiProject.archivingItemCount;
        this.successRatio = apiProject.successRatio;
        this.osType = apiProject.osType;
        this.readOnly = apiProject.readOnly;
        this.userId = apiProject.userId;
        this.userEmail = apiProject.userEmail;
    }
}
