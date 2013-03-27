package com.testdroid.api;

/**
 *
 * @author kajdus
 */
public interface APIExportable<T extends APIEntity> {
    
    /**
     * Exports object implementing this interface to type <code>T</code> being <code>APIEntity</code>
     * @return exported object of type <code>T</code>
     */
    T export();
}
