package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.FilterEntry;
import com.testdroid.api.model.*;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.testdroid.api.dto.MappingKey.*;
import static com.testdroid.api.dto.Operand.EQ;
import static com.testdroid.api.filter.FilterEntry.trueFilterEntry;
import static com.testdroid.api.model.APIDevice.OsType.ANDROID;
import static com.testdroid.api.model.APIFileConfig.Action.INSTALL;
import static com.testdroid.api.model.APIFileConfig.Action.RUN_TEST;
import static com.testdroid.api.model.APITestRun.State.WAITING;
import static com.testdroid.api.util.BitbarUtils.loadFile;
import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Integer.MAX_VALUE;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
class APIUserAPIClientTest extends BaseAPIClientTest {

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void getDevicesTest(APIClient apiClient) throws APIException {
        APIList<APIDevice> allDevices = apiClient.getDevices(new Context<>(APIDevice.class)).getEntity();
        assertThat(allDevices.isEmpty(), is(FALSE));

        APIList<APIDevice> androidDevices = apiClient.getDevices(new Context<>(APIDevice.class)
                .addFilter(new FilterEntry(OS_TYPE, EQ, ANDROID.name()))).getEntity();
        assertThat(androidDevices.isEmpty(), is(FALSE));
        assertThat(androidDevices.getData().stream().allMatch(d -> d.getOsType().equals(ANDROID)), is(TRUE));
        assertThat(androidDevices.getTotal(), is(lessThanOrEqualTo(allDevices.getTotal())));

        APIList<APIDevice> samsungDevices = apiClient.getDevices(new Context<>(APIDevice.class)
                .addFilter(new FilterEntry(DISPLAY_NAME, EQ, "%Samsung%"))).getEntity();
        assertThat(samsungDevices.getTotal(), is(lessThanOrEqualTo(androidDevices.getTotal())));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void getLabelGroups(APIClient apiClient) throws APIException {
        APIList<APILabelGroup> labelGroups = apiClient.getLabelGroups(new Context<>(APILabelGroup.class)
                .addFilter(new FilterEntry(DISPLAY_NAME, EQ, "Device groups"))).getEntity();
        assertThat(labelGroups.getTotal(), is(1));
        APILabelGroup deviceGroupLabelGroup = labelGroups.getData().stream().findFirst().orElseThrow(APIException::new);
        assertThat(deviceGroupLabelGroup.getDisplayName(), is("Device groups"));
        APIList<APIDeviceProperty> apiDeviceProperties = deviceGroupLabelGroup.getDevicePropertiesResource(new
                Context<>(APIDeviceProperty.class).setLimit(0)).getEntity();
        assertThat(apiDeviceProperties.getTotal(), lessThanOrEqualTo(4));
        assertThat(Arrays.asList("Android devices", "iOS devices", "Trial devices", "Desktops"),
                hasItems(apiDeviceProperties.getData().stream().map(APIDeviceProperty::getDisplayName)
                        .toArray(String[]::new)));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void uploadFileTest(APIClient apiClient) throws APIException {
        APIUserFile apiUserFile = apiClient.me().uploadFile(loadFile(APP_PATH));
        assertThat(apiUserFile.getName(), is("BitbarSampleApp.apk"));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void getAvailableFrameworksTest(APIClient apiClient) throws APIException {
        Context<APIFramework> context = new Context<>(APIFramework.class, 0, MAX_VALUE, EMPTY, EMPTY);
        context.addFilter(new FilterEntry(OS_TYPE, EQ, ANDROID.name()));
        context.addFilter(trueFilterEntry(FOR_PROJECTS));
        context.addFilter(trueFilterEntry(CAN_RUN_FROM_UI));
        APIList<APIFramework> availableFrameworks = apiClient.me().getAvailableFrameworksResource(context)
                .getEntity();
        assertThat(availableFrameworks.getData().stream().allMatch(f -> f.getForProjects() && f.getCanRunFromUI() && f
                .getOsType() == ANDROID), is(TRUE));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void startTestRunTest(APIClient apiClient) throws APIException, InterruptedException {
        APIUser me = apiClient.me();
        APITestRunConfig config = new APITestRunConfig();
        config.setProjectName(generateUnique("testProject"));
        APIFramework defaultApiFramework = getApiFramework(apiClient, "Android Instrumentation");
        config.setOsType(defaultApiFramework.getOsType());
        config.setFrameworkId(defaultApiFramework.getId());
        APIUserFile apkFile = me.uploadFile(loadFile(APP_PATH));
        APIFileConfig apkFileConfig = new APIFileConfig(apkFile.getId(), INSTALL);
        APIUserFile testFile = me.uploadFile(loadFile(TEST_PATH));
        APIFileConfig testFileConfig = new APIFileConfig(testFile.getId(), RUN_TEST);
        config.setFiles(Arrays.asList(apkFileConfig, testFileConfig));
        APIUserFile.waitForVirusScans(apkFile, testFile);
        me.validateTestRunConfig(config);
        APITestRun apiTestRun = me.startTestRun(config);
        assertThat(apiTestRun.getState(), is(oneOf(RUNNING, WAITING)));
        apiTestRun.delete();
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void addTagTest(APIClient apiClient) throws APIException, InterruptedException {
        APIUser me = apiClient.me();
        APITestRunConfig config = new APITestRunConfig();
        config.setProjectName(generateUnique("testProject"));
        APIFramework defaultApiFramework = getApiFramework(apiClient, "Android Instrumentation");
        config.setOsType(defaultApiFramework.getOsType());
        config.setFrameworkId(defaultApiFramework.getId());
        APIUserFile apkFile = me.uploadFile(loadFile(APP_PATH));
        APIFileConfig apkFileConfig = new APIFileConfig(apkFile.getId(), INSTALL);
        APIUserFile testFile = me.uploadFile(loadFile(TEST_PATH));
        APIFileConfig testFileConfig = new APIFileConfig(testFile.getId(), RUN_TEST);
        config.setFiles(Arrays.asList(apkFileConfig, testFileConfig));
        APIUserFile.waitForVirusScans(apkFile, testFile);
        me.validateTestRunConfig(config);
        APITestRun apiTestRun = me.startTestRun(config);
        assertThat(apiTestRun.getState(), is(oneOf(RUNNING, WAITING)));
        apiTestRun.abort();
        String tag = "aborted";
        APITag apiTag = apiTestRun.addTag(tag);
        apiTestRun.refresh();
        assertThat(apiTestRun.getTagsResource().getTotal(), is(1));
        assertThat(apiTestRun.getTagsResource().getEntity().get(0).getName(), is(tag));
        apiTag.delete();
        apiTestRun.refresh();
        assertThat(apiTestRun.getTagsResource().getTotal(), is(0));
        apiTestRun.delete();
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void requestScreenshotsZip(APIClient apiClient) throws APIException, InterruptedException, IOException {
        APIUser me = apiClient.me();
        APITestRunConfig config = new APITestRunConfig();
        config.setProjectName(generateUnique("testProject"));
        APIFramework defaultApiFramework = getApiFramework(apiClient, "Android Instrumentation");
        config.setOsType(defaultApiFramework.getOsType());
        config.setFrameworkId(defaultApiFramework.getId());
        APIUserFile apkFile = me.uploadFile(loadFile(APP_PATH));
        APIFileConfig apkFileConfig = new APIFileConfig(apkFile.getId(), INSTALL);
        APIUserFile testFile = me.uploadFile(loadFile(TEST_PATH));
        APIFileConfig testFileConfig = new APIFileConfig(testFile.getId(), RUN_TEST);
        config.setFiles(Arrays.asList(apkFileConfig, testFileConfig));
        APIUserFile.waitForVirusScans(apkFile, testFile);
        me.validateTestRunConfig(config);
        APITestRun apiTestRun = me.startTestRun(config);
        assertThat(apiTestRun.getState(), is(oneOf(RUNNING, WAITING)));
        apiTestRun.requestScreenshotsZip();
        APIUserFile file = apiTestRun.getScreenshotsZip();
        while (file.getState() != APIUserFile.State.READY) {
            try {
                TimeUnit.SECONDS.sleep(3);
                file.refresh();
            } catch (InterruptedException ignore) {
            }
        }
        try (InputStream inputStream = file.getFile()) {
            FileUtils.copyInputStreamToFile(inputStream, Files.createTempFile(null, null).toFile());
        }
        apiTestRun.delete();
    }

}
