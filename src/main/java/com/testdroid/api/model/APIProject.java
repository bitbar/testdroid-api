package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

    @XmlType(namespace = "APIProject")
    public enum Type {
        ANDROID(OsType.ANDROID, APIProjectJobConfig.Type.DEFAULT, "Android Instrumentation"),
        IOS(OsType.IOS, APIProjectJobConfig.Type.APPCRAWLER_IOS, "AppCrawler iOS"),
        UIAUTOMATOR(OsType.ANDROID, APIProjectJobConfig.Type.UIAUTOMATOR, "Android UIAutomator"),
        CALABASH(OsType.ANDROID, APIProjectJobConfig.Type.CALABASH, "Calabash Android"),
        CALABASH_IOS(OsType.IOS, APIProjectJobConfig.Type.CALABASH_IOS, "Calabash iOS"),
        XCTEST(OsType.IOS, APIProjectJobConfig.Type.XCTEST, "XCTest"),
        XCUITEST(OsType.IOS, APIProjectJobConfig.Type.XCUITEST, "XCUITest"),
        APPIUM_ANDROID(OsType.ANDROID, APIProjectJobConfig.Type.APPIUM_ANDROID, "Appium Android client side"),
        APPIUM_ANDROID_SERVER_SIDE(OsType.ANDROID, APIProjectJobConfig.Type.APPIUM_ANDROID_SERVER_SIDE,
                "Appium Android server side"),
        APPIUM_IOS(OsType.IOS, APIProjectJobConfig.Type.APPIUM_IOS, "Appium iOS client side"),
        APPIUM_IOS_SERVER_SIDE(OsType.IOS, APIProjectJobConfig.Type.APPIUM_IOS_SERVER_SIDE,
                "Appium iOS server side"),
        GENERIC(OsType.UNDEFINED, APIProjectJobConfig.Type.GENERIC, "Generic");

        private APIProjectJobConfig.Type jobConfigType;

        private APIDevice.OsType osType;

        private String title;

        Type(OsType osType, APIProjectJobConfig.Type jobConfigType, String title) {
            this.osType = osType;
            this.jobConfigType = jobConfigType;
            this.title = title;
        }

        public APIDevice.OsType getOsType() {
            return osType;
        }

        public String getTitle() {
            return title;
        }

        public APIProjectJobConfig.Type getJobConfigType() {
            return jobConfigType;
        }
    }

    private Integer archivingItemCount;

    private APIArchivingStrategy archivingStrategy;

    private boolean common;

    private String description;

    private byte[] icon;

    private Map<APIProjectJobConfig.Type, APIProjectJobConfig> jobConfig;

    private String name;

    private String sharedByEmail;

    private Long sharedById;

    private APITestRunConfig testRunConfig;

    private Type type;

    private Date createTime;

    private Date archiveTime;

    private Long frameworkId;

    private boolean isShared;

    private Double successRatio;

    public APIProject() {
    }

    public APIProject(
            Long id, Date createTime, Date archiveTime, String name, String description, Type type, Long sharedById,
            String sharedByEmail, boolean common, APIArchivingStrategy archivingStrategy, Integer archivingItemCount,
            Long frameworkId, Boolean isShared) {
        super(id);
        this.createTime = createTime;
        this.archiveTime = archiveTime;
        this.name = name;
        this.description = description;
        this.type = type;
        this.sharedById = sharedById;
        this.sharedByEmail = sharedByEmail;
        this.common = common;
        this.archivingStrategy = archivingStrategy;
        this.archivingItemCount = archivingItemCount;
        this.frameworkId = frameworkId;
        this.isShared = isShared;
        this.jobConfig = new HashMap<>();
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    /**
     * Returns user ID sharing this project or null if project is owned or common.
     *
     * @return user ID sharing this project
     */
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

    public String getArchivingStrategyDisplayValue() {
        switch (archivingStrategy) {
            case NEVER:
                return "never";
            case RUNS:
                return String.format("%s run%s", archivingItemCount, archivingItemCount != 1 ? "s" : "");
            case DAYS:
                return String.format("%s day%s", archivingItemCount, archivingItemCount != 1 ? "s" : "");
            default:
                return "";
        }
    }

    public Long getFrameworkId() {
        return frameworkId;
    }

    public void setFrameworkId(Long frameworkId) {
        this.frameworkId = frameworkId;
    }

    public Double getSuccessRatio() {
        return successRatio;
    }

    public void setSuccessRatio(Double successRatio) {
        this.successRatio = successRatio;
    }

    private String getConfigURI() {
        return createUri(selfURI, "/config");
    }

    private String getJobConfigURI(APIProjectJobConfig.Type type) {
        return createUri(selfURI, "/job-configs/" + type.toString());
    }

    private String getFilesURI() {
        return createUri(selfURI, "/files");
    }

    private String getIconURI() {
        return createUri(selfURI, "/icon");
    }

    private String getSharingsURI() {
        return createUri(selfURI, "/sharings");
    }

    private String getRunsURI() {
        return createUri(selfURI, "/runs");
    }

    private String getRunURI(Long id) {
        return createUri(selfURI, "/runs/" + id);
    }

    private String getPublicDeviceGroupsURI() {
        return createUri(selfURI, "/public-device-groups");
    }

    private String getDeviceGroupsURI() {
        return createUri(selfURI, "/device-groups");
    }

    private String getUploadApplicationURI() {
        return createUri(selfURI, "/files/application");
    }

    private String getUploadTestURI() {
        return createUri(selfURI, "/files/test");
    }

    private String getUploadDataURI() {
        return createUri(selfURI, "/files/data");
    }

    private String getNotificationEmailsURI() {
        return createUri(selfURI, "/notifications");
    }

    private String getParametersURI() {
        return createUri(selfURI, "/config/parameters");
    }

    private Map<String, Object> getCreateRunParameters(String testRunName) {
        return Collections.singletonMap("name", (Object) testRunName);
    }

    private Map<String, Object> getCreateRunParameters(Long testRunId) {
        return Collections.singletonMap("testRunId", (Object) testRunId);
    }

    private Map<String, Object> getCreateRunParameters(List<Long> usedDeviceIds) {
        return Collections.singletonMap("usedDeviceIds[]", (Object) StringUtils.join(usedDeviceIds, ","));
    }

    private Map<String, Object> getCreateRunParameters(String testRunName, List<Long> usedDeviceIds) {
        Map<String, Object> result = new HashMap<>();
        result.putAll(getCreateRunParameters(testRunName));
        result.putAll(getCreateRunParameters(usedDeviceIds));
        return result;
    }

    private Map<String, Object> getCreateRunParameters(String testRunName, List<Long> usedDeviceIds, Long testRunId) {
        Map<String, Object> result = new HashMap<>();
        result.putAll(getCreateRunParameters(testRunName));
        result.putAll(getCreateRunParameters(usedDeviceIds));
        result.putAll(getCreateRunParameters(testRunId));
        return result;
    }

    private Map<String, Object> getCreateRunParameters(String testRunName, String deviceNamePattern,
            Long testRunId) {
        Map<String, Object> result = new HashMap<>();
        result.putAll(getCreateRunParameters(testRunName));
        result.put("deviceNamePattern", deviceNamePattern);
        result.putAll(getCreateRunParameters(testRunId));
        return result;
    }

    private Map<String, Object> getCreateParameterParameters(final String key, final String value) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("value", value);
        return map;
    }

    private Map<String, Object> getShareParameters(final String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        return map;
    }

    @JsonIgnore
    public APITestRunConfig getTestRunConfig() throws APIException {
        if (testRunConfig == null) {
            testRunConfig = getResource(getConfigURI(), APITestRunConfig.class).getEntity();
        }
        return testRunConfig;
    }

    @JsonIgnore
    public APIProjectJobConfig getJobConfig(APIProjectJobConfig.Type type) throws APIException {
        if (jobConfig == null) {
            jobConfig = new HashMap<>();
        }
        if (jobConfig.get(type) == null) {
            jobConfig.put(type, getResource(getJobConfigURI(type), APIProjectJobConfig.class).getEntity());
        }
        return jobConfig.get(type);
    }

    @JsonIgnore
    public APIListResource<APIUserFile> getFiles() throws APIException {
        return getListResource(getFilesURI());
    }

    @JsonIgnore
    public byte[] getIcon() throws APIException, IOException {
        if (icon == null) {
            icon = IOUtils.toByteArray(getResource(getIconURI(), null).getStream());
        }
        return icon;
    }

    @JsonIgnore
    public APITestRun run() throws APIException {
        return postResource(getRunsURI(), null, APITestRun.class);
    }

    @JsonIgnore
    public APITestRun run(String testRunName) throws APIException {
        return postResource(getRunsURI(), getCreateRunParameters(testRunName), APITestRun.class);
    }

    @JsonIgnore
    public APITestRun run(List<Long> usedDevicesId) throws APIException {
        return postResource(getRunsURI(), getCreateRunParameters(usedDevicesId), APITestRun.class);
    }

    @JsonIgnore
    public APITestRun run(String testRunName, List<Long> usedDevicesId) throws APIException {
        return postResource(getRunsURI(), getCreateRunParameters(testRunName, usedDevicesId), APITestRun.class);
    }

    @JsonIgnore
    public APITestRun run(String testRunName, List<Long> usedDevicesId, Long testRunId) throws APIException {
        return postResource(getRunsURI(), getCreateRunParameters(testRunName, usedDevicesId, testRunId), APITestRun.class);
    }

    @JsonIgnore
    public APITestRun run(String testRunName, String deviceNamePattern, Long testRunId) throws APIException {
        return postResource(getRunsURI(), getCreateRunParameters(testRunName, deviceNamePattern, testRunId),
                APITestRun.class);
    }

    @JsonIgnore
    public APITestRun runWithConfig(
            String testRunName, List<Long> deviceIds, APITestRunConfig config, Long appFileId, Long testFileId,
            Long dataFileId) throws APIException {
        Map<String, Object> body = new HashMap<>();
        body.put("scheduler", config.getScheduler() != null ? config.getScheduler().name() : null);
        body.put("appCrawlerRun", config.isAppCrawlerRun());
        body.put("autoScreenshots", config.isAutoScreenshots());
        body.put("screenshotDir", config.getScreenshotDir());
        body.put("limitationType", config.getLimitationType() != null ? config.getLimitationType().name() : null);
        body.put("limitationValue", config.getLimitationValue());
        body.put("withAnnotation", config.getWithAnnotation());
        body.put("withoutAnnotation", config.getWithoutAnnotation());
        body.put("applicationUsername", config.getApplicationUsername());
        body.put("applicationPassword", config.getApplicationPassword());
        body.put("usedDeviceGroupId", config.getUsedDeviceGroupId());
        body.put("deviceLanguageCode", config.getDeviceLanguageCode());
        body.put("hookURL", config.getHookURL());
        body.put("uiAutomatorTestClasses", config.getUiAutomatorTestClasses());
        body.put("launchApp", config.isLaunchApp());
        body.put("instrumentationRunner", config.getInstrumentationRunner());
        body.put("appRequired", config.isAppRequired());
        body.put("timeout", config.getTimeout());
        body.put("name", testRunName);
        body.put("usedDeviceIds[]", StringUtils.join(deviceIds, ","));
        body.put("appFileId", appFileId);
        body.put("testFileId", testFileId);
        body.put("dataFileId", dataFileId);
        return postResource(getRunsURI(), body, APITestRun.class);
    }

    public void delete() throws APIException {
        deleteResource(selfURI);
    }

    @JsonIgnore
    public APIListResource<APITestRun> getTestRunsResource() throws APIException {
        return getListResource(getRunsURI());
    }

    /**
     * @param queryBuilder
     * @return
     * @throws APIException
     * @since 1.3.34
     */
    @JsonIgnore
    public APIListResource<APITestRun> getTestRunsResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getRunsURI(), queryBuilder);
    }

    public APITestRun getTestRun(Long id) throws APIException {
        return getResource(getRunURI(id), APITestRun.class).getEntity();
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
    public APIListResource<APITestRun> getTestRunsResource(long offset, long limit, String search, APISort sort)
            throws APIException {
        return getListResource(getRunsURI(), offset, limit, search, sort, APITestRun.class);
    }

    @JsonIgnore
    public APIProjectSharing share(String email) throws APIException {
        return postResource(getSharingsURI(), getShareParameters(email), APIProjectSharing.class);
    }

    @JsonIgnore
    public APIListResource<APIProjectSharing> getProjectSharings() throws APIException {
        return getListResource(getSharingsURI());
    }

    /**
     * @param queryBuilder
     * @return
     * @throws APIException
     * @since 1.3.34
     */
    @JsonIgnore
    public APIListResource<APIProjectSharing> getProjectSharings(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getSharingsURI(), queryBuilder);
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
    public APIListResource<APIProjectSharing> getProjectSharings(long offset, long limit, String search, APISort sort)
            throws APIException {
        return getListResource(getSharingsURI(), offset, limit, search, sort, APIProjectSharing.class);
    }

    @JsonIgnore
    public APIListResource<APIDeviceGroup> getPublicDeviceGroups() throws APIException {
        return getListResource(getPublicDeviceGroupsURI());
    }

    @JsonIgnore
    public APIListResource<APIDeviceGroup> getDeviceGroups() throws APIException {
        return getListResource(getDeviceGroupsURI());
    }

    @JsonIgnore
    public APIUserFile uploadApplication(File file, String contentType) throws APIException {
        return postFile(getUploadApplicationURI(), file, contentType, APIUserFile.class);
    }

    @JsonIgnore
    public APIUserFile uploadTest(File file, String contentType) throws APIException {
        return postFile(getUploadTestURI(), file, contentType, APIUserFile.class);
    }

    @JsonIgnore
    public APIUserFile uploadData(File file, String contentType) throws APIException {
        return postFile(getUploadDataURI(), file, contentType, APIUserFile.class);
    }

    @JsonIgnore
    public APITestRunParameter createParameter(String key, String value) throws APIException {
        return postResource(getParametersURI(), getCreateParameterParameters(key, value), APITestRunParameter.class);
    }

    @JsonIgnore
    public APIListResource<APITestRunParameter> getParameters() throws APIException {
        return getListResource(getParametersURI());
    }

    @JsonIgnore
    public APIListResource<APITestRunParameter> getParameters(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getParametersURI(), queryBuilder);
    }

    @JsonIgnore
    public void deleteParameter(long parameterId) throws APIException {
        deleteResource(String.format("%s/%s", getParametersURI(), parameterId));
    }

    public void update() throws APIException {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("description", description);
        body.put("common", common);
        body.put("archivingStrategy", archivingStrategy.name());
        body.put("archivingItemCount", archivingItemCount);
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
        this.jobConfig = apiProject.jobConfig;
        this.name = apiProject.name;
        this.sharedById = apiProject.sharedById;
        this.testRunConfig = apiProject.testRunConfig;
        this.type = apiProject.type;
        this.archivingStrategy = apiProject.archivingStrategy;
        this.archivingItemCount = apiProject.archivingItemCount;
        this.frameworkId = apiProject.frameworkId;
        this.successRatio = apiProject.successRatio;
    }
}
