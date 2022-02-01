package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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

    private boolean appCrawlerRun;

    private String appiumBrokerAddress;

    private String applicationPassword;

    private String applicationUsername;

    private List<APIDeviceGroup> availableDeviceGroups;

    private List<APIDevice> availableDevices;

    private List<APIFramework> availableFrameworks;

    private List<APIDevice.OsType> availableOsTypes;

    private APIClientSideTestConfig clientSideTestConfig;

    private List<Long> computedDevices;

    private Long creditsPrice;

    private Long deviceGroupId;

    private List<Long> deviceIds;

    private String deviceLanguageCode = Locale.US.toString();

    private String deviceNamePattern;

    private boolean disableResigning;

    private List<APIFileConfig> files = new ArrayList<>();

    private Long frameworkId;

    private String hookURL;

    private String instrumentationRunner;

    private LimitationType limitationType;

    private String limitationValue;

    private boolean loadedPrevious;

    private Integer maxAutoRetriesCount;

    private APIDuration maxTestTimeout;

    private APIDevice.OsType osType = APIDevice.OsType.UNDEFINED;

    private Long projectId;

    private String projectName;

    private boolean resignFiles;

    private boolean runAvailable;

    private Scheduler scheduler = Scheduler.PARALLEL;

    private String screenshotDir;

    private String status;

    private int statusCode;

    private Long testRunId;

    private String testRunName;

    private String testRunNameGrouping;

    private List<APITestRunParameter> testRunParameters = new ArrayList<>();

    private Long timeout;

    private APITunnelSettings tunnelSettings;

    private boolean useSamples;

    @Deprecated // use deviceGroupId
    private Long usedDeviceGroupId;

    private String usedDeviceGroupName;

    private boolean videoRecordingEnabled;

    private String withAnnotation;

    private String withoutAnnotation;

    public APITestRunConfig() {
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

    public void setMaxTestTimeout(APIDuration maxTestTimeout) {
        this.maxTestTimeout = maxTestTimeout;
    }

    public APIDuration getMaxTestTimeout() {
        return maxTestTimeout;
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

    public List<APIDevice.OsType> getAvailableOsTypes() {
        return availableOsTypes;
    }

    public void setAvailableOsTypes(List<APIDevice.OsType> availableOsTypes) {
        this.availableOsTypes = availableOsTypes;
    }

    public List<APIFileConfig> getFiles() {
        return files;
    }

    //TODO maybe we should handle all Collections in that way
    @JsonSetter(nulls = Nulls.AS_EMPTY)
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

    public boolean isUseSamples() {
        return useSamples;
    }

    public void setUseSamples(boolean useSamples) {
        this.useSamples = useSamples;
    }

    public boolean isDisableResigning() {
        return disableResigning;
    }

    public void setDisableResigning(boolean disableResigning) {
        this.disableResigning = disableResigning;
    }

    public Optional<APIUserFile> findAnyFileByAction(APIFileConfig.Action action) {
        return getFiles().stream().filter(fc -> fc.getAction() == action).map(APIFileConfig::getFile).findAny();
    }

    public String getTestRunNameGrouping() {
        return testRunNameGrouping;
    }

    public void setTestRunNameGrouping(String testRunNameGrouping) {
        this.testRunNameGrouping = testRunNameGrouping;
    }

    public APITunnelSettings getTunnelSettings() {
        return tunnelSettings;
    }

    public void setTunnelSettings(APITunnelSettings tunnelSettings) {
        this.tunnelSettings = tunnelSettings;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APITestRunConfig apiTestRunConfig = (APITestRunConfig) from;
        cloneBase(from);
        this.appCrawlerRun = apiTestRunConfig.appCrawlerRun;
        this.appiumBrokerAddress = apiTestRunConfig.appiumBrokerAddress;
        this.applicationPassword = apiTestRunConfig.applicationPassword;
        this.applicationUsername = apiTestRunConfig.applicationUsername;
        this.availableDeviceGroups = apiTestRunConfig.availableDeviceGroups;
        this.availableDevices = apiTestRunConfig.availableDevices;
        this.availableFrameworks = apiTestRunConfig.availableFrameworks;
        this.availableOsTypes = apiTestRunConfig.availableOsTypes;
        this.clientSideTestConfig = apiTestRunConfig.clientSideTestConfig;
        this.creditsPrice = apiTestRunConfig.creditsPrice;
        this.deviceGroupId = apiTestRunConfig.deviceGroupId;
        this.deviceIds = apiTestRunConfig.deviceIds;
        this.deviceLanguageCode = apiTestRunConfig.deviceLanguageCode;
        this.disableResigning = apiTestRunConfig.disableResigning;
        this.files = apiTestRunConfig.files;
        this.frameworkId = apiTestRunConfig.frameworkId;
        this.hookURL = apiTestRunConfig.hookURL;
        this.instrumentationRunner = apiTestRunConfig.instrumentationRunner;
        this.limitationType = apiTestRunConfig.limitationType;
        this.limitationValue = apiTestRunConfig.limitationValue;
        this.maxAutoRetriesCount = apiTestRunConfig.maxAutoRetriesCount;
        this.osType = apiTestRunConfig.osType;
        this.projectId = apiTestRunConfig.projectId;
        this.projectName = apiTestRunConfig.projectName;
        this.resignFiles = apiTestRunConfig.resignFiles;
        this.runAvailable = apiTestRunConfig.runAvailable;
        this.scheduler = apiTestRunConfig.scheduler;
        this.screenshotDir = apiTestRunConfig.screenshotDir;
        this.status = apiTestRunConfig.status;
        this.statusCode = apiTestRunConfig.statusCode;
        this.testRunId = apiTestRunConfig.testRunId;
        this.testRunName = apiTestRunConfig.testRunName;
        this.testRunNameGrouping = apiTestRunConfig.testRunNameGrouping;
        this.testRunParameters = apiTestRunConfig.testRunParameters;
        this.timeout = apiTestRunConfig.timeout;
        this.tunnelSettings = apiTestRunConfig.tunnelSettings;
        this.useSamples = apiTestRunConfig.useSamples;
        this.usedDeviceGroupId = apiTestRunConfig.usedDeviceGroupId;
        this.usedDeviceGroupName = apiTestRunConfig.usedDeviceGroupName;
        this.videoRecordingEnabled = apiTestRunConfig.videoRecordingEnabled;
        this.withAnnotation = apiTestRunConfig.withAnnotation;
        this.withoutAnnotation = apiTestRunConfig.withoutAnnotation;
    }
}
