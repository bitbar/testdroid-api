package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.net.URL;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIS3Url extends APIEntity {

    private String path;

    private URL url;

    public APIS3Url() {
    }

    public APIS3Url(Long id, URL url, String path) {
        super(id);
        this.url = url;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        throw new UnsupportedOperationException();
    }
}
