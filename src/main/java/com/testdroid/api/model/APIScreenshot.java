package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APIScreenshot extends APIEntity {
    
    @XmlType public static enum Type { LANDSCAPE, PORTRAIT }
    
    private String originalName;
    private boolean fail;
    private Type type;

    public APIScreenshot() {}

    public APIScreenshot(Long id, String originalName, boolean fail, Type type) {
        super(id);
        this.originalName = originalName;
        this.fail = fail;
        this.type = type;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
}
