package com.testdroid.api.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.testdroid.api.*;
import com.testdroid.api.model.*;
import com.testdroid.api.model.capabilities.APIDesktopBrowserCapabilities;
import com.testdroid.api.model.devicetime.APIBasicDeviceTime;
import com.testdroid.api.model.devicetime.APIDeviceTimeCountSessionReportEntry;
import com.testdroid.api.model.devicetime.APIDeviceTimeStepTimeReportEntry;
import com.testdroid.api.model.notification.APINotification;
import com.testdroid.api.model.notification.APINotificationPlan;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class TypeReferenceFactory {

    private static final Map<Class<? extends APIEntity>, TypeReference<?>> LIST_MAP = new HashMap<>();

    static {
        //@formatter:off
        LIST_MAP.put(APIAccessGroup.class, new TypeReference<APIList<APIAccessGroup>>() {});
        LIST_MAP.put(APIAccount.class, new TypeReference<APIList<APIAccount>>() {});
        LIST_MAP.put(APIAccountService.class, new TypeReference<APIList<APIAccountService>>() {});
        LIST_MAP.put(APIActivity.class, new TypeReference<APIList<APIActivity>>() {});
        LIST_MAP.put(APIAdminDevice.class, new TypeReference<APIList<APIAdminDevice>>() {});
        LIST_MAP.put(APIAdminDeviceModel.class, new TypeReference<APIList<APIAdminDeviceModel>>() {});
        LIST_MAP.put(APIAdminDeviceProblem.class, new TypeReference<APIList<APIAdminDeviceProblem>>() {});
        LIST_MAP.put(APIAdminDeviceSession.class, new TypeReference<APIList<APIAdminDeviceSession>>() {});
        LIST_MAP.put(APIAdminDeviceType.class, new TypeReference<APIList<APIAdminDeviceType>>() {});
        LIST_MAP.put(APIAdminEmail.class, new TypeReference<APIList<APIAdminEmail>>() {});
        LIST_MAP.put(APIAdminError.class, new TypeReference<APIList<APIAdminError>>() {});
        LIST_MAP.put(APIAdminFrameworkStatistics.class, new TypeReference<APIList<APIAdminFrameworkStatistics>>() {});
        LIST_MAP.put(APIAdminInteractiveDeviceSession.class, new TypeReference<APIList<APIAdminInteractiveDeviceSession>>() {});
        LIST_MAP.put(APIAdminTestRun.class, new TypeReference<APIList<APIAdminTestRun>>() {});
        LIST_MAP.put(APIBillingPeriod.class, new TypeReference<APIList<APIBillingPeriod>>() {});
        LIST_MAP.put(APIBillingPeriodUsage.class, new TypeReference<APIList<APIBillingPeriodUsage>>() {});
        LIST_MAP.put(APIBrowser.class, new TypeReference<APIList<APIBrowser>>() {});
        LIST_MAP.put(APICluster.class, new TypeReference<APIList<APICluster>>() {});
        LIST_MAP.put(APICommand.class, new TypeReference<APIList<APICommand>>() {});
        LIST_MAP.put(APIConnection.class, new TypeReference<APIList<APIConnection>>() {});
        LIST_MAP.put(APICountryVatRate.class, new TypeReference<APIList<APICountryVatRate>>() {});
        LIST_MAP.put(APIDevice.class, new TypeReference<APIList<APIDevice>>() {});
        LIST_MAP.put(APIDeviceCleanupConfiguration.class, new TypeReference<APIList<APIDeviceCleanupConfiguration>>() {});
        LIST_MAP.put(APIDeviceGroup.class, new TypeReference<APIList<APIDeviceGroup>>() {});
        LIST_MAP.put(APIDeviceModelCriterion.class, new TypeReference<APIList<APIDeviceModelCriterion>>() {});
        LIST_MAP.put(APIDeviceModelPool.class, new TypeReference<APIList<APIDeviceModelPool>>() {});
        LIST_MAP.put(APIDeviceProperty.class, new TypeReference<APIList<APIDeviceProperty>>() {});
        LIST_MAP.put(APIDeviceSession.class, new TypeReference<APIList<APIDeviceSession>>() {});
        LIST_MAP.put(APIDeviceSessionStep.class, new TypeReference<APIList<APIDeviceSessionStep>>() {});
        LIST_MAP.put(APIDeviceStatistic.class, new TypeReference<APIList<APIDeviceStatistic>>() {});
        LIST_MAP.put(APIDeviceStatus.class, new TypeReference<APIList<APIDeviceStatus>>() {});
        LIST_MAP.put(APIDeviceTimeCountSessionReportEntry.class, new TypeReference<APIList<APIDeviceTimeCountSessionReportEntry>>() {});
        LIST_MAP.put(APIDeviceTimeStepTimeReportEntry.class, new TypeReference<APIList<APIDeviceTimeStepTimeReportEntry>>() {});
        LIST_MAP.put(APIDeviceUsage.class, new TypeReference<APIList<APIDeviceUsage>>() {});
        LIST_MAP.put(APIEnum.class, new TypeReference<APIList<APIEnum>>() {});
        LIST_MAP.put(APIFramework.class, new TypeReference<APIList<APIFramework>>() {});
        LIST_MAP.put(APILabelGroup.class, new TypeReference<APIList<APILabelGroup>>() {});
        LIST_MAP.put(APILicense.class, new TypeReference<APIList<APILicense>>() {});
        LIST_MAP.put(APIMarketShare.class, new TypeReference<APIList<APIMarketShare>>() {});
        LIST_MAP.put(APINotification.class, new TypeReference<APIList<APINotification>>() {});
        LIST_MAP.put(APINotificationPlan.class, new TypeReference<APIList<APINotificationPlan>>() {});
        LIST_MAP.put(APIProject.class, new TypeReference<APIList<APIProject>>() {});
        LIST_MAP.put(APIProjectJobConfig.class, new TypeReference<APIList<APIProjectJobConfig>>() {});
        LIST_MAP.put(APIProperty.class, new TypeReference<APIList<APIProperty>>() {});
        LIST_MAP.put(APIRole.class, new TypeReference<APIList<APIRole>>() {});
        LIST_MAP.put(APIScreenshot.class, new TypeReference<APIList<APIScreenshot>>() {});
        LIST_MAP.put(APIScreenshotExtended.class, new TypeReference<APIList<APIScreenshotExtended>>() {});
        LIST_MAP.put(APIService.class, new TypeReference<APIList<APIService>>() {});
        LIST_MAP.put(APISharedResource.class, new TypeReference<APIList<APISharedResource>>() {});
        LIST_MAP.put(APITag.class, new TypeReference<APIList<APITag>>() {});
        LIST_MAP.put(APITestCaseRun.class, new TypeReference<APIList<APITestCaseRun>>() {});
        LIST_MAP.put(APITestRun.class, new TypeReference<APIList<APITestRun>>() {});
        LIST_MAP.put(APITestRunParameter.class, new TypeReference<APIList<APITestRunParameter>>() {});
        LIST_MAP.put(APIUser.class, new TypeReference<APIList<APIUser>>() {});
        LIST_MAP.put(APIUserDeviceTime.class, new TypeReference<APIList<APIUserDeviceTime>>() {});
        LIST_MAP.put(APIUserFile.class, new TypeReference<APIList<APIUserFile>>() {});
        LIST_MAP.put(APIUserFileProperty.class, new TypeReference<APIList<APIUserFileProperty>>() {});
        LIST_MAP.put(APIUserFileTag.class, new TypeReference<APIList<APIUserFileTag>>() {});
        //@formatter:on
    }

    private static final Map<Class<? extends APIEntity>, TypeReference<?>> SIMPLE_LIST_MAP = new HashMap<>();

    static {
        //@formatter:off
        SIMPLE_LIST_MAP.put(APIBrokerHub.class, new TypeReference<APISimpleList<APIBrokerHub>>() {});
        //@formatter:on
    }

    private static final Map<Class<?>, TypeReference<?>> MAP = new HashMap<>();

    static {
        //@formatter:off
        MAP.put(APIAccessGroup.class, new TypeReference<APIAccessGroup>() {});
        MAP.put(APIAccount.class, new TypeReference<APIAccount>() {});
        MAP.put(APIAccountConcurrencyStatus.class, new TypeReference<APIAccountConcurrencyStatus>() {});
        MAP.put(APIAccountPreference.class, new TypeReference<APIAccountPreference>() {});
        MAP.put(APIAccountService.class, new TypeReference<APIAccountService>() {});
        MAP.put(APIAccountServicePayment.class, new TypeReference<APIAccountServicePayment>() {});
        MAP.put(APIAdminDevice.class, new TypeReference<APIAdminDevice>() {});
        MAP.put(APIAdminDeviceSessionStatistics.class, new TypeReference<APIAdminDeviceSessionStatistics>() {});
        MAP.put(APIAdminDeviceType.class, new TypeReference<APIAdminDeviceType>() {});
        MAP.put(APIAdminEmail.class, new TypeReference<APIAdminEmail>() {});
        MAP.put(APIAdminOverview.class, new TypeReference<APIAdminOverview>() {});
        MAP.put(APIAdminTestRun.class, new TypeReference<APIAdminTestRun>() {});
        MAP.put(APIBasicDeviceTime.class, new TypeReference<APIBasicDeviceTime>() {});
        MAP.put(APIBillingPeriod.class, new TypeReference<APIBillingPeriod>() {});
        MAP.put(APIBillingPeriodUsage.class, new TypeReference<APIBillingPeriodUsage>() {});
        MAP.put(APIBrowser.class, new TypeReference<APIBrowser>() {});
        MAP.put(APICloudInfo.class, new TypeReference<APICloudInfo>() {});
        MAP.put(APICluster.class, new TypeReference<APICluster>() {});
        MAP.put(APIConnection.class, new TypeReference<APIConnection>() {});
        MAP.put(APICountryVatRate.class, new TypeReference<APICountryVatRate>() {});
        MAP.put(APIDesktopBrowserCapabilities.class, new TypeReference<APIDesktopBrowserCapabilities>() {});
        MAP.put(APIDevice.class, new TypeReference<APIDevice>() {});
        MAP.put(APIDeviceCleanupConfiguration.class, new TypeReference<APIDeviceCleanupConfiguration>() {});
        MAP.put(APIDeviceGroup.class, new TypeReference<APIDeviceGroup>() {});
        MAP.put(APIDeviceModelCriterion.class, new TypeReference<APIDeviceModelCriterion>() {});
        MAP.put(APIDeviceModelPool.class, new TypeReference<APIDeviceModelPool>() {});
        MAP.put(APIDeviceModelPoolStatistics.class, new TypeReference<APIDeviceModelPoolStatistics>() {});
        MAP.put(APIDevicePicker.class, new TypeReference<APIDevicePicker>() {});
        MAP.put(APIDeviceProperty.class, new TypeReference<APIDeviceProperty>() {});
        MAP.put(APIDeviceSession.class, new TypeReference<APIDeviceSession>() {});
        MAP.put(APIDeviceSessionStep.class, new TypeReference<APIDeviceSessionStep>() {});
        MAP.put(APIExceptionMessage.class, new TypeReference<APIExceptionMessage>() {});
        MAP.put(APIFramework.class, new TypeReference<APIFramework>() {});
        MAP.put(APIHealthCheck.class, new TypeReference<APIHealthCheck>() {});
        MAP.put(APILabelGroup.class, new TypeReference<APILabelGroup>() {});
        MAP.put(APILicense.class, new TypeReference<APILicense>() {});
        MAP.put(APIMaintenance.class, new TypeReference<APIMaintenance>() {});
        MAP.put(APIMarketShare.class, new TypeReference<APIMarketShare>() {});
        MAP.put(APIMessage.class, new TypeReference<APIMessage>() {});
        MAP.put(APINotification.class, new TypeReference<APINotification>() {});
        MAP.put(APINotificationPlan.class, new TypeReference<APINotificationPlan>() {});
        MAP.put(APIProject.class, new TypeReference<APIProject>() {});
        MAP.put(APIProjectJobConfig.class, new TypeReference<APIProjectJobConfig>() {});
        MAP.put(APIProperty.class, new TypeReference<APIProperty>() {});
        MAP.put(APIRole.class, new TypeReference<APIRole>() {});
        MAP.put(APIS3Url.class, new TypeReference<APIS3Url>() {});
        MAP.put(APIService.class, new TypeReference<APIService>() {});
        MAP.put(APIServicePaymentStatus.class, new TypeReference<APIServicePaymentStatus>() {});
        MAP.put(APISharedResource.class, new TypeReference<APISharedResource>() {});
        MAP.put(APITag.class, new TypeReference<APITag>() {});
        MAP.put(APITestRun.class, new TypeReference<APITestRun>() {});
        MAP.put(APITestRunConfig.class, new TypeReference<APITestRunConfig>() {});
        MAP.put(APITestRunDataAvailability.class, new TypeReference<APITestRunDataAvailability>() {});
        MAP.put(APITestRunParameter.class, new TypeReference<APITestRunParameter>() {});
        MAP.put(APIUser.class, new TypeReference<APIUser>() {});
        MAP.put(APIUserDeviceTimeSummary.class, new TypeReference<APIUserDeviceTimeSummary>() {});
        MAP.put(APIUserFile.class, new TypeReference<APIUserFile>() {});
        MAP.put(APIUserFileProperty.class, new TypeReference<APIUserFileProperty>() {});
        MAP.put(APIUserPreference.class, new TypeReference<APIUserPreference>() {});
        MAP.put(APIUserStatistics.class, new TypeReference<APIUserStatistics>() {});
        MAP.put(Properties.class, new TypeReference<Properties>() {});
        MAP.put(String.class, new TypeReference<String>() {});
        //@formatter:on
    }

    @SuppressWarnings("unchecked")
    public static <T> TypeReference<T> getTypeRef(Class<T> clazz) {
        return (TypeReference<T>) MAP.get(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T extends APIEntity> TypeReference<APIList<T>> getListTypeRef(Class<T> clazz) {
        return (TypeReference<APIList<T>>) LIST_MAP.get(clazz);
    }

    public static <T extends APIEntity> TypeReference<APISimpleList<T>> getSimpleListTypeRef(Class<T> clazz) {
        return (TypeReference<APISimpleList<T>>) SIMPLE_LIST_MAP.get(clazz);
    }

    public static TypeReference<Map<String, String>> getMapTypeReference() {
        return new TypeReference<Map<String, String>>() {
        };
    }
}
