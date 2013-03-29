package com.testdroid.api;

/**
 *
 * @author kajdus
 */
public class APIListResource<T extends APIList> extends APIResource<T> {
    
    public APIListResource(APIClient client, String resourceURI, Class<T> type) {
        this(client, resourceURI, null, null, null, null, type);
    }
    
    public APIListResource(APIClient client, String resourceURI, Long offset, Long limit, String search, APISort sort, Class<T> type) {
        super(client, (offset == null && limit == null && search == null && (sort == null || sort.isEmpty())) ? resourceURI : String.format("%s?offset=%s&limit=%s&search=%s&sort=%s", resourceURI, 
                getNotNullValue(offset), getNotNullValue(limit), getNotNullValue(search), sort != null ? sort.serialize() : null), type);
    }
    
    /**
     * Returns total number of available items in this resource list.
     * @throws APIException on any API errors.
     */
    public Long getTotal() throws APIException {
        return getEntity().getTotal();
    }
    
    /**
     * Returns <code>true</code> if next page of items is available.
     */
    public boolean isNextAvailable() {
        try {
            return getEntity().getOffset() + getEntity().getLimit() < getEntity().getTotal();
        }
        catch(APIException ex) {
            return false;
        }
    }
    
    /**
     * Returns resource with next page of items in that list.
     * Should check with <code>isNextAvailable()</code> before calling this method.
     * If no next page is available, returns <code>null</code>.
     * @throws APIException on any API errors.
     */
    public APIListResource<T> getNext() throws APIException {
        if(!isNextAvailable()) {
            return null;
        }
        return new APIListResource(client, getEntity().getNext(), null, null, null, null, type);
    }
    
    /**
     * Returns <code>true</code> if previous page of items is available.
     */
    public boolean isPreviousAvailable() {
        try {
            return getEntity().getOffset() > 0;
        }
        catch(APIException ex) {
            return false;
        }
    }
    
    /**
     * Returns resource with previous page of items in that list.
     * Should check with <code>isPreviousAvailable()</code> before calling this method.
     * If no previous page is available, returns <code>null</code>.
     * @throws APIException on any API errors.
     */
    public APIListResource<T> getPrevious() throws APIException {
        if(!isPreviousAvailable()) {
            return null;
        }
        return new APIListResource(client, getEntity().getPrevious(), null, null, null, null, type);
    }
    
    private static String getNotNullValue(Object obj) {
        return obj != null ? obj.toString() : "";
    }
    
}
