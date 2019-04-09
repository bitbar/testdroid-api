package com.testdroid.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testdroid.api.dto.Context;
import com.testdroid.api.model.*;
import com.testdroid.api.model.build.APIBuildConfig;
import com.testdroid.api.model.build.APIBuildResultConfig;
import com.testdroid.api.model.devicetime.APIBasicDeviceTime;
import com.testdroid.api.model.devicetime.APIDeviceTimeCountSessionReportEntry;
import com.testdroid.api.model.jrjc.*;
import com.testdroid.api.model.notification.APINotification;
import com.testdroid.api.model.notification.APINotificationPlan;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement(namespace = "com.testdroid.api")
@XmlSeeAlso({
        APIAccessGroup.class,
        APIAccount.class,
        APIAccountService.class,
        APIAccountServicePayment.class,
        APIActiveUser.class,
        APIActivity.class,
        APIAdminDevice.class,
        APIAdminDeviceEvent.class,
        APIAdminDeviceModel.class,
        APIAdminDeviceProblem.class,
        APIAdminDeviceProblemPair.class,
        APIAdminDeviceSession.class,
        APIAdminDeviceSessionStatistics.class,
        APIAdminDeviceType.class,
        APIAdminEmail.class,
        APIAdminError.class,
        APIAdminInteractiveDeviceSession.class,
        APIAdminOverview.class,
        APIAdminTestRun.class,
        APIArray.class,
        APIBasicDeviceTime.class,
        APIBasicJiraProject.class,
        APIBillingPeriod.class,
        APIBuildConfig.class,
        APIBuildExecutor.class,
        APIBuildResultConfig.class,
        APICloudInfo.class,
        APICluster.class,
        APIConnection.class,
        APICountryVatRate.class,
        APIDevice.class,
        APIDeviceCleanupConfiguration.class,
        APIDeviceFilter.class,
        APIDeviceFilterGroup.class,
        APIDeviceGroup.class,
        APIDeviceModelCriterion.class,
        APIDevicePicker.class,
        APIDeviceProperty.class,
        APIDeviceSession.class,
        APIDeviceSessionDataAvailability.class,
        APIDeviceSessionStep.class,
        APIDeviceStatus.class,
        APIDeviceTimeCountSessionReportEntry.class,
        APIDeviceUsage.class,
        APIDesktopBrowserCapabilities.class,
        APIEnum.class,
        APIExceptionMessage.class,
        APIFileConfig.class,
        APIFileSet.class,
        APIFramework.class,
        APIJiraIssue.class,
        APIJiraIssueType.class,
        APIJiraPriority.class,
        APIJiraProject.class,
        APIJiraServerInfo.class,
        APILabelGroup.class,
        APILicense.class,
        APIList.class,
        APIMarketShare.class,
        APINotification.class,
        APINotificationPlan.class,
        APIProject.class,
        APIProjectJobConfig.class,
        APIProperty.class,
        APIRole.class,
        APIS3Url.class,
        APIScreenshot.class,
        APIScreenshotExtended.class,
        APIService.class,
        APISharedResource.class,
        APISoftwareVersion.class,
        APITag.class,
        APITestCaseRun.class,
        APITestCaseRunStep.class,
        APITestRun.class,
        APITestRunConfig.class,
        APITestRunDataAvailability.class,
        APITestRunExtended.class,
        APITestRunParameter.class,
        APIUser.class,
        APIUserDeviceTime.class,
        APIUserDeviceTimeSummary.class,
        APIUserFile.class,
        APIUserFileProperty.class,
        APIUserFileTag.class,
        APIUserIntegration.class,
        APIUserStatistics.class
})
public abstract class APIEntity {

    private static final DateFormat API_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH:mm");

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    protected APIClient client;

    protected Long id;

    protected String selfURI;

    public APIEntity() {
    }

    public APIEntity(Long id) {
        this.id = id;
    }

    @Deprecated
    @JsonIgnore
    public static String format(Date date) {
        return API_DATE_FORMAT.format(date);
    }

    @JsonIgnore
    protected static String createUri(String preUri, String postUri) {
        if (preUri.contains("?")) {
            String[] parts = preUri.split("\\?");
            return String.format("%s%s", parts[0], postUri);
        } else {
            return String.format("%s%s", preUri, postUri);
        }
    }

    /**
     * Returns ID of entity if such ID exists. Usually it does not exist for
     * lists. Please use
     * <code>hasId()</code> method to check if ID exists.
     */
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelfURI() {
        return selfURI;
    }

    public void setSelfURI(String selfURI) {
        this.selfURI = selfURI;
    }

    @JsonIgnore
    protected <T extends APIEntity> APIResource<T> getResource(String uri, Class<T> type)
            throws APIException {
        checkClient(client);
        return new APIResource<>(client, uri, type);
    }

    @JsonIgnore
    protected <T extends APIEntity> APIListResource<T> getListResource(String uri, Class<T> type) throws APIException {
        checkClient(client);
        return new APIListResource<>(client, uri, type);
    }

    @JsonIgnore
    protected <T extends APIEntity> APIListResource<T> getListResource(String uri, Context<T> context)
            throws APIException {
        checkClient(client);
        return new APIListResource<>(client, uri, context);
    }

    @JsonIgnore
    protected <T extends APIEntity> T postResource(String uri, Object body, Class<T> type) throws APIException {
        checkClient(client);
        return client.post(uri, body, type);
    }

    @JsonIgnore
    protected <T extends APIEntity> T postFile(String uri, File file, String contentType, Class<T> type)
            throws APIException {
        checkClient(client);
        return client.postFile(uri, contentType, file, type);
    }

    @JsonIgnore
    protected void deleteResource(String uri) throws APIException {
        checkClient(client);
        client.delete(uri);
    }

    @JsonIgnore
    protected InputStream getFile(String uri) throws APIException {
        checkClient(client);
        return client.get(uri);
    }

    @JsonIgnore
    public void refresh() throws APIException {
        checkClient(client);
        clone(client.get(selfURI, getClass()));
    }

    @JsonIgnore
    private void checkClient(APIClient client) throws APIException {
        if (client == null) {
            throw new APIException("Missing API client");
        }
    }

    @JsonIgnore
    public String toJson() throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(this);
    }

    @JsonIgnore
    protected abstract <T extends APIEntity> void clone(T from);

    @JsonIgnore
    protected <T extends APIEntity> void cloneBase(T from) {
        this.id = from.id;
    }

}
