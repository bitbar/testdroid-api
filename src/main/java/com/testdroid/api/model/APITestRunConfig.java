package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.APIQueryBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APITestRunConfig extends APIEntity {

    @XmlType(namespace = "APITestRunConfig")
    public static enum LimitationType {
        PACKAGE,
        CLASS
    }

    @XmlType(namespace = "APITestRunConfig")
    public static enum Scheduler {
        PARALLEL,
        SERIAL,
        SINGLE
    }

    private boolean createVNCConnection;

    private String appiumBrokerAddress;

    private boolean appRequired;

    private String applicationPassword;

    private String applicationUsername;

    private boolean autoScreenshots;

    private boolean checkApp;

    private Long creditsPrice;

    private String deviceLanguageCode;

    private boolean gamebenchEnabled;

    private boolean useAdditionalFiles;

    private boolean videoRecordingEnabled;

    private String hookURL;

    private String instrumentationRunner;

    private boolean launchApp;

    private LimitationType limitationType;

    private String limitationValue;

    private Boolean appCrawlerRun;

    private Long projectId;

    private boolean runAvailable;

    private Scheduler scheduler;

    private String screenshotDir;

    private String uiAutomatorTestClasses;

    private Long usedDeviceGroupId;

    private String usedDeviceGroupName;

    private String withAnnotation;

    private String withoutAnnotation;

    private Long timeout;

    private Integer maxAutoRetriesCount;

    public APITestRunConfig() {
    }

    public APITestRunConfig(
            Long id, Scheduler scheduler, Boolean appCrawlerRun, Boolean autoScreenshots,
            String screenshotDir, LimitationType limitationType, String limitationValue, String withAnnotation,
            String withoutAnnotation, String applicationUsername, String applicationPassword, Long usedDeviceGroupId,
            String usedDeviceGroupName, Long creditsPrice, String deviceLanguageCode, String hookURL,
            String uiAutomatorTestClasses, Boolean launchApp, String instrumentationRunner, Boolean checkApp,
            Boolean appRequired, Boolean gamebenchEnabled, Boolean useAdditionalFiles, Boolean videoRecordingEnabled,
            Long timeout, String appiumBrokerAddress, Boolean createVNCConnection, Integer maxAutoRetriesCount) {
        super(id);
        this.scheduler = scheduler;
        this.appCrawlerRun = appCrawlerRun;
        this.autoScreenshots = autoScreenshots;
        this.screenshotDir = screenshotDir;
        this.limitationType = limitationType;
        this.limitationValue = limitationValue;
        this.withAnnotation = withAnnotation;
        this.withoutAnnotation = withoutAnnotation;
        this.applicationUsername = applicationUsername;
        this.applicationPassword = applicationPassword;
        this.usedDeviceGroupId = usedDeviceGroupId;
        this.usedDeviceGroupName = usedDeviceGroupName;
        this.creditsPrice = creditsPrice;
        this.deviceLanguageCode = deviceLanguageCode;
        this.hookURL = hookURL;
        this.uiAutomatorTestClasses = uiAutomatorTestClasses;
        this.launchApp = launchApp;
        this.instrumentationRunner = instrumentationRunner;
        this.checkApp = checkApp;
        this.appRequired = appRequired;
        this.gamebenchEnabled = gamebenchEnabled;
        this.videoRecordingEnabled = videoRecordingEnabled;
        this.timeout = timeout;
        this.useAdditionalFiles = useAdditionalFiles;
        this.appiumBrokerAddress = appiumBrokerAddress;
        this.createVNCConnection = createVNCConnection;
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

    public Boolean getAppCrawlerRun() {
        return appCrawlerRun;
    }

    public void setAppCrawlerRun(Boolean appCrawlerRun) {
        this.appCrawlerRun = appCrawlerRun;
    }

    public LimitationType getLimitationType() {
        return limitationType;
    }

    public void setLimitationType(LimitationType limitationType) {
        this.limitationType = limitationType;
    }

    public boolean isAutoScreenshots() {
        return autoScreenshots;
    }

    public void setAutoScreenshots(boolean autoScreenshots) {
        this.autoScreenshots = autoScreenshots;
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

    public Long getUsedDeviceGroupId() {
        return usedDeviceGroupId;
    }

    public void setUsedDeviceGroupId(Long usedDeviceGroupId) {
        this.usedDeviceGroupId = usedDeviceGroupId;
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

    public String getUiAutomatorTestClasses() {
        return uiAutomatorTestClasses;
    }

    public void setUiAutomatorTestClasses(String uiAutomatorTestClasses) {
        this.uiAutomatorTestClasses = uiAutomatorTestClasses;
    }

    public boolean isLaunchApp() {
        return launchApp;
    }

    public void setLaunchApp(boolean launchApp) {
        this.launchApp = launchApp;
    }

    public String getInstrumentationRunner() {
        return instrumentationRunner;
    }

    public void setInstrumentationRunner(String instrumentationRunner) {
        this.instrumentationRunner = instrumentationRunner;
    }

    public boolean isCheckApp() {
        return checkApp;
    }

    public void setCheckApp(boolean checkApp) {
        this.checkApp = checkApp;
    }

    public boolean isAppRequired() {
        return appRequired;
    }

    public void setAppRequired(boolean appRequired) {
        this.appRequired = appRequired;
    }

    private String getParametersURI() {
        return createUri(selfURI, "/parameters");
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public boolean isGamebenchEnabled() {
        return gamebenchEnabled;
    }

    public boolean getUseAdditionalFiles() {
        return useAdditionalFiles;
    }

    public void setUseAdditionalFiles(boolean useAdditionalFiles) {
        this.useAdditionalFiles = useAdditionalFiles;
    }

    public void setGamebenchEnabled(boolean gamebenchEnabled) {
        this.gamebenchEnabled = gamebenchEnabled;
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

    public boolean isCreateVNCConnection() {
        return createVNCConnection;
    }

    public void setCreateVNCConnection(boolean createVNCConnection) {
        this.createVNCConnection = createVNCConnection;
    }

    public Integer getMaxAutoRetriesCount() {
        return maxAutoRetriesCount;
    }

    public void setMaxAutoRetriesCount(Integer maxAutoRetriesCount) {
        this.maxAutoRetriesCount = maxAutoRetriesCount;
    }

    @JsonIgnore
    public APITestRunParameter createParameter(final String key, final String value) throws APIException {
        Map<String, Object> body = new HashMap<String, Object>() {{
            put("key", key);
            put("value", value);
        }};
        return postResource(getParametersURI(), body, APITestRunParameter.class);
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
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("scheduler", scheduler != null ? scheduler.name() : null);
        body.put("appCrawlerRun", appCrawlerRun);
        body.put("autoScreenshots", autoScreenshots);
        body.put("screenshotDir", screenshotDir);
        body.put("limitationType", limitationType != null ? limitationType.name() : null);
        body.put("limitationValue", limitationValue);
        body.put("withAnnotation", withAnnotation);
        body.put("withoutAnnotation", withoutAnnotation);
        body.put("applicationUsername", applicationUsername);
        body.put("applicationPassword", applicationPassword);
        body.put("usedDeviceGroupId", usedDeviceGroupId);
        body.put("deviceLanguageCode", deviceLanguageCode);
        body.put("hookURL", hookURL);
        body.put("uiAutomatorTestClasses", uiAutomatorTestClasses);
        body.put("launchApp", launchApp);
        body.put("instrumentationRunner", instrumentationRunner);
        body.put("checkApp", checkApp);
        body.put("appRequired", appRequired);
        body.put("gamebenchEnabled", gamebenchEnabled);
        body.put("timeout", timeout);
        body.put("appiumBrokerAddress", appiumBrokerAddress);
        body.put("createVNCConnection", createVNCConnection);
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
        this.appRequired = apiTestRunConfig.appRequired;
        this.autoScreenshots = apiTestRunConfig.autoScreenshots;
        this.checkApp = apiTestRunConfig.checkApp;
        this.creditsPrice = apiTestRunConfig.creditsPrice;
        this.deviceLanguageCode = apiTestRunConfig.deviceLanguageCode;
        this.gamebenchEnabled = apiTestRunConfig.gamebenchEnabled;
        this.videoRecordingEnabled = apiTestRunConfig.videoRecordingEnabled;
        this.hookURL = apiTestRunConfig.hookURL;
        this.instrumentationRunner = apiTestRunConfig.instrumentationRunner;
        this.launchApp = apiTestRunConfig.launchApp;
        this.limitationType = apiTestRunConfig.limitationType;
        this.limitationValue = apiTestRunConfig.limitationValue;
        this.appCrawlerRun = apiTestRunConfig.appCrawlerRun;
        this.projectId = apiTestRunConfig.projectId;
        this.runAvailable = apiTestRunConfig.runAvailable;
        this.scheduler = apiTestRunConfig.scheduler;
        this.screenshotDir = apiTestRunConfig.screenshotDir;
        this.uiAutomatorTestClasses = apiTestRunConfig.uiAutomatorTestClasses;
        this.usedDeviceGroupId = apiTestRunConfig.usedDeviceGroupId;
        this.usedDeviceGroupName = apiTestRunConfig.usedDeviceGroupName;
        this.withAnnotation = apiTestRunConfig.withAnnotation;
        this.withoutAnnotation = apiTestRunConfig.withoutAnnotation;
        this.timeout = apiTestRunConfig.timeout;
        this.useAdditionalFiles = apiTestRunConfig.useAdditionalFiles;
        this.appiumBrokerAddress = apiTestRunConfig.appiumBrokerAddress;
        this.createVNCConnection = apiTestRunConfig.createVNCConnection;
        this.maxAutoRetriesCount = apiTestRunConfig.maxAutoRetriesCount;
    }
}
