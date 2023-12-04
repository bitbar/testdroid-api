package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.model.APIDeviceProperty;
import com.testdroid.api.model.APILabelGroup;
import com.testdroid.api.model.APIUser;
import okhttp3.Response;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
public interface APIClient {

    /**
     * Sets establish connection timeout for APIClient requests.
     *
     * @param timeout timeout is ms. Value <i>0</i> means infinite timeout.
     */
    void setConnectTimeout(int timeout);

    /**
     * Sets timeout for read data from established connection for APIClient requests.
     *
     * @param timeout timeout is ms. Value <i>0</i> means infinite timeout.
     */
    void setRequestTimeout(int timeout);

    /**
     * Fetch and return me - the authenticated user.
     *
     * @throws APIException on invalid username, password or API error.
     */
    APIUser me() throws APIException;

    /**
     * Calls GET request to API
     *
     * @param <T>  expected result class to be parsed from response
     * @param uri  context URI of the resource (without <code>/api/v2</code> prefix
     * @param type expected result class to be parsed from response
     * @return object defined as <code>T</code> if succesfully returned and parsed
     * @throws APIException on any problem related to API communication
     */
    <T extends APIEntity> T get(String uri, Class<T> type) throws APIException;

    <T extends APIEntity> APIList<T> get(String uri, Context<T> context) throws APIException;

    /**
     * Calls GET request to API. Used when result expected as stream.
     *
     * @param uri context URI of the resource (without <code>/api/v2</code> prefix
     * @return stream with response
     * @throws APIException on any problem related to API communication
     */
    InputStream get(String uri) throws APIException;

    /**
     * Calls POST request to API
     *
     * @param <T>  expected result class to be parsed from response
     * @param uri  context URI of the resource (without <code>/api/v2</code> prefix
     * @param body body - usually url encoded, to POST to API
     * @param type expected result class to be parsed from response
     * @return object defined as <code>T</code> if succesfully returned and parsed
     * @throws APIException on any problem related to API communication
     */
    <T extends APIEntity> T post(String uri, Object body, Class<T> type) throws APIException;

    /**
     * Calls POST request to API
     *
     * @param <T>               expected result class to be parsed from response
     * @param uri               context URI of the resource (without <code>/api/v2</code> prefix)
     * @param contentType       content type of uploaded file
     * @param file              file to be uploaded with that request
     * @param fileExtraParams   extra parameters for form-data describing the file
     * @param type              expected result class to be parsed from response
     * @return object defined as <code>T</code> if successfully returned and parsed
     * @throws APIException on any problem related to API communication
     */
    <T extends APIEntity> T postFile(
            String uri, String contentType, File file, Map<String, String> fileExtraParams, Class<T> type)
            throws APIException;

    /**
     * Calls DELETE request to API
     *
     * @param uri context URI of the resource (without <code>/api/v2</code> prefix
     * @throws APIException on any problem related to API communication
     */
    void delete(String uri) throws APIException;

    /**
     * return resource for accessing list of devices in Cloud using provided filters
     *
     * @return list resource for accessing all devices matching selected filters, if no filter used returns all devices
     */
    APIListResource<APIDevice> getDevices();

    APIListResource<APIDevice> getDevices(Context<APIDevice> context);

    APIListResource<APILabelGroup> getLabelGroups();

    APIListResource<APILabelGroup> getLabelGroups(Context<APILabelGroup> context);

    Optional<APIDeviceProperty> findDevicePropertyInLabelGroup(String groupName, String labelName) throws APIException;

    Response getHttpResponse(String uri, Context<?> context) throws APIException;

}
