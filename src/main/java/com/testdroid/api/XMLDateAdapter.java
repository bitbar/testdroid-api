package com.testdroid.api;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
public class XMLDateAdapter extends XmlAdapter<Long, Date> {

    @Override
    public Date unmarshal(Long value) {
        return new Date(value);
    }

    @Override
    public Long marshal(Date value) {
        return value.getTime();
    }

}
