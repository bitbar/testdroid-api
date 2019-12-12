package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.FilterEntry;
import com.testdroid.api.model.APIUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static com.testdroid.api.dto.MappingKey.ID;
import static com.testdroid.api.dto.MappingKey.NAME;
import static com.testdroid.api.dto.Operand.EQ;
import static com.testdroid.api.dto.Operand.IN;
import static com.testdroid.cloud.test.categories.TestTags.UNIT;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@Tag(UNIT)
class APIListTest {

    private static final String URL = "https://cloud.bitbar.com/api/v2/users";

    private static final String FORMAT = "%s?offset=%s&limit=%s&search=%s&sort=%s&filter=%s";

    private static final String SEARCH = RandomStringUtils.randomAlphanumeric(30);

    private static final APISort SORT_EMPTY = APISort.deserialize(EMPTY);

    private static final String SORT_RAW = "state_d";

    private static final APISort SORT = APISort.deserialize(SORT_RAW);

    private static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {
                        0,
                        new Context<>(APIUser.class).setSort(SORT_EMPTY).setSearch(EMPTY),
                        null,
                        null
                },
                {
                        20,
                        new Context<>(APIUser.class).setSort(SORT_EMPTY).setSearch(EMPTY),
                        null,
                        null,
                },
                {
                        20,
                        new Context<>(APIUser.class).setSort(SORT_EMPTY).setSearch(EMPTY).setLimit(10),
                        null,
                        String.format(FORMAT, URL, 10, 10, EMPTY, EMPTY, EMPTY)
                },
                {
                        100,
                        new Context<>(APIUser.class).setSort(SORT_EMPTY).setSearch(EMPTY),
                        null,
                        String.format(FORMAT, URL, 20, 20, EMPTY, EMPTY, EMPTY)
                },
                {
                        100,
                        new Context<>(APIUser.class).setSort(SORT_EMPTY).setSearch(SEARCH),
                        null,
                        String.format(FORMAT, URL, 20, 20, SEARCH, EMPTY, EMPTY)
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setSort(SORT_EMPTY).setSearch(SEARCH),
                        String.format(FORMAT, URL, 5, 20, SEARCH, EMPTY, EMPTY),
                        String.format(FORMAT, URL, 45, 20, SEARCH, EMPTY, EMPTY)
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setLimit(5).setSort(SORT_EMPTY).setSearch(SEARCH),
                        String.format(FORMAT, URL, 20, 5, SEARCH, EMPTY, EMPTY),
                        String.format(FORMAT, URL, 30, 5, SEARCH, EMPTY, EMPTY)
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setLimit(5).setSort(SORT)
                                .setSearch(SEARCH),
                        String.format(FORMAT, URL, 20, 5, SEARCH, SORT_RAW, EMPTY),
                        String.format(FORMAT, URL, 30, 5, SEARCH, SORT_RAW, EMPTY)
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setLimit(5).setSort(SORT).setSearch(SEARCH),
                        String.format(FORMAT, URL, 20, 5, SEARCH, SORT_RAW, EMPTY),
                        String.format(FORMAT, URL, 30, 5, SEARCH, SORT_RAW, EMPTY)
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setLimit(5).setSort(SORT).setSearch(SEARCH)
                                .addFilter(new FilterEntry(ID, EQ, 10)),
                        String.format(FORMAT, URL, 20, 5, SEARCH, SORT_RAW, "id_EQ_10"),
                        String.format(FORMAT, URL, 30, 5, SEARCH, SORT_RAW, "id_EQ_10")
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setLimit(5).setSort(SORT).setSearch(EMPTY)
                                .addFilter(new FilterEntry(NAME, IN, Arrays.asList("Nowak", "Skrobak"))),
                        String.format(FORMAT, URL, 20, 5, EMPTY, SORT_RAW, "name_IN_Nowak|Skrobak"),
                        String.format(FORMAT, URL, 30, 5, EMPTY, SORT_RAW, "name_IN_Nowak|Skrobak")
                },
        };
        return Arrays.asList(data);
    }

    @ParameterizedTest
    @MethodSource("data")
    void testLinks(int total, Context<?> ctx, String expectedPrevious, String expectedNext) {
        APIList<?> apiList = new APIList<>(URL, null, total, ctx);
        assertThat(apiList.getPrevious(), is(expectedPrevious));
        assertThat(apiList.getNext(), is(expectedNext));
    }

}
