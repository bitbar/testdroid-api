package com.testdroid.api;

import com.testdroid.api.dto.Context;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APIListResource<T extends APIEntity> {

    private final APIClient client;

    private final Context<T> context;

    private final String resourceURI;

    public APIListResource(APIClient client, String resourceURI, Class<T> type) {
        this.client = client;
        this.resourceURI = resourceURI;
        this.context = new Context<>(type);
    }

    public APIListResource(APIClient client, String resourceURI, Context<T> context) {
        this.client = client;
        this.resourceURI = resourceURI;
        this.context = context;
    }

    public APIList<T> getEntity() throws APIException {
        APIList<T> entity = client.get(resourceURI, context);
        for (APIEntity item : entity.getData()) {
            item.client = this.client;
            item.selfURI = APIEntity.createUri(this.resourceURI, String.format("/%s", item.id));
        }
        return entity;
    }

    /**
     * Returns total number of available items in this resource list.
     *
     * @throws APIException on any API errors.
     */
    public Integer getTotal() throws APIException {
        return getEntity().getTotal();
    }

    private boolean isNextAvailable() {
        try {
            APIList<T> list = getEntity();
            return list.getOffset() + list.getLimit() < list.getTotal();
        } catch (APIException ex) {
            return false;
        }
    }

    /**
     * Returns resource with next page of items in that list.
     * Should check with <code>isNextAvailable()</code> before calling this method.
     * If no next page is available, returns <code>null</code>.
     *
     * @throws APIException on any API errors.
     */
    public APIListResource<T> getNext() throws APIException {
        if (!isNextAvailable()) {
            return null;
        }
        APIList<T> list = getEntity();
        return new APIListResource<>(client, resourceURI, context.setOffset(list.getOffset() + list.getLimit()));
    }

    private boolean isPreviousAvailable() {
        try {
            return getEntity().getOffset() > 0;
        } catch (APIException ex) {
            return false;
        }
    }

    /**
     * Returns resource with previous page of items in that list.
     * Should check with <code>isPreviousAvailable()</code> before calling this method.
     * If no previous page is available, returns <code>null</code>.
     *
     * @throws APIException on any API errors.
     */
    public APIListResource<T> getPrevious() throws APIException {
        if (!isPreviousAvailable()) {
            return null;
        }
        APIList<T> list = getEntity();
        return new APIListResource<>(client, resourceURI, context.setOffset(list.getOffset() - list.getLimit()));
    }
}
