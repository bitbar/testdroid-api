package com.testdroid.api;

import com.testdroid.api.model.APIProject;
import com.testdroid.api.model.APIUser;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat(project.getName()).isEqualTo(projectName);
    }

    @ParameterizedTest
    @ArgumentsSource(BaseAPIClientTest.APIClientProvider.class)
    void updateProjectTest(APIClient apiClient) throws APIException {
        APIProject project = apiClient.me().createProject(generateUnique("testProject"));
        String updatedProjectName = generateUnique("testProject");
        project.setName(updatedProjectName);
        project.setDescription("Description of testProject");
        project.update();
        assertThat(project.getName()).isEqualTo(updatedProjectName);
        assertThat(project.getDescription()).isEqualTo("Description of testProject");
    }

    @ParameterizedTest
    @ArgumentsSource(BaseAPIClientTest.APIClientProvider.class)
    void deleteProjectTest(APIClient apiClient) throws APIException {
        APIUser me = apiClient.me();
        APIProject project = me.createProject(generateUnique("testProject"));
        project.delete();
        assertThatThrownBy(() -> me.getProject(project.getId())).isInstanceOf(APIException.class)
                .hasMessageContaining(String.format("Project with id %d does not exist", project.getId()));
    }
}
