package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import java.net.URL;
import java.util.Map;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIS3Url extends APIEntity {

    private String path;

    private URL url;

    private Map<String, String> headers;

    public APIS3Url() {
    }

    public APIS3Url(Long id, URL url, String path, Map<String, String> headers) {
        super(id);
        this.url = url;
        this.path = path;
        this.headers = headers;
    }

    public String getPath() {
        return path;
    }

    public URL getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        throw new UnsupportedOperationException();
    }
}
