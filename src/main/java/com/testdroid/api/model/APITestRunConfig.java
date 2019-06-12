package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.*;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APITestRunConfig extends APIEntity implements Serializable {

    public static final String DEFAULT_RUNNER = "android.test.InstrumentationTestRunner";

    public static final String SCREENSHOT_DIR = "/sdcard/test-screenshots";

    private static final long serialVersionUID = 1L;

    @XmlType(namespace = "APITestRunConfig")
    public enum LimitationType {
        PACKAGE,
        CLASS
    }

    @XmlType(namespace = "APITestRunConfig")
    public enum Scheduler {
        PARALLEL,
        SERIAL,
        SINGLE,
        ALL_INSTANCES
    }

    private String appiumBrokerAddress;

    private String applicationPassword;

    private String applicationUsername;

    private Long creditsPrice;

    private String deviceLanguageCode = Locale.US.toString();

    private boolean videoRecordingEnabled;

    private String hookURL;

    private String instrumentationRunner;

    private LimitationType limitationType;

    private String limitationValue;

    private boolean appCrawlerRun;

    private Long projectId;

    private boolean runAvailable;

    private Scheduler scheduler = Scheduler.PARALLEL;

    private String screenshotDir;

    private Long deviceGroupId;

    @Deprecated
    private Long usedDeviceGroupId;

    private String usedDeviceGroupName;

    private String withAnnotation;

    private String withoutAnnotation;

    private Long timeout;

    private Integer maxAutoRetriesCount;

    private Long frameworkId;

    private List<APIDeviceGroup> availableDeviceGroups;

    private List<APIDevice> availableDevices;

    private List<APIFramework> availableFrameworks;

    private List<APIFileConfig> files = new ArrayList<>();

    private APIDevice.OsType osType = APIDevice.OsType.UNDEFINED;

    private List<APITestRunParameter> testRunParameters = new ArrayList<>();

    private List<Long> deviceIds;

    private String status;

    private int statusCode;

    private String testRunName;

    private Long testRunId;

    private String deviceNamePattern;

    private String projectName;

    private boolean resignFiles;

    private boolean loadedPrevious;

    private APIClientSideTestConfig clientSideTestConfig;

    @JsonIgnore
    private List<Long> computedDevices;

    public APITestRunConfig() {
    }

    public APITestRunConfig(
            Long id, Scheduler scheduler, Boolean appCrawlerRun, String screenshotDir, LimitationType limitationType,
            String limitationValue, String withAnnotation, String withoutAnnotation, String applicationUsername,
            String applicationPassword, Long deviceGroupId, String usedDeviceGroupName, Long creditsPrice,
            String deviceLanguageCode, String hookURL, String instrumentationRunner, Boolean videoRecordingEnabled,
            Long timeout, String appiumBrokerAddress, Integer maxAutoRetriesCount) {
        super(id);
        this.scheduler = scheduler;
        this.appCrawlerRun = appCrawlerRun;
        this.screenshotDir = screenshotDir;
        this.limitationType = limitationType;
        this.limitationValue = limitationValue;
        this.withAnnotation = withAnnotation;
        this.withoutAnnotation = withoutAnnotation;
        this.applicationUsername = applicationUsername;
        this.applicationPassword = applicationPassword;
        this.deviceGroupId = deviceGroupId;
        this.usedDeviceGroupId = deviceGroupId;
        this.usedDeviceGroupName = usedDeviceGroupName;
        this.creditsPrice = creditsPrice;
        this.deviceLanguageCode = deviceLanguageCode;
        this.hookURL = hookURL;
        this.instrumentationRunner = instrumentationRunner;
        this.videoRecordingEnabled = videoRecordingEnabled;
        this.timeout = timeout;
        this.appiumBrokerAddress = appiumBrokerAddress;
        this.maxAutoRetriesCount = maxAutoRetriesCount;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public boolean isAppCrawlerRun() {
        return appCrawlerRun;
    }

    public void setAppCrawlerRun(boolean appCrawlerRun) {
        this.appCrawlerRun = appCrawlerRun;
    }

    public LimitationType getLimitationType() {
        return limitationType;
    }

    public void setLimitationType(LimitationType limitationType) {
        this.limitationType = limitationType;
    }

    public boolean isRunAvailable() {
        return runAvailable;
    }

    public void setRunAvailable(boolean runAvailable) {
        this.runAvailable = runAvailable;
    }

    public String getScreenshotDir() {
        return screenshotDir;
    }

    public void setScreenshotDir(String screenshotDir) {
        this.screenshotDir = screenshotDir;
    }

    public String getLimitationValue() {
        return limitationValue;
    }

    public void setLimitationValue(String limitationValue) {
        this.limitationValue = limitationValue;
    }

    public String getWithAnnotation() {
        return withAnnotation;
    }

    public void setWithAnnotation(String withAnnotation) {
        this.withAnnotation = withAnnotation;
    }

    public String getWithoutAnnotation() {
        return withoutAnnotation;
    }

    public void setWithoutAnnotation(String withoutAnnotation) {
        this.withoutAnnotation = withoutAnnotation;
    }

    public String getApplicationUsername() {
        return applicationUsername;
    }

    public void setApplicationUsername(String applicationUsername) {
        this.applicationUsername = applicationUsername;
    }

    public String getApplicationPassword() {
        return applicationPassword;
    }

    public void setApplicationPassword(String applicationPassword) {
        this.applicationPassword = applicationPassword;
    }

    public Long getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(Long deviceGroupId) {
        this.usedDeviceGroupId = deviceGroupId;
        this.deviceGroupId = deviceGroupId;
    }

    public Long getUsedDeviceGroupId() {
        return usedDeviceGroupId;
    }

    public void setUsedDeviceGroupId(Long deviceGroupId) {
        this.usedDeviceGroupId = deviceGroupId;
        this.deviceGroupId = deviceGroupId;
    }

    public String getUsedDeviceGroupName() {
        return usedDeviceGroupName;
    }

    public void setUsedDeviceGroupName(String usedDeviceGroupName) {
        this.usedDeviceGroupName = usedDeviceGroupName;
    }

    public Long getCreditsPrice() {
        return creditsPrice;
    }

    public void setCreditsPrice(Long creditsPrice) {
        this.creditsPrice = creditsPrice;
    }

    public String getDeviceLanguageCode() {
        return deviceLanguageCode;
    }

    public void setDeviceLanguageCode(String deviceLanguageCode) {
        this.deviceLanguageCode = deviceLanguageCode;
    }

    public String getHookURL() {
        return hookURL;
    }

    public void setHookURL(String hookURL) {
        this.hookURL = hookURL;
    }

    public String getInstrumentationRunner() {
        return instrumentationRunner;
    }

    public void setInstrumentationRunner(String instrumentationRunner) {
        this.instrumentationRunner = instrumentationRunner;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public boolean isVideoRecordingEnabled() {
        return videoRecordingEnabled;
    }

    public void setVideoRecordingEnabled(boolean videoRecordingEnabled) {
        this.videoRecordingEnabled = videoRecordingEnabled;
    }

    public String getAppiumBrokerAddress() {
        return appiumBrokerAddress;
    }

    public void setAppiumBrokerAddress(String appiumBrokerAddress) {
        this.appiumBrokerAddress = appiumBrokerAddress;
    }

    public Integer getMaxAutoRetriesCount() {
        return maxAutoRetriesCount;
    }

    public void setMaxAutoRetriesCount(Integer maxAutoRetriesCount) {
        this.maxAutoRetriesCount = maxAutoRetriesCount;
    }

    public Long getFrameworkId() {
        return frameworkId;
    }

    public void setFrameworkId(Long frameworkId) {
        this.frameworkId = frameworkId;
    }

    public List<APIDeviceGroup> getAvailableDeviceGroups() {
        return availableDeviceGroups;
    }

    public void setAvailableDeviceGroups(List<APIDeviceGroup> availableDeviceGroups) {
        this.availableDeviceGroups = availableDeviceGroups;
    }

    public List<APIDevice> getAvailableDevices() {
        return availableDevices;
    }

    public void setAvailableDevices(List<APIDevice> availableDevices) {
        this.availableDevices = availableDevices;
    }

    public List<APIFramework> getAvailableFrameworks() {
        return availableFrameworks;
    }

    public void setAvailableFrameworks(List<APIFramework> availableFrameworks) {
        this.availableFrameworks = availableFrameworks;
    }

    public List<APIFileConfig> getFiles() {
        return files;
    }

    public void setFiles(List<APIFileConfig> files) {
        this.files = files;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public APIDevice.OsType getOsType() {
        return osType;
    }

    public void setOsType(APIDevice.OsType osType) {
        this.osType = osType;
    }

    public List<APITestRunParameter> getTestRunParameters() {
        return testRunParameters;
    }

    public void setTestRunParameters(List<APITestRunParameter> testRunParameters) {
        this.testRunParameters = testRunParameters;
    }

    public List<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public String getTestRunName() {
        return testRunName;
    }

    public void setTestRunName(String testRunName) {
        this.testRunName = testRunName;
    }

    public Long getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(Long testRunId) {
        this.testRunId = testRunId;
    }

    public String getDeviceNamePattern() {
        return deviceNamePattern;
    }

    public void setDeviceNamePattern(String deviceNamePattern) {
        this.deviceNamePattern = deviceNamePattern;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isResignFiles() {
        return resignFiles;
    }

    public void setResignFiles(boolean resignFiles) {
        this.resignFiles = resignFiles;
    }

    public boolean isLoadedPrevious() {
        return loadedPrevious;
    }

    public void setLoadedPrevious(boolean loadedPrevious) {
        this.loadedPrevious = loadedPrevious;
    }

    public APIClientSideTestConfig getClientSideTestConfig() {
        return clientSideTestConfig;
    }

    public void setClientSideTestConfig(APIClientSideTestConfig clientSideTestConfig) {
        this.clientSideTestConfig = clientSideTestConfig;
    }

    public List<Long> getComputedDevices() {
        return computedDevices;
    }

    public void setComputedDevices(List<Long> computedDevices) {
        this.computedDevices = computedDevices;
    }

    public void update() throws APIException {
        Map<String, Object> body = new HashMap<>();
        body.put("scheduler", scheduler != null ? scheduler.name() : null);
        body.put("appCrawlerRun", appCrawlerRun);
        body.put("screenshotDir", screenshotDir);
        body.put("limitationType", limitationType != null ? limitationType.name() : null);
        body.put("limitationValue", limitationValue);
        body.put("withAnnotation", withAnnotation);
        body.put("withoutAnnotation", withoutAnnotation);
        body.put("applicationUsername", applicationUsername);
        body.put("applicationPassword", applicationPassword);
        body.put("usedDeviceGroupId", deviceGroupId);
        body.put("deviceLanguageCode", deviceLanguageCode);
        body.put("hookURL", hookURL);
        body.put("instrumentationRunner", instrumentationRunner);
        body.put("timeout", timeout);
        body.put("appiumBrokerAddress", appiumBrokerAddress);
        body.put("maxAutoRetriesCount", maxAutoRetriesCount);
        APITestRunConfig config = postResource(selfURI, body, APITestRunConfig.class);
        clone(config);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APITestRunConfig apiTestRunConfig = (APITestRunConfig) from;
        cloneBase(from);
        this.applicationPassword = apiTestRunConfig.applicationPassword;
        this.applicationUsername = apiTestRunConfig.applicationUsername;
        this.creditsPrice = apiTestRunConfig.creditsPrice;
        this.deviceLanguageCode = apiTestRunConfig.deviceLanguageCode;
        this.videoRecordingEnabled = apiTestRunConfig.videoRecordingEnabled;
        this.hookURL = apiTestRunConfig.hookURL;
        this.instrumentationRunner = apiTestRunConfig.instrumentationRunner;
        this.limitationType = apiTestRunConfig.limitationType;
        this.limitationValue = apiTestRunConfig.limitationValue;
        this.appCrawlerRun = apiTestRunConfig.appCrawlerRun;
        this.projectId = apiTestRunConfig.projectId;
        this.runAvailable = apiTestRunConfig.runAvailable;
        this.scheduler = apiTestRunConfig.scheduler;
        this.screenshotDir = apiTestRunConfig.screenshotDir;
        this.deviceGroupId = apiTestRunConfig.deviceGroupId;
        this.usedDeviceGroupId = apiTestRunConfig.usedDeviceGroupId;
        this.usedDeviceGroupName = apiTestRunConfig.usedDeviceGroupName;
        this.withAnnotation = apiTestRunConfig.withAnnotation;
        this.withoutAnnotation = apiTestRunConfig.withoutAnnotation;
        this.timeout = apiTestRunConfig.timeout;
        this.appiumBrokerAddress = apiTestRunConfig.appiumBrokerAddress;
        this.maxAutoRetriesCount = apiTestRunConfig.maxAutoRetriesCount;
        this.frameworkId = apiTestRunConfig.frameworkId;
        this.availableDeviceGroups = apiTestRunConfig.availableDeviceGroups;
        this.availableDevices = apiTestRunConfig.availableDevices;
        this.availableFrameworks = apiTestRunConfig.availableFrameworks;
        this.files = apiTestRunConfig.files;
        this.status = apiTestRunConfig.status;
        this.osType = apiTestRunConfig.osType;
        this.testRunParameters = apiTestRunConfig.testRunParameters;
        this.deviceIds = apiTestRunConfig.deviceIds;
        this.testRunName = apiTestRunConfig.testRunName;
        this.testRunId = apiTestRunConfig.testRunId;
        this.projectName = apiTestRunConfig.projectName;
        this.statusCode = apiTestRunConfig.statusCode;
        this.resignFiles = apiTestRunConfig.resignFiles;
    }
}
