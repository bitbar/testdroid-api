package com.testdroid.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testdroid.api.dto.Context;
import com.testdroid.api.model.*;
import com.testdroid.api.model.build.APIBuildConfig;
import com.testdroid.api.model.build.APIBuildResultConfig;
import com.testdroid.api.model.jrjc.*;
import com.testdroid.api.util.TypeReferenceFactory;

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
        APIArray.class,
        APIBillingPeriod.class,
        APICloudInfo.class,
        APIConnection.class,
        APIDevice.class,
        APIDeviceGroup.class,
        APIDeviceProperty.class,
        APIDeviceSession.class,
        APIDeviceSessionStep.class,
        APIExceptionMessage.class,
        APIFileSet.class,
        APILabelGroup.class,
        APIList.class,
        APIProject.class,
        APIProjectJobConfig.class,
        APIProjectSharing.class,
        APIProjectTypeExtended.class,
        APIRole.class,
        APIService.class,
        APIScreenshot.class,
        APIScreenshotExtended.class,
        APISoftwareVersion.class,
        APITag.class,
        APITestCaseRun.class,
        APITestCaseRunStep.class,
        APITestRun.class,
        APITestRunConfig.class,
        APITestRunExtended.class,
        APITestRunParameter.class,
        APIUser.class,
        APIUserFile.class,
        APIUserFileProperty.class,
        APIUserFileTag.class,
        APIAccountService.class,
        APIBasicJiraProject.class,
        APIBasicJiraIssue.class,
        APIJiraIssue.class,
        APIJiraIssueType.class,
        APIJiraPriority.class,
        APIJiraProject.class,
        APIJiraServerInfo.class,
        APIDeviceSessionDataAvailability.class,
        APITestRunDataAvailability.class,
        APIUserDeviceTime.class,
        APIDeviceTimeEntry.class,
        APIFramework.class,
        APIS3Url.class,
        APIDeviceCleanupConfiguration.class,
        APIAdminDevice.class,
        APIAdminDeviceModel.class,
        APIAdminDeviceType.class,
        APIAdminDeviceSession.class,
        APICluster.class,
        APIDeviceModelCriterion.class,
        APIDeviceStatus.class,
        APIAdminEmail.class,
        APIAdminError.class,
        APIProperty.class,
        APISession.class,
        APIAdminTestRun.class,
        APIActiveUser.class,
        APIAdminOverview.class,
        APIAdminDeviceProblem.class,
        APIAdminDeviceProblemPair.class,
        APIAccount.class,
        APILicense.class,
        APIAdminInteractiveDeviceSession.class,
        APIUserIntegration.class,
        APIAdminDeviceSessionStatistics.class,
        APIActivity.class,
        APIDevicePicker.class,
        APIDeviceFilter.class,
        APIDeviceFilterGroup.class,
        APICountryVatRate.class,
        APIMarketShare.class,
        APIFileConfig.class,
        APIBuildExecutor.class,
        APIBuildConfig.class,
        APIBuildResultConfig.class
})
public abstract class APIEntity {

    private static final DateFormat API_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH:mm");

    protected APIClient client;

    protected Long id;

    protected String selfURI;

    public APIEntity() {
    }

    public APIEntity(Long id) {
        this.id = id;
    }

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
        return new ObjectMapper().writeValueAsString(this);
    }

    @JsonIgnore
    protected abstract <T extends APIEntity> void clone(T from);

    @JsonIgnore
    protected <T extends APIEntity> void cloneBase(T from) {
        this.id = from.id;
    }

}
