package com.testdroid.api;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author kajdus
 */
public class XMLDateAdapter extends XmlAdapter<Long, Date> {

    @Override
    public Date unmarshal(Long value) throws Exception {
        return new Date(value);
    }

    @Override
    public Long marshal(Date value) throws Exception {
        return value.getTime();
    }
    
}
