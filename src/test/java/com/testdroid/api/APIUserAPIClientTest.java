package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.FilterEntry;
import com.testdroid.api.model.*;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.testdroid.api.dto.MappingKey.*;
import static com.testdroid.api.dto.Operand.EQ;
import static com.testdroid.api.filter.FilterEntry.trueFilterEntry;
import static com.testdroid.api.model.APIAccessGroup.Scope.USER;
import static com.testdroid.api.model.APIDevice.OsType.ANDROID;
import static com.testdroid.api.model.APIFileConfig.Action.INSTALL;
import static com.testdroid.api.model.APIFileConfig.Action.RUN_TEST;
import static com.testdroid.api.model.APITestRun.State.WAITING;
import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static java.lang.Integer.MAX_VALUE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.singletonMap;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
class APIUserAPIClientTest extends BaseAPIClientTest {

    private static final String DEFAULT_FRAMEWORK_NAME_LIKE = "%Android Instrumentation%";

    private static final long VIRUS_SCAN_WAIT_TIME = 10 * 60 * 1000L;

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void getDevicesTest(APIClient apiClient) throws APIException {
        APIList<APIDevice> allDevices = apiClient.getDevices(new Context<>(APIDevice.class)).getEntity();
        assertThat(allDevices.isEmpty()).isFalse();

        APIList<APIDevice> androidDevices = apiClient.getDevices(new Context<>(APIDevice.class)
                .addFilter(new FilterEntry(OS_TYPE, EQ, ANDROID.name()))).getEntity();
        assertThat(androidDevices.isEmpty()).isFalse();
        assertThat(androidDevices.getData().stream().allMatch(d -> d.getOsType().equals(ANDROID))).isTrue();
        assertThat(androidDevices.getTotal()).isLessThanOrEqualTo(allDevices.getTotal());

        APIList<APIDevice> samsungDevices = apiClient.getDevices(new Context<>(APIDevice.class)
                .addFilter(new FilterEntry(DISPLAY_NAME, EQ, "%Samsung%"))).getEntity();
        assertThat(samsungDevices.getTotal()).isLessThanOrEqualTo(androidDevices.getTotal());
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void getLabelGroups(APIClient apiClient) throws APIException {
        APIList<APILabelGroup> labelGroups = apiClient.getLabelGroups(new Context<>(APILabelGroup.class)
                .addFilter(new FilterEntry(DISPLAY_NAME, EQ, "Device groups"))).getEntity();
        assertThat(labelGroups.getTotal()).isEqualTo(1);
        APILabelGroup deviceGroupLabelGroup = labelGroups.getData().stream().findFirst().orElseThrow(APIException::new);
        assertThat(deviceGroupLabelGroup.getDisplayName()).isEqualTo("Device groups");
        APIList<APIDeviceProperty> apiDeviceProperties = deviceGroupLabelGroup.getDevicePropertiesResource(new
                Context<>(APIDeviceProperty.class).setLimit(0)).getEntity();
        assertThat(apiDeviceProperties.getTotal()).isLessThanOrEqualTo(4);
        assertThat(apiDeviceProperties.getData()).extracting(APIDeviceProperty::getDisplayName)
                .containsExactly("Android devices", "iOS devices", "Trial devices", "Desktops");
    }

    @Tag("SDCB-3881")
    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void getFilesDefaultOrderTest(APIClient apiClient) throws APIException, IOException {
        APIUser me = apiClient.me();
        APIUserFile file1 = me.uploadFile(File.createTempFile("getFilesDefaultOrderTest", ".tmp"));
        APIUserFile file2 = me.uploadFile(File.createTempFile("getFilesDefaultOrderTest", ".tmp"));
        APIList<APIUserFile> list = me.getListResource("/me/files", new Context<>(APIUserFile.class, 0, 2, EMPTY,
                EMPTY).addFilter(FilterEntry.create(DIRECTION, EQ, APIUserFile.Direction.INPUT.name()))).getEntity();
        assertThat(list.getData().get(0).getId()).isGreaterThan(list.getData().get(1).getId());
        file1.delete();
        file2.delete();
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void uploadFileTest(APIClient apiClient) throws APIException {
        APIUserFile apiUserFile = apiClient.me().uploadFile(loadFile(APP_PATH));
        assertThat(apiUserFile.getName()).isEqualTo("BitbarSampleApp.apk");
        assertThat(apiUserFile.isDuplicate()).isFalse();
        Response httpResponse;
        httpResponse = apiClient.getHttpResponse("/me/files/" + apiUserFile.id + "/file", null);
        assertThat(httpResponse.isSuccessful()).isTrue();
        //Verify file duplication
        apiUserFile = apiClient.me().uploadFile(loadFile(APP_PATH));
        assertThat(apiUserFile.getName()).isEqualTo("BitbarSampleApp.apk");
        assertThat(apiUserFile.isDuplicate()).isTrue();
        httpResponse = apiClient.getHttpResponse("/me/files/" + apiUserFile.id + "/file", null);
        assertThat(httpResponse.isSuccessful()).isTrue();
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void downloadFileTest(APIClient apiClient) throws APIException, IOException {
        File source = Files.createTempFile(null, ".txt").toFile();
        FileUtils.writeStringToFile(source, "test", UTF_8);
        APIUserFile apiUserFile = apiClient.me().uploadFile(source);
        File destination = Files.createTempFile(null, null).toFile();
        try (InputStream inputStream = apiUserFile.getFile()) {
            FileUtils.copyInputStreamToFile(inputStream, destination);
        }
        assertThat(destination).hasSize(apiUserFile.getSize());
        apiUserFile.delete();
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
                .getOsType() == ANDROID)).isTrue();
    }

    @Tag("SDCB-3876")
    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void sdcb3876Test(APIClient apiClient) throws APIException {
        APIUser me = apiClient.me();
        APITestRunConfig config = new APITestRunConfig();
        config.setProjectName(generateUnique("testProject"));
        APIFramework defaultApiFramework = getApiFramework(apiClient, DEFAULT_FRAMEWORK_NAME_LIKE);
        config.setOsType(defaultApiFramework.getOsType());
        config.setFrameworkId(defaultApiFramework.getId());
        //simulate that client set files to null
        config.setFiles(null);
        APITestRunConfig validatedConfig = me.validateTestRunConfig(config);
        assertThat(validatedConfig.getFiles()).isNotNull();
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void startTestRunTest(APIClient apiClient) throws APIException, InterruptedException {
        APIUser me = apiClient.me();
        APITestRunConfig config = new APITestRunConfig();
        config.setProjectName(generateUnique("testProject"));
        APIFramework defaultApiFramework = getApiFramework(apiClient, DEFAULT_FRAMEWORK_NAME_LIKE);
        config.setOsType(defaultApiFramework.getOsType());
        config.setFrameworkId(defaultApiFramework.getId());
        APIUserFile apkFile = me.uploadFile(loadFile(APP_PATH));
        APIFileConfig apkFileConfig = new APIFileConfig(apkFile.getId(), INSTALL);
        APIUserFile testFile = me.uploadFile(loadFile(TEST_PATH));
        APIFileConfig testFileConfig = new APIFileConfig(testFile.getId(), RUN_TEST);
        config.setFiles(Arrays.asList(apkFileConfig, testFileConfig));
        APIUserFile.waitForVirusScans(VIRUS_SCAN_WAIT_TIME, apkFile, testFile);
        me.validateTestRunConfig(config);
        APITestRun apiTestRun = me.startTestRun(config);
        assertThat(apiTestRun.getState()).isIn(RUNNING, WAITING);

        apiTestRun.delete();
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void requestScreenshotsZip(APIClient apiClient) throws APIException, InterruptedException, IOException {
        APIUser me = apiClient.me();
        APITestRunConfig config = new APITestRunConfig();
        config.setProjectName(generateUnique("testProject"));
        APIFramework defaultApiFramework = getApiFramework(apiClient, DEFAULT_FRAMEWORK_NAME_LIKE);
        config.setOsType(defaultApiFramework.getOsType());
        config.setFrameworkId(defaultApiFramework.getId());
        APIUserFile apkFile = me.uploadFile(loadFile(APP_PATH));
        APIFileConfig apkFileConfig = new APIFileConfig(apkFile.getId(), INSTALL);
        APIUserFile testFile = me.uploadFile(loadFile(TEST_PATH));
        APIFileConfig testFileConfig = new APIFileConfig(testFile.getId(), RUN_TEST);
        config.setFiles(Arrays.asList(apkFileConfig, testFileConfig));
        APIUserFile.waitForVirusScans(VIRUS_SCAN_WAIT_TIME, apkFile, testFile);
        me.validateTestRunConfig(config);
        APITestRun apiTestRun = me.startTestRun(config);
        assertThat(apiTestRun.getState()).isIn(RUNNING, WAITING);
        apiTestRun.requestScreenshotsZip();
        APIUserFile file = apiTestRun.getScreenshotsZip();
        await().atMost(3, TimeUnit.MINUTES).pollInterval(3, TimeUnit.SECONDS)
                .until(() -> {
                    file.refresh();
                    return file.getState() == APIUserFile.State.READY;
                });
        try (InputStream inputStream = file.getFile()) {
            FileUtils.copyInputStreamToFile(inputStream, Files.createTempFile(null, null).toFile());
        }
        apiTestRun.delete();
    }

    @Tag("SDCC-2690")
    @ParameterizedTest
    @ArgumentsSource(APIClientProvider.class)
    void projectSharingTest(APIClient apiClient) throws APIException, InterruptedException {
        APIUser user1 = apiClient.me();
        String projectName = generateUnique("sharedProject");
        APIProject project = user1.createProject(projectName);
        Map<String, Object> data = new HashMap<>();
        data.put(NAME, "sharedProjectAccessGroup");
        data.put(SCOPE, USER);
        APIAccessGroup accessGroup = user1.postResource("/me/access-groups", data, APIAccessGroup.class);

        APIUser apiUser2 = create(ADMIN_API_CLIENT);
        TO_BE_DELETED.add(apiUser2);
        user1.postResource(accessGroup.getSelfURI() + "/users", singletonMap(EMAIL, apiUser2.getEmail()),
                APIAccessGroup.class);
        user1.postResource(project.getSelfURI() + "/share", singletonMap(ACCESS_GROUP_ID, accessGroup.getId()),
                APIList.class);

        APIClient apiClient2 = new APIKeyClient(CLOUD_URL, apiUser2.getApiKey());
        apiUser2 = apiClient2.me();

        APITestRunConfig config = new APITestRunConfig();
        config.setProjectId(project.getId());
        APIFramework defaultApiFramework = getApiFramework(apiClient, DEFAULT_FRAMEWORK_NAME_LIKE);
        config.setOsType(defaultApiFramework.getOsType());
        config.setFrameworkId(defaultApiFramework.getId());
        APIUserFile apkFile = apiUser2.uploadFile(loadFile(APP_PATH));
        APIFileConfig apkFileConfig = new APIFileConfig(apkFile.getId(), INSTALL);
        APIUserFile testFile = apiUser2.uploadFile(loadFile(TEST_PATH));
        APIFileConfig testFileConfig = new APIFileConfig(testFile.getId(), RUN_TEST);
        config.setFiles(Arrays.asList(apkFileConfig, testFileConfig));
        APIUserFile.waitForVirusScans(VIRUS_SCAN_WAIT_TIME, apkFile, testFile);
        apiUser2.validateTestRunConfig(config);
        APITestRun apiTestRun = apiUser2.startTestRun(config);
        apiTestRun.abort();
        // sharing files used for tests is not immediate action, it has to be propagated.
        await().atMost(1, TimeUnit.MINUTES).pollInterval(3, TimeUnit.SECONDS)
                .until(() -> {
                    APIUserFile file = user1.getFile(apkFile.getId());
                    return Objects.nonNull(file);
                });
    }

}
