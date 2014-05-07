package com.testdroid.api;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

/**
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
