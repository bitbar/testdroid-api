package com.testdroid.api;

import com.testdroid.api.model.APIProject;
import com.testdroid.api.model.APIProjectSharing;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.testdroid.api.model.APIProject.Type.ANDROID;
import static com.testdroid.cloud.test.categories.TestTags.API_CLIENT;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@Tag(API_CLIENT)
public class APIProjectAPIClientTest extends APIClientTest{

    @ParameterizedTest
    @ArgumentsSource(APIClientTest.APIClientProvider.class)
    void createProjectTest(AbstractAPIClient apiKeyClient) throws APIException {
        String projectName = generateUnique("testProject");
        APIProject project = apiKeyClient.me().createProject(ANDROID, projectName);
        assertThat(project.getName(), is(projectName));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientTest.APIClientProvider.class)
    void updateProjectTest(AbstractAPIClient apiKeyClient) throws APIException {
        APIProject project = apiKeyClient.me().createProject(ANDROID, generateUnique("testProject"));
        String updatedProjectName = generateUnique("testProject");
        project.setName(updatedProjectName);
        project.setDescription("Description of testProject");
        project.update();
        assertThat(project.getName(), is(updatedProjectName));
        assertThat(project.getDescription(), is("Description of testProject"));
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientTest.APIClientProvider.class)
    void deleteProjectTest(AbstractAPIClient apiKeyClient) throws APIException {
        APIProject project = apiKeyClient.me().createProject(ANDROID, generateUnique("testProject"));
        project.delete();
    }

    @ParameterizedTest
    @ArgumentsSource(APIClientTest.APIClientProvider.class)
    void shareProjectTest(AbstractAPIClient apiKeyClient) throws APIException {
        APIProject project = apiKeyClient.me().createProject(ANDROID, generateUnique("testProject"));
        APIList<APIProjectSharing> projectSharings = project.getProjectSharings().getEntity();
        assertThat(projectSharings.isEmpty(), is(TRUE));
        String shareTo = ADMIN_API_CLIENT.me().getEmail();
        project.share(shareTo);
        projectSharings = project.getProjectSharings().getEntity();
        assertThat(projectSharings.getTotal(), is(1));
        APIProjectSharing projectSharing = projectSharings.get(0);
        assertThat(projectSharing.getUserEmail(), is(shareTo));
        projectSharing.deleteResource(projectSharing.selfURI);
        projectSharings = project.getProjectSharings().getEntity();
        assertThat(projectSharings.isEmpty(), is(TRUE));
    }

}
