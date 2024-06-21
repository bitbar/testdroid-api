package com.testdroid.api.model.test;

import com.testdroid.api.model.APITestCaseRun;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;

import static com.testdroid.api.dto.MappingKey.UNIT;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jarno Tuovinen <jarno.tuovinen@bitbar.com>
 */
@Tag(UNIT)
class APITestCaseRunTest {

    private final APITestCaseRun classUnderTest = new APITestCaseRun();

    private static Collection<Object[]> data() {
        //@formatter:off
        Object[][] data = new Object[][]{
                {"1.01",        1.01d},
                {"0.01",        0.01d},
                {"0",           0.00d},
                {"1",           1.00d},
                {"13.43523",    13.44d},
                {"16.2345222",  16.23d},
                {"1.58565",     1.59d},
                {EMPTY,         0.00d},
                {"asdf",        0.00d},
                {null,          0.00d}
        };
        //@formatter:on
        return List.of(data);
    }

    @ParameterizedTest
    @MethodSource("data")
    void testSetDuration(String duration, double expected) {
        classUnderTest.setDuration(duration);
        assertThat(classUnderTest.getDuration()).isEqualTo(expected);
    }

    @Test
    void testSetSuiteName() {
        classUnderTest.setSuiteName("MySuite");
        assertThat(classUnderTest.getSuiteName()).isEqualTo("MySuite");
        classUnderTest.setSuiteName(EMPTY);
        assertThat(classUnderTest.getSuiteName()).isEqualTo(APITestCaseRun.DEFAULT_SUITE_NAME);
        classUnderTest.setSuiteName(null);
        assertThat(classUnderTest.getSuiteName()).isEqualTo(APITestCaseRun.DEFAULT_SUITE_NAME);
    }
}
