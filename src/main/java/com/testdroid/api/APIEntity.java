package com.testdroid.api;

import com.testdroid.api.model.*;
import com.testdroid.api.model.AndroidFiles;
import com.testdroid.api.model.IOSFiles;
import com.testdroid.api.model.RemoteControlFiles;
import com.testdroid.api.model.UIAutomatorFiles;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 *
 * @author kajdus
 */
@XmlRootElement
@XmlSeeAlso({APIUser.class, APIDeviceGroup.class, APIProject.class, APITestRun.class,APITestRunConfig.class, APIProjectSharing.class,
    APIProjectJobConfig.class, APIFiles.class, AndroidFiles.class, IOSFiles.class, UIAutomatorFiles.class, RemoteControlFiles.class,
    APIFiles.APIFile.class, APIFiles.AndroidAppFile.class, APIFiles.AndroidTestFile.class, APIFiles.DataFile.class, APIFiles.IOSAppFile.class, APIFiles.IOSTestFile.class,
    APIFiles.UIAutomatorTestFile.class, APITag.class, APIDeviceRun.class, APIDeviceRunState.class, APISoftwareVersion.class, APIScreenshot.class, APIDevice.class,
    APIDeviceProperty.class})
public abstract class APIEntity {
    protected APIClient client;
    protected String selfURI;
    protected Long id;
        
    public APIEntity() {}

    public APIEntity(Long id) {
        this.id = id;
    }
        
    /**
     * Returns ID of entity if such ID exists.
     * Usually it does not exist for lists. Please use
     * <code>hasId()</code> method to check if ID exists.
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Returns <code>true</code> if ID exists for this entity.
     */
    public boolean hasId() {
        return true;
    }
    
    @JsonIgnore
    protected <T extends APIEntity> APIResource<T> getResource(String uri, Class<T> type) throws APIException {
        checkClient(client);
        return new APIResource<T>(client, uri, type);
    }
    
    @JsonIgnore
    protected <T extends APIEntity> APIListResource<T> getListResource(String uri, Class<T> type) throws APIException {
        checkClient(client);
        return new APIListResource<T>(client, uri, type);
    }
    
    @JsonIgnore
    protected <T extends APIEntity> APIListResource<T> getListResource(String uri, long offset, long limit, String search, APISort sort, Class<T> type) throws APIException {
        checkClient(client);
        return new APIListResource<T>(client, uri, offset, limit, search, sort, type);
    }
    
    @JsonIgnore
    protected <T extends APIEntity> T postResource(String uri, Object body, Class<T> type) throws APIException {
        checkClient(client);
        return client.post(uri, body, type);
    }
    
    @JsonIgnore
    protected void deleteResource(String uri) throws APIException {
        checkClient(client);
        client.delete(uri);
    }
    
    @JsonIgnore
    private void checkClient(APIClient client) throws APIException {
        if(client == null) {
            throw new APIException("Missing API client");
        }
    }
    
    private static final String ENCODING = "UTF-8";
    @JsonIgnore
    public static String encodeURL(String name) {
        try {
            if(name == null) {
                return "";
            }
            return URLEncoder.encode(name, ENCODING);
        } catch (UnsupportedEncodingException ex) {
        }
        return name;
    }

    @JsonIgnore
    public static String encodeURL(boolean value) {
        return value ? "on" : "";
    }
    
    @JsonIgnore
    public static String decodeURL(String name) {
        try {
            return URLDecoder.decode(name, ENCODING);
        } catch (UnsupportedEncodingException ex) {
        }
        return name;
    }
    
    @JsonIgnore
    public String toXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(this.getClass());
            Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(this, writer);
            return writer.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(APIEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    @JsonIgnore
    public static  <T extends APIEntity> T fromXML(String xml, Class<T> type) {
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T)unmarshaller.unmarshal(new StringReader(xml));            
        } catch (JAXBException ex) {
            Logger.getLogger(APIEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
