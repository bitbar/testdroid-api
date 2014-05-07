package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APITag extends APIEntity {

    private String name;

    public APITag() {
    }

    public APITag(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void delete() throws APIException {
        deleteResource(selfURI);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APITag apiTag = (APITag) from;
        cloneBase(from);
        this.name = apiTag.name;
    }

}
