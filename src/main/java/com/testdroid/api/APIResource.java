package com.testdroid.api;

/**
 *
 * @author kajdus
 */
public class APIResource<T extends APIEntity> {
    
    protected APIClient client;
    private boolean loaded = false;
    private String resourceURI;
    private T entity;
    protected Class<T> type;
    
    public APIResource(APIClient client, String resourceURI, Class<T> type) {
        this.client = client;
        this.resourceURI = resourceURI;
        this.type = type;
    }
    
    /**
     * Returns the entity owned by this resource.
     * It may be retrieved from server or from cache.
     * @throws APIException on any API errors.
     */
    public T getEntity() throws APIException {
        this.load();
        return entity;
    }
    
    /**
     * Updates the entity owned by this resource in server side.
     * @return updated entity
     * @throws APIException on any API errors.
     */
    public T update() throws APIException {
        Object body = null;
        entity = client.post(resourceURI, body, type);
        return entity;
    }
    
    /**
     * Deletes the entity owned by this resource in server side.
     * @throws APIException on any API errors.
     */
    public void delete() throws APIException {
        client.delete(resourceURI);
        this.entity = null;
        this.loaded = true;
    }
    
    /**
     * Forces retrieving entity from the server side.
     * @throws APIException on any API errors.
     */
    public void refresh() throws APIException {
        clean();
        load();
    }
    
    private void load() throws APIException {
        if(!loaded) {
            entity = client.get(resourceURI, type);
        }
    }
    
    private void clean() {
        loaded = false;
    }
}
