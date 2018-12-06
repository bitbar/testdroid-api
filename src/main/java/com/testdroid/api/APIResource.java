package com.testdroid.api;

import java.io.InputStream;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
public class APIResource<T extends APIEntity> {

    protected final APIClient client;

    protected final Class<T> type;

    protected final String resourceURI;

    private T entity;

    public APIResource(APIClient client, String resourceURI, Class<T> type) {
        this.client = client;
        this.resourceURI = resourceURI;
        this.type = type;
    }

    /**
     * Returns the entity owned by this resource.
     * It may be retrieved from server or from cache.
     *
     * @throws APIException on any API errors.
     */
    public T getEntity() throws APIException {
        refresh();
        return entity;
    }

    /**
     * Calls API and returns response stream as a result. No cache is used here.
     *
     * @return InputStream from API response
     * @throws APIException on any API errors.
     */
    public InputStream getStream() throws APIException {
        return client.get(resourceURI);
    }

    /**
     * Updates the entity owned by this resource in server side.
     *
     * @return updated entity
     * @throws APIException on any API errors.
     */
    public T update() throws APIException {
        entity = client.post(resourceURI, null, type);
        return entity;
    }

    /**
     * Deletes the entity owned by this resource in server side.
     *
     * @throws APIException on any API errors.
     */
    public void delete() throws APIException {
        client.delete(resourceURI);
        this.entity = null;
    }

    /**
     * Forces retrieving entity from the server side.
     *
     * @throws APIException on any API errors.
     */
    public void refresh() throws APIException {
        entity = client.get(resourceURI, type);
    }
}
