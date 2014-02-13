package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import static com.testdroid.api.APIEntity.createUri;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import java.io.InputStream;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIUserFile extends APIEntity {

    private String name;
    private Date createTime;
    private Long size;
    private APIUserFileProperty[] fileProperties;

    public APIUserFile() {
    }

    public APIUserFile(Long id, String name, Date createTime, Long size, APIUserFileProperty ...fileProperties) {
        super(id);
        this.name = name;
        this.createTime = createTime;
        this.size = size;
        this.fileProperties = fileProperties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public APIUserFileProperty[] getFileProperties() {
        return fileProperties;
    }

    public void setFileProperties(APIUserFileProperty[] fileProperties) {
        this.fileProperties = fileProperties;
    }

    private String getFileURI() { return createUri(selfURI, "/file"); }
    private String getIconURI() { return createUri(selfURI, "/icon"); }
    private String getFileTagsURI() { return createUri(selfURI, "/tags"); }

    @JsonIgnore
    public InputStream getIcon() throws APIException {
        return getFile(getIconURI());
    }

    @JsonIgnore
    public InputStream getFile() throws APIException {
        return getFile(getFileURI());
    }
    
    @JsonIgnore
    public APIListResource<APIUserFileTag> getTagsResource() throws APIException {
        return getListResource(getFileTagsURI(), APIUserFileTag.class);
    }
    
    public void delete() throws APIException {
        deleteResource(selfURI);
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIUserFile apiUserFile = (APIUserFile) from;
        cloneBase(from);
        this.name = apiUserFile.name;
        this.createTime = apiUserFile.createTime;
        this.size = apiUserFile.size;
        this.fileProperties = apiUserFile.fileProperties;
    }
}
