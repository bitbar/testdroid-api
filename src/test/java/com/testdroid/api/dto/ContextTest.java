package com.testdroid.api.dto;

import com.testdroid.api.APIEntity;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static com.testdroid.cloud.test.categories.TestTags.UNIT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Damian Sniezek <damian.sniezek@smartbear.com>
 */
@Tag(UNIT)
class ContextTest {

    private Context<APIEntity> classUnderTest;

    private static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                //Count not executed/set we can't relay on it => LIMIT = page_size
                {0, 10, null, 10},
                //First page, only 5 elements overall => LIMIT=5
                {0, 10, 5L, 5},
                //First page, # of elements overall = page_size => LIMIT = page_size
                {0, 10, 10L, 10},
                //Second page, # of elements overall = page_size => LIMIT=0
                {10, 10, 10L, 0},
                //First page, 15 elements overall  => LIMIT=10
                {0, 10, 15L, 10},
                //Second page, 15 elements overall  => LIMIT=5
                {10, 10, 15L, 5},
                //Count not executed/set we can't relay on it, but no paging at all(limit=0) => NO LIMIT
                {0, 0, null, null},
                //no paging at all(limit=0) => NO LIMIT
                {0, 0, 5L, null},
                //no paging at all(limit=Integer.MAX_VALUE) => NO LIMIT
                {0, Integer.MAX_VALUE, 5L, null},
        };
        return Arrays.asList(data);
    }

    @MethodSource("data")
    @ParameterizedTest
    void compute(int offset, int limit, Long count, Integer expectedMaxResults) {
        classUnderTest = new Context<>(APIEntity.class, offset, limit, null, null);
        Optional.ofNullable(count).ifPresent(classUnderTest::setCount);
        Optional<Integer> optionalMaxResult = classUnderTest.computeMaxResult();
        assertThat(optionalMaxResult, is(Optional.ofNullable(expectedMaxResults)));
    }
}
