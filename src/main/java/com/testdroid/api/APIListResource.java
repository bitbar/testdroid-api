package com.testdroid.api;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APIListResource<T extends APIEntity> extends APIResource<APIList<T>> {

    public APIListResource(APIClient client, String resourceURI) {
        this(client, resourceURI, new APIQueryBuilder());
    }

    public APIListResource(APIClient client, String resourceURI, APIQueryBuilder queryBuilder) {
        super(client, resourceURI, queryBuilder, (Class<APIList<T>>) (Class) APIList.class);
    }

    @Override
    public APIList<T> getEntity() throws APIException {
        APIList<T> result = super.getEntity();
        for (APIEntity item : result.getData()) {
            item.client = this.client;
            item.selfURI = this.resourceURI;
        }
        return result;
    }

    /**
     * Returns total number of available items in this resource list.
     *
     * @throws APIException on any API errors.
     */
    public Integer getTotal() throws APIException {
        return getEntity().getTotal();
    }

    /**
     * Returns <code>true</code> if next page of items is available.
     */
    public boolean isNextAvailable() {
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
        return new APIListResource(client, resourceURI, queryBuilder.offset(list.getOffset() + list.getLimit()));
    }

    /**
     * Returns <code>true</code> if previous page of items is available.
     */
    public boolean isPreviousAvailable() {
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
        return new APIListResource(client, resourceURI, queryBuilder.offset(list.getOffset() - list.getLimit()));
    }

}
