package com.testdroid.api.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.testdroid.api.*;
import com.testdroid.api.model.*;
import com.testdroid.api.model.build.APIPipelineBuild;
import com.testdroid.api.model.build.APIPipelineJob;
import com.testdroid.api.model.devicetime.APIBasicDeviceTime;
import com.testdroid.api.model.devicetime.APIDeviceTimeCountSessionReportEntry;
import com.testdroid.api.model.devicetime.APIDeviceTimeStepTimeReportEntry;
import com.testdroid.api.model.jrjc.*;
import com.testdroid.api.model.notification.APINotification;
import com.testdroid.api.model.notification.APINotificationPlan;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class TypeReferenceFactory {

    private static final Map<Class<?>, TypeReference<?>> TYPE_REFERENCE_ARRAY_MAP = new HashMap<>();

    static {
        TYPE_REFERENCE_ARRAY_MAP.put(APIProject.Type.class, new TypeReference<APIArray<APIProject.Type>>() {
        });
    }

    private static final Map<Class, TypeReference<?>> TYPE_REFERENCE_LIST_MAP = new HashMap<>();

    static {
        TYPE_REFERENCE_LIST_MAP.put(APIFramework.class, new TypeReference<APIList<APIFramework>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APITestCaseRun.class, new TypeReference<APIList<APITestCaseRun>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIAccount.class, new TypeReference<APIList<APIAccount>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIAccountService.class, new TypeReference<APIList<APIAccountService>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIBillingPeriod.class, new TypeReference<APIList<APIBillingPeriod>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIConnection.class, new TypeReference<APIList<APIConnection>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIDevice.class, new TypeReference<APIList<APIDevice>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIDeviceGroup.class, new TypeReference<APIList<APIDeviceGroup>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIDeviceProperty.class, new TypeReference<APIList<APIDeviceProperty>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIDeviceSession.class, new TypeReference<APIList<APIDeviceSession>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIDeviceSessionStep.class, new TypeReference<APIList<APIDeviceSessionStep>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIDeviceUsage.class, new TypeReference<APIList<APIDeviceUsage>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIFileSet.class, new TypeReference<APIList<APIFileSet>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APILabelGroup.class, new TypeReference<APIList<APILabelGroup>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APILicense.class, new TypeReference<APIList<APILicense>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APINotification.class, new TypeReference<APIList<APINotification>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIProject.class, new TypeReference<APIList<APIProject>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIProjectJobConfig.class, new TypeReference<APIList<APIProjectJobConfig>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIProjectSharing.class, new TypeReference<APIList<APIProjectSharing>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIRole.class, new TypeReference<APIList<APIRole>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIScreenshot.class, new TypeReference<APIList<APIScreenshot>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIScreenshotExtended.class, new TypeReference<APIList<APIScreenshotExtended>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIService.class, new TypeReference<APIList<APIService>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APITag.class, new TypeReference<APIList<APITag>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APITestRun.class, new TypeReference<APIList<APITestRun>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APITestRunExtended.class, new TypeReference<APIList<APITestRunExtended>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIUser.class, new TypeReference<APIList<APIUser>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIUserFile.class, new TypeReference<APIList<APIUserFile>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIUserFileTag.class, new TypeReference<APIList<APIUserFileTag>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APITestRunParameter.class, new TypeReference<APIList<APITestRunParameter>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIProjectTypeExtended.class, new TypeReference<APIList<APIProjectTypeExtended>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIDeviceTimeStepTimeReportEntry.class,
                new TypeReference<APIList<APIDeviceTimeStepTimeReportEntry>>() {
                });
        TYPE_REFERENCE_LIST_MAP.put(APIDeviceTimeCountSessionReportEntry.class,
                new TypeReference<APIList<APIDeviceTimeCountSessionReportEntry>>() {
                });
        TYPE_REFERENCE_LIST_MAP.put(APIActiveUser.class, new TypeReference<APIList<APIActiveUser>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIAdminDevice.class, new TypeReference<APIList<APIAdminDevice>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIAdminDeviceModel.class, new TypeReference<APIList<APIAdminDeviceModel>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIAdminDeviceType.class, new TypeReference<APIList<APIAdminDeviceType>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIAdminDeviceProblem.class, new TypeReference<APIList<APIAdminDeviceProblem>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIAdminDeviceSession.class, new TypeReference<APIList<APIAdminDeviceSession>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIAdminError.class, new TypeReference<APIList<APIAdminError>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIAdminTestRun.class, new TypeReference<APIList<APIAdminTestRun>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APICluster.class, new TypeReference<APIList<APICluster>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIDeviceStatus.class, new TypeReference<APIList<APIDeviceStatus>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIProperty.class, new TypeReference<APIList<APIProperty>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIAdminInteractiveDeviceSession.class,
                new TypeReference<APIList<APIAdminInteractiveDeviceSession>>() {
                });
        TYPE_REFERENCE_LIST_MAP.put(APIDeviceModelCriterion.class,
                new TypeReference<APIList<APIDeviceModelCriterion>>() {
                });
        TYPE_REFERENCE_LIST_MAP.put(APIAdminEmail.class, new TypeReference<APIList<APIAdminEmail>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIDeviceCleanupConfiguration.class,
                new TypeReference<APIList<APIDeviceCleanupConfiguration>>() {
                });
        TYPE_REFERENCE_LIST_MAP.put(APIUserIntegration.class, new TypeReference<APIList<APIUserIntegration>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIEnum.class, new TypeReference<APIList<APIEnum>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIActivity.class, new TypeReference<APIList<APIActivity>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APINotificationPlan.class, new TypeReference<APIList<APINotificationPlan>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIJiraIssue.class, new TypeReference<APIList<APIJiraIssue>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APICountryVatRate.class, new TypeReference<APIList<APICountryVatRate>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIPipelineBuild.class, new TypeReference<APIList<APIPipelineBuild>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIPipelineJob.class, new TypeReference<APIList<APIPipelineJob>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIMarketShare.class, new TypeReference<APIList<APIMarketShare>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIBuildExecutor.class, new TypeReference<APIList<APIBuildExecutor>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIAdminDeviceEvent.class, new TypeReference<APIList<APIAdminDeviceEvent>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIBasicJiraProject.class, new TypeReference<APIList<APIBasicJiraProject>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIJiraIssueType.class, new TypeReference<APIList<APIJiraIssueType>>() {
        });
        TYPE_REFERENCE_LIST_MAP.put(APIJiraPriority.class, new TypeReference<APIList<APIJiraPriority>>() {
        });
    }

    private static final Map<Class, TypeReference<?>> TYPE_REFERENCE_MAP = new HashMap<>();

    static {
        TYPE_REFERENCE_MAP.put(APIBillingPeriod.class, new TypeReference<APIBillingPeriod>() {
        });
        TYPE_REFERENCE_MAP.put(APIFramework.class, new TypeReference<APIFramework>() {
        });
        TYPE_REFERENCE_MAP.put(APIAccount.class, new TypeReference<APIAccount>() {
        });
        TYPE_REFERENCE_MAP.put(APIAdminOverview.class, new TypeReference<APIAdminOverview>() {
        });
        TYPE_REFERENCE_MAP.put(APICloudInfo.class, new TypeReference<APICloudInfo>() {
        });
        TYPE_REFERENCE_MAP.put(APICluster.class, new TypeReference<APICluster>() {
        });
        TYPE_REFERENCE_MAP.put(APIConnection.class, new TypeReference<APIConnection>() {
        });
        TYPE_REFERENCE_MAP.put(APIDevice.class, new TypeReference<APIDevice>() {
        });
        TYPE_REFERENCE_MAP.put(APIDeviceGroup.class, new TypeReference<APIDeviceGroup>() {
        });
        TYPE_REFERENCE_MAP.put(APIDeviceProperty.class, new TypeReference<APIDeviceProperty>() {
        });
        TYPE_REFERENCE_MAP.put(APIDeviceSession.class, new TypeReference<APIDeviceSession>() {
        });
        TYPE_REFERENCE_MAP.put(APIDeviceSessionStep.class, new TypeReference<APIDeviceSessionStep>() {
        });
        TYPE_REFERENCE_MAP.put(APIExceptionMessage.class, new TypeReference<APIExceptionMessage>() {
        });
        TYPE_REFERENCE_MAP.put(APIMessage.class, new TypeReference<APIMessage>() {
        });
        TYPE_REFERENCE_MAP.put(APIFileSet.class, new TypeReference<APIFileSet>() {
        });
        TYPE_REFERENCE_MAP.put(APILabelGroup.class, new TypeReference<APILabelGroup>() {
        });
        TYPE_REFERENCE_MAP.put(APINotification.class, new TypeReference<APINotification>() {
        });
        TYPE_REFERENCE_MAP.put(APIProject.class, new TypeReference<APIProject>() {
        });
        TYPE_REFERENCE_MAP.put(APIProjectJobConfig.class, new TypeReference<APIProjectJobConfig>() {
        });
        TYPE_REFERENCE_MAP.put(APIProjectSharing.class, new TypeReference<APIProjectSharing>() {
        });
        TYPE_REFERENCE_MAP.put(APIProperty.class, new TypeReference<APIProperty>() {
        });
        TYPE_REFERENCE_MAP.put(APIRole.class, new TypeReference<APIRole>() {
        });
        TYPE_REFERENCE_MAP.put(APIService.class, new TypeReference<APIService>() {
        });
        TYPE_REFERENCE_MAP.put(APIServicePaymentStatus.class, new TypeReference<APIServicePaymentStatus>() {
        });
        TYPE_REFERENCE_MAP.put(APITag.class, new TypeReference<APITag>() {
        });
        TYPE_REFERENCE_MAP.put(APITestRun.class, new TypeReference<APITestRun>() {
        });
        TYPE_REFERENCE_MAP.put(APITestRunConfig.class, new TypeReference<APITestRunConfig>() {
        });
        TYPE_REFERENCE_MAP.put(APITestRunDataAvailability.class, new TypeReference<APITestRunDataAvailability>() {
        });
        TYPE_REFERENCE_MAP.put(APITestRunExtended.class, new TypeReference<APITestRunExtended>() {
        });
        TYPE_REFERENCE_MAP.put(APIUser.class, new TypeReference<APIUser>() {
        });
        TYPE_REFERENCE_MAP.put(APIUserDeviceTime.class, new TypeReference<APIUserDeviceTime>() {
        });
        TYPE_REFERENCE_MAP.put(APIBasicDeviceTime.class, new TypeReference<APIBasicDeviceTime>() {
        });
        TYPE_REFERENCE_MAP.put(APIUserFile.class, new TypeReference<APIUserFile>() {
        });
        TYPE_REFERENCE_MAP.put(APIAccountService.class, new TypeReference<APIAccountService>() {
        });
        TYPE_REFERENCE_MAP.put(APILicense.class, new TypeReference<APILicense>() {
        });
        TYPE_REFERENCE_MAP.put(Properties.class, new TypeReference<Properties>() {
        });
        TYPE_REFERENCE_MAP.put(String.class, new TypeReference<String>() {
        });
        TYPE_REFERENCE_MAP.put(APITestRunParameter.class, new TypeReference<APITestRunParameter>() {
        });
        TYPE_REFERENCE_MAP.put(APIDeviceModelCriterion.class, new TypeReference<APIDeviceModelCriterion>() {
        });
        TYPE_REFERENCE_MAP.put(APIAdminDeviceType.class, new TypeReference<APIAdminDeviceType>() {
        });
        TYPE_REFERENCE_MAP.put(APIAdminEmail.class, new TypeReference<APIAdminEmail>() {
        });
        TYPE_REFERENCE_MAP.put(APIAdminTestRun.class, new TypeReference<APIAdminTestRun>() {
        });
        TYPE_REFERENCE_MAP.put(APIS3Url.class, new TypeReference<APIS3Url>() {
        });
        TYPE_REFERENCE_MAP.put(APIDeviceCleanupConfiguration.class,
                new TypeReference<APIDeviceCleanupConfiguration>() {
                });
        TYPE_REFERENCE_MAP.put(APIAdminDevice.class, new TypeReference<APIAdminDevice>() {
        });
        TYPE_REFERENCE_MAP.put(APIUserIntegration.class, new TypeReference<APIUserIntegration>() {
        });
        TYPE_REFERENCE_MAP.put(APIAdminDeviceSessionStatistics.class,
                new TypeReference<APIAdminDeviceSessionStatistics>() {
                });
        TYPE_REFERENCE_MAP.put(APIDevicePicker.class, new TypeReference<APIDevicePicker>() {
        });
        TYPE_REFERENCE_MAP.put(APINotificationPlan.class, new TypeReference<APINotificationPlan>() {
        });
        TYPE_REFERENCE_MAP.put(APIJiraServerInfo.class, new TypeReference<APIJiraServerInfo>() {
        });
        TYPE_REFERENCE_MAP.put(APIJiraProject.class, new TypeReference<APIJiraProject>() {
        });
        TYPE_REFERENCE_MAP.put(APIJiraIssue.class, new TypeReference<APIJiraIssue>() {
        });
        TYPE_REFERENCE_MAP.put(APICountryVatRate.class, new TypeReference<APICountryVatRate>() {
        });
        TYPE_REFERENCE_MAP.put(APIPipelineBuild.class, new TypeReference<APIPipelineBuild>() {
        });
        TYPE_REFERENCE_MAP.put(APIPipelineJob.class, new TypeReference<APIPipelineJob>() {
        });
        TYPE_REFERENCE_MAP.put(APIUserStatistics.class, new TypeReference<APIUserStatistics>() {
        });
        TYPE_REFERENCE_MAP.put(APIMarketShare.class, new TypeReference<APIMarketShare>() {
        });
        TYPE_REFERENCE_MAP.put(APIBuildExecutor.class, new TypeReference<APIBuildExecutor>() {
        });
        TYPE_REFERENCE_MAP.put(APIAccountServicePayment.class, new TypeReference<APIAccountServicePayment>() {
        });
    }

    public static <T> TypeReference<T> getTypeRef(Class<T> clazz) {
        return (TypeReference<T>) TYPE_REFERENCE_MAP.get(clazz);
    }

    public static <T extends APIEntity> TypeReference<APIList<T>> getListTypeRef(Class<T> clazz) {
        return (TypeReference<APIList<T>>) TYPE_REFERENCE_LIST_MAP.get(clazz);
    }

    public static <T> TypeReference<APIArray<T>> getArrayTypeRef(Class<T> clazz) {
        return (TypeReference<APIArray<T>>) TYPE_REFERENCE_ARRAY_MAP.get(clazz);
    }

    public static TypeReference<Map<String, String>> getMapTypeReference() {
        return new TypeReference<Map<String, String>>() {
        };
    }
}
