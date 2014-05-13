package com.testdroid.api.um.model;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @@author Stefano Gregori <stefano.gregori@bitbar.com>
 */
public class APIUserServiceTest {

    @Test
    public void testUserServiceIsActiveAt() {
        //GIVEN
        DateTime startTime = new DateTime(2014, 03, 11, 10, 34);
        DateTime endTime = new DateTime(2015, 03, 11, 10, 34);
        APIUserService service = prepareUserService(startTime, endTime);


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
        APIUserService service = prepareUserService(startTime, null);

        //THEN
        assertThat(service.isActiveAt(new DateTime(2014, 02, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2014, 05, 11, 10, 34).toDate())).isTrue();
        assertThat(service.isActiveAt(startTime.toDate())).isTrue();
        assertThat(service.isActiveAt(new DateTime(2016, 02, 11, 10, 34).toDate())).isTrue();

        // AND WHEN
        service.setStartTime(null);

        //THEN
        assertThat(service.isActiveAt(new DateTime(2014, 02, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2014, 05, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2016, 02, 11, 10, 34).toDate())).isFalse();
    }

    @Test
    public void testUserServiceIsActiveAt_nullParam() {
        //GIVEN
        DateTime startTime = new DateTime(2014, 03, 11, 10, 34);
        DateTime endTime = new DateTime(2015, 03, 11, 10, 34);
        APIUserService service = prepareUserService(startTime, endTime);

        //THEN
        assertThat(service.isActiveAt(null)).isFalse();
    }

    private APIUserService prepareUserService(DateTime startTime, DateTime endTime) {
        APIUserService service = new APIUserService();
        service.setStartTime(startTime != null ? startTime.toDate() : null);
        service.setEndTime(endTime != null ? endTime.toDate() : null);
        return service;
    }
}
