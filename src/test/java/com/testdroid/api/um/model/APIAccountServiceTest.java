package com.testdroid.api.um.model;

import com.testdroid.api.model.APIAccountService;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Stefano Gregori <stefano.gregori@bitbar.com>
 */
public class APIAccountServiceTest {

    @Test
    public void testAccountServiceIsActiveAt() {
        //GIVEN
        DateTime startTime = new DateTime(2014, 3, 11, 10, 34);
        DateTime endTime = new DateTime(2015, 3, 11, 10, 34);
        APIAccountService service = prepareAccountService(startTime, endTime);

        //THEN
        assertThat(service.isActiveAt(new DateTime(2014, 2, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(startTime.toDate())).isTrue();
        assertThat(service.isActiveAt(new DateTime(2014, 5, 11, 10, 34).toDate())).isTrue();
        assertThat(service.isActiveAt(endTime.toDate())).isTrue();
        assertThat(service.isActiveAt(new DateTime(2016, 2, 11, 10, 34).toDate())).isFalse();

        // AND WHEN
        service.setStartTime(null);

        //THEN
        assertThat(service.isActiveAt(new DateTime(2014, 2, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2014, 5, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2016, 2, 11, 10, 34).toDate())).isFalse();
    }

    @Test
    public void testAccountServiceIsActiveAt_withNullEndDate() {
        //GIVEN
        DateTime startTime = new DateTime(2014, 3, 11, 10, 34);
        APIAccountService service = prepareAccountService(startTime, null);

        //THEN
        assertThat(service.isActiveAt(new DateTime(2014, 2, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2014, 5, 11, 10, 34).toDate())).isTrue();
        assertThat(service.isActiveAt(startTime.toDate())).isTrue();
        assertThat(service.isActiveAt(new DateTime(2016, 2, 11, 10, 34).toDate())).isTrue();

        // AND WHEN
        service.setStartTime(null);

        //THEN
        assertThat(service.isActiveAt(new DateTime(2014, 2, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2014, 5, 11, 10, 34).toDate())).isFalse();
        assertThat(service.isActiveAt(new DateTime(2016, 2, 11, 10, 34).toDate())).isFalse();
    }

    @Test
    public void testAccountServiceIsActiveAt_nullParam() {
        //GIVEN
        DateTime startTime = new DateTime(2014, 3, 11, 10, 34);
        DateTime endTime = new DateTime(2015, 3, 11, 10, 34);
        APIAccountService service = prepareAccountService(startTime, endTime);

        //THEN
        assertThat(service.isActiveAt(null)).isFalse();
    }

    private APIAccountService prepareAccountService(DateTime startTime, DateTime endTime) {
        APIAccountService service = new APIAccountService();
        service.setStartTime(startTime != null ? startTime.toDate() : null);
        service.setEndTime(endTime != null ? endTime.toDate() : null);
        return service;
    }
}
