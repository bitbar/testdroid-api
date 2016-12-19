package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.*;
import com.testdroid.api.model.APIFiles.APIFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.testdroid.api.model.APIDevice.OsType;
import static com.testdroid.api.model.APITestRunConfig.Mode;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIProject extends APIEntity {

    @XmlType(namespace = "APIProject")
    public static enum APIArchivingStrategy {
        NEVER,
        DAYS,
        RUNS
    }

    @XmlType(namespace = "APIProject")
    public static enum Type {
        ANDROID(OsType.ANDROID, Mode.APP_CRAWLER, APIProjectJobConfig.Type.DEFAULT, AndroidFiles.class,
                "Android Instrumentation"),
        CTS(OsType.ANDROID, Mode.CTS, APIProjectJobConfig.Type.CTS, null, "Android CTS"),
        IOS(OsType.IOS, Mode.IOS, APIProjectJobConfig.Type.IOS, IOSFiles.class, "iOS UI Automation"),
        UIAUTOMATOR(OsType.ANDROID, Mode.UIAUTOMATOR, APIProjectJobConfig.Type.UIAUTOMATOR, UIAutomatorFiles.class,
                "Android UIAutomator"),
        REMOTECONTROL(OsType.ANDROID, Mode.REMOTECONTROL, APIProjectJobConfig.Type.REMOTECONTROL,
                RemoteControlFiles.class, "Android Remote Control"),
        CALABASH(OsType.ANDROID, Mode.CALABASH, APIProjectJobConfig.Type.CALABASH, CalabashFiles.class,
                "Calabash Android"),
        CALABASH_IOS(OsType.IOS, Mode.CALABASH_IOS, APIProjectJobConfig.Type.CALABASH_IOS, CalabashIOSFiles.class,
                "Calabash iOS"),
        XCTEST(OsType.IOS, Mode.CALABASH_IOS, APIProjectJobConfig.Type.XCTEST, CalabashIOSFiles.class,
                "XCTest"),
        XCUITEST(OsType.IOS, Mode.CALABASH_IOS, APIProjectJobConfig.Type.XCUITEST, CalabashIOSFiles.class,
                "XCUITest"),
        APPIUM_ANDROID(OsType.ANDROID, Mode.APPIUM_ANDROID, APIProjectJobConfig.Type.APPIUM_ANDROID,
                AppiumAndroidFiles.class, "Appium Android client side"),
        APPIUM_ANDROID_SERVER_SIDE(OsType.ANDROID, Mode.CALABASH,
                APIProjectJobConfig.Type.APPIUM_ANDROID_SERVER_SIDE, CalabashFiles.class, "Appium Android server side"),
        APPIUM_IOS(OsType.IOS, Mode.APPIUM_IOS, APIProjectJobConfig.Type.APPIUM_IOS, AppiumIOSFiles.class,
                "Appium iOS client side"),
        APPIUM_IOS_SERVER_SIDE(OsType.IOS, Mode.CALABASH_IOS, APIProjectJobConfig.Type.APPIUM_IOS_SERVER_SIDE,
                CalabashIOSFiles.class, "Appium iOS server side"),
        TELERIK_ANDROID(OsType.ANDROID, Mode.TELERIK_ANDROID, APIProjectJobConfig.Type.TELERIK_ANDROID,
                TelerikAndroidFiles.class, "Telerik Android"),
        TELERIK_IOS(OsType.IOS, Mode.TELERIK_IOS, APIProjectJobConfig.Type.TELERIK_IOS, TelerikIOSFiles.class,
                "Telerik iOS"),
        GENERIC(OsType.UNDEFINED, Mode.GENERIC, APIProjectJobConfig.Type.GENERIC, null, "Generic");

        private APITestRunConfig.Mode defaultMode;

        private Class<? extends APIFiles> filesClass;

        private APIProjectJobConfig.Type jobConfigType;

        private APIDevice.OsType osType;

        private String title;

        private Type(
                OsType osType, Mode defaultMode, APIProjectJobConfig.Type jobConfigType,
                Class<? extends APIFiles> filesClass, String title) {
            this.osType = osType;
            this.defaultMode = defaultMode;
            this.jobConfigType = jobConfigType;
            this.filesClass = filesClass;
            this.title = title;
        }

        public Class<? extends APIFiles> getFilesClass() {
            return filesClass;
        }

        public APIDevice.OsType getOsType() {
            return osType;
        }

        public String getTitle() {
            return title;
        }

        public APITestRunConfig.Mode getDefaultMode() {
            return defaultMode;
        }

        public APIProjectJobConfig.Type getJobConfigType() {
            return jobConfigType;
        }
    }

    private Integer archivingItemCount;

    private APIArchivingStrategy archivingStrategy;

    private boolean common;

    private String description;

    private APIFiles files;

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

    public APIProject() {
    }

    public APIProject(
            Long id, Date createTime, Date archiveTime, String name, String description, Type type, Long sharedById,
            String sharedByEmail, boolean common, APIArchivingStrategy archivingStrategy, Integer archivingItemCount,
            Long frameworkId) {
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
        this.jobConfig = new HashMap<APIProjectJobConfig.Type, APIProjectJobConfig>();
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
        Map<String, Object> result = new HashMap<String, Object>();
        result.putAll(getCreateRunParameters(testRunName));
        result.putAll(getCreateRunParameters(usedDeviceIds));
        return result;
    }

    private Map<String, Object> getCreateRunParameters(String testRunName, List<Long> usedDeviceIds, Long testRunId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.putAll(getCreateRunParameters(testRunName));
        result.putAll(getCreateRunParameters(usedDeviceIds));
        result.putAll(getCreateRunParameters(testRunId));
        return result;
    }

    private Map<String, Object> getCreateNotificationParameters(
            final String email,
            final APINotificationEmail.Type type) {
        return new HashMap<String, Object>() {{
            put("email", email);
            put("type", type);
        }};
    }

    private Map<String, Object> getCreateParameterParameters(final String key, final String value) {
        return new HashMap<String, Object>() {{
            put("key", key);
            put("value", value);
        }};
    }

    private Map<String, Object> getShareParameters(final String email) {
        return new HashMap<String, Object>() {{
            put("email", email);
        }};
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
            jobConfig = new HashMap<APIProjectJobConfig.Type, APIProjectJobConfig>();
        }
        if (jobConfig.get(type) == null) {
            jobConfig.put(type, getResource(getJobConfigURI(type), APIProjectJobConfig.class).getEntity());
        }
        return jobConfig.get(type);
    }

    /**
     * Returns APIFiles entity about files uploaded to this project.
     * Depending on <code>type</code> it may be any subclass of <code>APIFiles</code> returned.
     */
    @JsonIgnore
    public <T extends APIFiles> T getFiles(Class<T> clazz) throws APIException {
        if (clazz == null || !clazz.isAssignableFrom(type.getFilesClass())) {
            throw new APIException("This project type does not have requested file types");
        }
        if (files == null) {
            files = getResource(getFilesURI(), clazz).getEntity();
        }
        return (T) files;
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
    public APITestRun runWithConfig(
            String testRunName, List<Long> deviceIds, APITestRunConfig config, Long appFileId, Long testFileId,
            Long dataFileId) throws APIException {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("scheduler", config.getScheduler() != null ? config.getScheduler().name() : null);
        body.put("mode", config.getMode() != null ? config.getMode().name() : null);
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
        body.put("gamebenchEnabled", config.isGamebenchEnabled());
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
    public APINotificationEmail createNotificationEmail(String email, APINotificationEmail.Type type)
            throws APIException {
        return postResource(getNotificationEmailsURI(), getCreateNotificationParameters(email, type),
                APINotificationEmail.class);
    }

    @JsonIgnore
    public APIListResource<APINotificationEmail> getNotificationEmails() throws APIException {
        return getListResource(getNotificationEmailsURI());
    }

    /**
     * Returns list of notification emails for project.
     *
     * @param queryBuilder
     * @return
     * @throws APIException
     * @since 1.3.34
     */
    @JsonIgnore
    public APIListResource<APINotificationEmail> getNotificationEmails(APIQueryBuilder queryBuilder)
            throws APIException {
        return getListResource(getNotificationEmailsURI(), queryBuilder);
    }

    @JsonIgnore
    public APIFiles.APIFile uploadApplication(File file, String contentType) throws APIException {
        return postFile(getUploadApplicationURI(), file, contentType, APIFile.class);
    }

    @JsonIgnore
    public APIFiles.APIFile uploadTest(File file, String contentType) throws APIException {
        return postFile(getUploadTestURI(), file, contentType, APIFile.class);
    }

    @JsonIgnore
    public APIFiles.APIFile uploadData(File file, String contentType) throws APIException {
        return postFile(getUploadDataURI(), file, contentType, APIFile.class);
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
        Map<String, Object> body = new HashMap<String, Object>() {{
            put("name", name);
            put("description", description);
            put("common", common);
            put("archivingStrategy", archivingStrategy.name());
            put("archivingItemCount", archivingItemCount);
        }};
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
        this.files = apiProject.files;
        this.icon = apiProject.icon;
        this.jobConfig = apiProject.jobConfig;
        this.name = apiProject.name;
        this.sharedById = apiProject.sharedById;
        this.testRunConfig = apiProject.testRunConfig;
        this.type = apiProject.type;
        this.archivingStrategy = apiProject.archivingStrategy;
        this.archivingItemCount = apiProject.archivingItemCount;
        this.frameworkId = apiProject.frameworkId;
    }
}
