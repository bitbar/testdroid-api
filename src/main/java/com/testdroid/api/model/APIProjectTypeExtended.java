package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIProjectTypeExtended extends APIEntity {

    private APIProject.Type type;

    private String title;

    public APIProjectTypeExtended() {

    }

    public APIProjectTypeExtended(APIProject.Type type) {
        this.type = type;
        this.title = type.getTitle();
    }

    public APIProject.Type getType() {
        return type;
    }

    public void setType(APIProject.Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIProjectTypeExtended apiProjectTypeExtended = (APIProjectTypeExtended) from;
        cloneBase(from);
        this.type = apiProjectTypeExtended.type;
        this.title = apiProjectTypeExtended.title;
    }
}
