package com.testdroid.api;

import com.testdroid.api.model.APIProject;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
class APIProjectAPIClientTest extends BaseAPIClientTest {

    @ParameterizedTest
    @ArgumentsSource(BaseAPIClientTest.APIClientProvider.class)
    void createProjectTest(APIClient apiClient) throws APIException {
        String projectName = generateUnique("testProject");
        APIProject project = apiClient.me().createProject(projectName);
        assertThat(project.getName(), is(projectName));
    }

    @ParameterizedTest
    @ArgumentsSource(BaseAPIClientTest.APIClientProvider.class)
    void updateProjectTest(APIClient apiClient) throws APIException {
        APIProject project = apiClient.me().createProject(generateUnique("testProject"));
        String updatedProjectName = generateUnique("testProject");
        project.setName(updatedProjectName);
        project.setDescription("Description of testProject");
        project.update();
        assertThat(project.getName(), is(updatedProjectName));
        assertThat(project.getDescription(), is("Description of testProject"));
    }

    @ParameterizedTest
    @ArgumentsSource(BaseAPIClientTest.APIClientProvider.class)
    void deleteProjectTest(APIClient apiClient) throws APIException {
        APIProject project = apiClient.me().createProject(generateUnique("testProject"));
        project.delete();
    }
}
