package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import static com.testdroid.api.APIEntity.createUri;
import com.testdroid.api.APIException;
import com.testdroid.api.APIListResource;
import com.testdroid.api.APIQueryBuilder;
import java.util.Collections;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIFileSet extends APIEntity {

    private String name;
    private Date createTime;
    private Integer fileCount;

    public APIFileSet() {
    }
    
    public APIFileSet(Long id, String name, Date createTime, Integer fileCount) {
        super(id);
        this.name = name;
        this.createTime = createTime;
        this.fileCount = fileCount;
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

    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }
    
    private String getIncludedFilesURI() { return createUri(selfURI, "/files"); };
    private String getIncludedFilesURI(Long fileId) { return createUri(selfURI, "/files/" + fileId); };

    @JsonIgnore
    public APIListResource<APIUserFile> getIncludedFilesResource() throws APIException {
        return getListResource(getIncludedFilesURI(), APIUserFile.class);
    }
    
    @JsonIgnore
    public APIListResource<APIUserFile> getIncludedFilesResource(APIQueryBuilder queryBuilder) throws APIException {
        return getListResource(getIncludedFilesURI(), queryBuilder, APIUserFile.class);
    }
    
    public void delete() throws APIException {
        deleteResource(selfURI);
    }
    
    public void addFile(APIUserFile file) throws APIException {
        postResource(getIncludedFilesURI(), Collections.singletonMap("fileId", file.getId()), null);
    }
    
    public void removeFile(APIUserFile file) throws APIException {
        deleteResource(getIncludedFilesURI(file.getId()));
    }
    
    public void update() throws APIException {
        APIFileSet fileSet = postResource(selfURI, Collections.singletonMap("name", name), APIFileSet.class);
        clone(fileSet);
    }
    
    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIFileSet apiFileSet = (APIFileSet) from;
        cloneBase(from);
        this.name = apiFileSet.name;
        this.createTime = apiFileSet.createTime;
        this.fileCount = apiFileSet.fileCount;
    }
}
