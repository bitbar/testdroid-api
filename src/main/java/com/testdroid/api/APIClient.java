package com.testdroid.api;

import com.testdroid.api.model.APIUser;

/**
 *
 * @author kajdus
 */
public interface APIClient {
    /**
     * Fetch and return me - the authenticated user.
     * @throws APIException on invalid username, password or API error.
     */
    public APIUser me() throws APIException;

    /**
     * Registers and fetches user from given email.
     * @throws APIException on invalid email or when user already exists.
     */
    public APIUser register(String email) throws APIException;
    
    /**
     * Calls GET request to API
     * @param <T> expected result class to be parsed from response
     * @param uri context URI of the resource (without <code>/api/v2</code> prefix
     * @param type expected result class to be parsed from response
     * @return object defined as <code>T</code> if succesfully returned and parsed
     * @throws APIException on any problem related to API communication
     */
    public <T extends APIEntity> T get(String uri, Class<T> type) throws APIException;
    
    /**
     * Calls POST request to API
     * @param <T> expected result class to be parsed from response
     * @param uri context URI of the resource (without <code>/api/v2</code> prefix
     * @param body body - usually url encoded, to POST to API
     * @param type expected result class to be parsed from response
     * @return object defined as <code>T</code> if succesfully returned and parsed
     * @throws APIException on any problem related to API communication
     */
    public <T extends APIEntity> T post(String uri, Object body, Class<T> type) throws APIException;
    
    /**
     * Calls DELETE request to API
     * @param uri context URI of the resource (without <code>/api/v2</code> prefix
     * @throws APIException on any problem related to API communication
     */
    public void delete(String uri) throws APIException;
}
