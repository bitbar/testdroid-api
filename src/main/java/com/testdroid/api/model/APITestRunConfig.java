package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.APIQueryBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;

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
    public static enum Mode {

        FULL_RUN("Full run"),
        APP_CRAWLER("App crawler"),
        CTS("CTS"),
        IOS("iOS"),
        UIAUTOMATOR("UIAutomator"),
        REMOTECONTROL("Remote control"),
        CALABASH("Calabash Android"),
        CALABASH_IOS("Calabash iOS"),
        APPIUM_ANDROID("Appium Android"),
        APPIUM_IOS("Appium iOS"),
        IOS_CRAWLER("iOS App crawler"),
        TELERIK_ANDROID("Telerik Android"),
        TELERIK_IOS("Telerik iOS");

        private String friendlyName;

        private Mode(String friendlyName) {
            this.friendlyName = friendlyName;
        }

        public String getFriendlyName() {
            return friendlyName;
        }
    }

    @XmlType(namespace = "APITestRunConfig")
    public static enum Scheduler {
        PARALLEL,
        SERIAL,
        SINGLE
    }

    private boolean appRequired;

    private String applicationPassword;

    private String applicationUsername;

    private boolean autoScreenshots;

    private boolean checkApp;

    private Integer creditsPrice;

    private String deviceLanguageCode;

    private boolean gamebenchEnabled;

    private String hookURL;

    private String instrumentationRunner;

    private boolean launchApp;

    private LimitationType limitationType;

    private String limitationValue;

    private Mode mode;

    private Long projectId;

    private boolean runAvailable;

    private Scheduler scheduler;

    private String screenshotDir;

    private String uiAutomatorTestClasses;

    private Long usedDeviceGroupId;

    private String withAnnotation;

    private String withoutAnnotation;

    private Long timeout;

    public APITestRunConfig() {
    }

    public APITestRunConfig(
            Long id, Long projectId, Scheduler scheduler, Mode mode, boolean autoScreenshots, boolean runAvailable,
            String screenshotDir, LimitationType limitationType, String limitationValue, String withAnnotation,
            String withoutAnnotation, String applicationUsername, String applicationPassword, Long usedClusterId,
            Integer creditsPrice, String deviceLanguageCode, String hookURL, String uiAutomatorTestClasses,
            Boolean launchApp, String instrumentationRunner, Boolean checkApp, Boolean appRequired,
            Boolean gamebenchEnabled, Long timeout) {
        super(id);
        this.projectId = projectId;
        this.scheduler = scheduler;
        this.mode = mode;
        this.autoScreenshots = autoScreenshots;
        this.runAvailable = runAvailable;
        this.screenshotDir = screenshotDir;
        this.limitationType = limitationType;
        this.limitationValue = limitationValue;
        this.withAnnotation = withAnnotation;
        this.withoutAnnotation = withoutAnnotation;
        this.applicationUsername = applicationUsername;
        this.applicationPassword = applicationPassword;
        this.usedDeviceGroupId = usedClusterId;
        this.creditsPrice = creditsPrice;
        this.deviceLanguageCode = deviceLanguageCode;
        this.hookURL = hookURL;
        this.uiAutomatorTestClasses = uiAutomatorTestClasses;
        this.launchApp = launchApp;
        this.instrumentationRunner = instrumentationRunner;
        this.checkApp = checkApp;
        this.appRequired = appRequired;
        this.gamebenchEnabled = gamebenchEnabled;
        this.timeout = timeout;
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

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
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

    public Integer getCreditsPrice() {
        return creditsPrice;
    }

    public void setCreditsPrice(Integer creditsPrice) {
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

    public void setGamebenchEnabled(boolean gamebenchEnabled) {
        this.gamebenchEnabled = gamebenchEnabled;
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
        Map<String, Object> body = new HashMap<String, Object>() {{
            put("scheduler", scheduler != null ? scheduler.name() : null);
            put("mode", mode != null ? mode.name() : null);
            put("autoScreenshots", autoScreenshots);
            put("screenshotDir", screenshotDir);
            put("limitationType", limitationType != null ? limitationType.name() : null);
            put("limitationValue", limitationValue);
            put("withAnnotation", withAnnotation);
            put("withoutAnnotation", withoutAnnotation);
            put("applicationUsername", applicationUsername);
            put("applicationPassword", applicationPassword);
            put("usedDeviceGroupId", usedDeviceGroupId);
            put("deviceLanguageCode", deviceLanguageCode);
            put("hookURL", hookURL);
            put("uiAutomatorTestClasses", uiAutomatorTestClasses);
            put("launchApp", launchApp);
            put("instrumentationRunner", instrumentationRunner);
            put("checkApp", checkApp);
            put("appRequired", appRequired);
            put("gamebenchEnabled", gamebenchEnabled);
            put("timeout", timeout);
        }};
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
        this.hookURL = apiTestRunConfig.hookURL;
        this.instrumentationRunner = apiTestRunConfig.instrumentationRunner;
        this.launchApp = apiTestRunConfig.launchApp;
        this.limitationType = apiTestRunConfig.limitationType;
        this.limitationValue = apiTestRunConfig.limitationValue;
        this.mode = apiTestRunConfig.mode;
        this.projectId = apiTestRunConfig.projectId;
        this.runAvailable = apiTestRunConfig.runAvailable;
        this.scheduler = apiTestRunConfig.scheduler;
        this.screenshotDir = apiTestRunConfig.screenshotDir;
        this.uiAutomatorTestClasses = apiTestRunConfig.uiAutomatorTestClasses;
        this.usedDeviceGroupId = apiTestRunConfig.usedDeviceGroupId;
        this.withAnnotation = apiTestRunConfig.withAnnotation;
        this.withoutAnnotation = apiTestRunConfig.withoutAnnotation;
        this.timeout = apiTestRunConfig.timeout;
    }
}
