package com.testdroid.api;

import java.io.InputStream;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
public class APIResource<T extends APIEntity> {

    protected APIClient client;

    protected APIQueryBuilder queryBuilder;

    protected String resourceURI;

    protected Class<T> type;

    private T entity;

    private boolean loaded = false;

    public APIResource(APIClient client, String resourceURI, Class<T> type) {
        this(client, resourceURI, null, type);
    }

    public APIResource(APIClient client, String resourceURI, APIQueryBuilder queryBuilder, Class<T> type) {
        this.client = client;
        this.resourceURI = resourceURI;
        this.type = type;
        this.queryBuilder = queryBuilder;
    }

    /**
     * Returns the entity owned by this resource.
     * It may be retrieved from server or from cache.
     *
     * @throws APIException on any API errors.
     */
    public T getEntity() throws APIException {
        this.load();
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
        this.loaded = true;
    }

    /**
     * Forces retrieving entity from the server side.
     *
     * @throws APIException on any API errors.
     */
    public void refresh() throws APIException {
        clean();
        load();
    }

    private void load() throws APIException {
        if (!loaded) {
            entity = client.get(resourceURI, queryBuilder, type);
        }
    }

    private void clean() {
        loaded = false;
    }
}
