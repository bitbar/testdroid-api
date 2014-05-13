package com.testdroid.api.um.model;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Stefano Gregori on 13/05/14.
 */
public class APIUserService_Test {
    @Test
    public void testUserServiceIsActiveAt() {
        //GIVEN
        DateTime startTime = new DateTime(2014, 03, 11, 10, 34);
        DateTime endTime = new DateTime(2015, 03, 11, 10, 34);
        APIUserService service = new APIUserService();
        service.setStartTime(startTime.toDate());
        service.setEndTime(endTime.toDate());

        //THEN
        assertThat(service.isActiveAt(new DateTime(2014, 02, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(startTime.toDate())).isTrue();
        assertThat(service.isActiveAt(new DateTime(2014, 05, 11, 10, 34).toDate())).isTrue();
        assertThat(service.isActiveAt(endTime.toDate())).isTrue();
        assertThat(service.isActiveAt(new DateTime(2016, 02, 11, 10, 34).toDate())).isFalse();

        // AND WHEN
        service.setStartTime(null);

        //THEN
        assertThat(service.isActiveAt(new DateTime(2014, 02, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2014, 05, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2016, 02, 11, 10, 34).toDate())).isFalse();
    }

    @Test
    public void testUserServiceIsActiveAt_withNullEndDate() {
        //GIVEN
        DateTime startTime = new DateTime(2014, 03, 11, 10, 34);
        APIUserService service = new APIUserService();
        service.setStartTime(startTime.toDate());
        service.setEndTime(null);

        //THEN
        assertThat(service.isActiveAt(new DateTime(2014, 02, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2014, 05, 11, 10, 34).toDate())).isTrue();
        assertThat(service.isActiveAt(new DateTime(2016, 02, 11, 10, 34).toDate())).isTrue();

        // AND WHEN
        service.setStartTime(null);

        //THEN
        assertThat(service.isActiveAt(new DateTime(2014, 02, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2014, 05, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2016, 02, 11, 10, 34).toDate())).isFalse();
    }
}
