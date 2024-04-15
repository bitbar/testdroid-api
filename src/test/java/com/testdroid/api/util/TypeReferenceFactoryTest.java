package com.testdroid.api.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.testdroid.api.*;
import com.testdroid.api.model.*;
import com.testdroid.api.model.capabilities.APIDesktopBrowserCapabilities;
import com.testdroid.api.model.devicetime.APIBasicDeviceTime;
import com.testdroid.api.model.notification.APINotification;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

import static com.testdroid.cloud.test.categories.TestTags.UNIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
@Tag(UNIT)
class TypeReferenceFactoryTest {

    static class ExplicitTypeProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            Stream<Class<?>> stream = Stream.of(APIAccessGroup.class,
                    APIAccount.class,
                    APIAccountConcurrencyStatus.class,
                    APIAccountConcurrencyStatusMap.class,
                    APIAccountPreference.class,
                    APIAccountService.class,
                    APIAccountServicePayment.class,
                    APIAdminDevice.class,
                    APIAdminDeviceSessionStatistics.class,
                    APIAdminDeviceType.class,
                    APIAdminEmail.class,
                    APIAdministrator.class,
                    APIAdminOverview.class,
                    APIAdminTestRun.class,
                    APIBasicDeviceTime.class,
                    APIBillingPeriod.class,
                    APIBillingPeriodUsage.class,
                    APIBrowser.class,
                    APICloudInfo.class,
                    APICluster.class,
                    APIConnection.class,
                    APIDesktopBrowserCapabilities.class,
                    APIDevice.class,
                    APIDeviceCleanupConfiguration.class,
                    APIDeviceGroup.class,
                    APIDeviceModelCriterion.class,
                    APIDeviceModelPool.class,
                    APIDevicePicker.class,
                    APIDeviceProperty.class,
                    APIDeviceSession.class,
                    APIDeviceSessionStep.class,
                    APIExceptionMessage.class,
                    APIFramework.class,
                    APIHealthCheck.class,
                    APILabelGroup.class,
                    APILicense.class,
                    APIMaintenance.class,
                    APIMessage.class,
                    APINotification.class,
                    APIProject.class,
                    APIProjectJobConfig.class,
                    APIProperty.class,
                    APIRole.class,
                    APIS3Url.class,
                    APIService.class,
                    APIServicePaymentStatus.class,
                    APISharedResource.class,
                    APISignalingSession.class,
                    APITestRun.class,
                    APITestRunConfig.class,
                    APITestRunDataAvailability.class,
                    APITestRunParameter.class,
                    APIUser.class,
                    APIUserDeviceTimeSummary.class,
                    APIUserFile.class,
                    APIUserFileProperty.class,
                    APIUserPreference.class,
                    APIUserStatistics.class,
                    Properties.class,
                    String.class,
                    APIVisualTestAccess.class);
            return stream.distinct().map(Arguments::of);
        }
    }

    static class APIEntitySubTypeProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            Reflections reflections = new Reflections(APIEntity.class.getPackage().getName());
            Set<Class<? extends APIEntity>> classes = reflections.getSubTypesOf(APIEntity.class);
            return classes.stream()
                    .filter(c -> c != APIList.class)
                    .filter(c -> c != APISimpleList.class)
                    .map(Arguments::of);
        }
    }

    @Test
    void testConstructor() {
        assertThrows(IllegalAccessException.class, () -> TypeReferenceFactory.class.getDeclaredConstructor()
                .newInstance());
    }

    @ArgumentsSource(ExplicitTypeProvider.class)
    @ParameterizedTest
    void testGetTypeRef(Class<?> clazz) {
        TypeReference<?> result = TypeReferenceFactory.getTypeRef(clazz);
        assertThat(result).isNotNull();
        assertThat(result.getType().getTypeName()).isEqualTo(clazz.getName());
    }

    @ArgumentsSource(APIEntitySubTypeProvider.class)
    @ParameterizedTest
    <T extends APIEntity> void testGetListTypeRef(Class<T> clazz) {
        TypeReference<?> result = TypeReferenceFactory.getListTypeRef(clazz);
        assertThat(result).withFailMessage(clazz.getSimpleName() + " not in the map").isNotNull();
        assertThat(result.getType().getTypeName())
                .isEqualTo(String.format("%s<%s>", APIList.class.getName(), clazz.getName()));
    }

    @ArgumentsSource(APIEntitySubTypeProvider.class)
    @ParameterizedTest
    <T extends APIEntity> void testGetSimpleListTypeRef(Class<T> clazz) {
        TypeReference<?> result = TypeReferenceFactory.getSimpleListTypeRef(clazz);
        if (clazz == APIBrokerHub.class) {
            assertThat(result).withFailMessage(clazz.getSimpleName() + " not in the map").isNotNull();
            assertThat(result.getType().getTypeName())
                    .isEqualTo(String.format("%s<%s>", APISimpleList.class.getName(), clazz.getName()));
        } else {
            assertThat(result).isNull();
        }
    }

    @Test
    void testGetMapTypeReference() {
        TypeReference<?> result = TypeReferenceFactory.getMapTypeReference();
        assertThat(result).isNotNull();
        assertThat(result.getType().getTypeName()).isEqualTo(
                String.format("%1$s<%2$s, %2$s>", Map.class.getName(), String.class.getName()));
    }
}
