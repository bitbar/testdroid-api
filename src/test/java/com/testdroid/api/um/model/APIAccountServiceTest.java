package com.testdroid.api.um.model;

import com.testdroid.api.model.APIAccountService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.testdroid.cloud.test.categories.TestTags.UNIT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stefano Gregori <stefano.gregori@bitbar.com>
 */
@Tag(UNIT)
class APIAccountServiceTest {

    @Test
    void testAccountServiceIsActiveAt() {
        //GIVEN
        Date startTime = dateOf(2014, 3, 11, 10, 34);
        Date endTime = dateOf(2015, 3, 11, 10, 34);
        APIAccountService service = prepareAccountService(startTime, endTime);

        //THEN
        assertThat(service.isActiveAt(dateOf(2014, 2, 11, 10, 34))).isFalse();
        assertThat(service.isActiveAt(startTime)).isTrue();
        assertThat(service.isActiveAt(dateOf(2014, 5, 11, 10, 34))).isTrue();
        assertThat(service.isActiveAt(endTime)).isTrue();
        assertThat(service.isActiveAt(dateOf(2016, 2, 11, 10, 34))).isFalse();

        // AND WHEN
        service.setStartTime(null);

        //THEN
        assertThat(service.isActiveAt(dateOf(2014, 2, 11, 10, 34))).isFalse();
        assertThat(service.isActiveAt(dateOf(2014, 5, 11, 10, 34))).isFalse();
        assertThat(service.isActiveAt(dateOf(2016, 2, 11, 10, 34))).isFalse();
    }

    @Test
    void testAccountServiceIsActiveAt_withNullEndDate() {
        //GIVEN
        Date startTime = dateOf(2014, 3, 11, 10, 34);
        APIAccountService service = prepareAccountService(startTime, null);

        //THEN
        assertThat(service.isActiveAt(dateOf(2014, 2, 11, 10, 34))).isFalse();
        assertThat(service.isActiveAt(dateOf(2014, 5, 11, 10, 34))).isTrue();
        assertThat(service.isActiveAt(startTime)).isTrue();
        assertThat(service.isActiveAt(dateOf(2016, 2, 11, 10, 34))).isTrue();

        // AND WHEN
        service.setStartTime(null);

        //THEN
        assertThat(service.isActiveAt(dateOf(2014, 2, 11, 10, 34))).isFalse();
        assertThat(service.isActiveAt(dateOf(2014, 5, 11, 10, 34))).isFalse();
        assertThat(service.isActiveAt(dateOf(2016, 2, 11, 10, 34))).isFalse();
    }

    @Test
    void testAccountServiceIsActiveAt_nullParam() {
        //GIVEN
        Date startTime = dateOf(2014, 3, 11, 10, 34);
        Date endTime = dateOf(2015, 3, 11, 10, 34);
        APIAccountService service = prepareAccountService(startTime, endTime);

        //THEN
        assertThat(service.isActiveAt(null)).isFalse();
    }

    private APIAccountService prepareAccountService(Date startTime, Date endTime) {
        APIAccountService service = new APIAccountService();
        service.setStartTime(startTime);
        service.setEndTime(endTime);
        return service;
    }

    private Date dateOf(int year, int month, int day, int hour, int minute) {
        return Date.from(LocalDateTime.of(year, month, day, hour, minute).atZone(ZoneId.systemDefault()).toInstant());
    }
}
