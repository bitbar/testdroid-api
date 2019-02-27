package com.testdroid.api;

import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.ListStringFilterEntry;
import com.testdroid.api.filter.NumberFilterEntry;
import com.testdroid.api.model.APIUser;
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

    private static final APISort SORT_EMPTY = APISort.deserialize(EMPTY);

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
                        String.format("%s?offset=10&limit=10&search=&sort=&filter=", URL)
                },
                {
                        100,
                        new Context<>(APIUser.class).setSort(SORT_EMPTY).setSearch(EMPTY),
                        null,
                        String.format("%s?offset=20&limit=20&search=&sort=&filter=", URL)
                },
                {
                        100,
                        new Context<>(APIUser.class).setSort(SORT_EMPTY).setSearch("text"),
                        null,
                        String.format("%s?offset=20&limit=20&search=text&sort=&filter=", URL)
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setSort(SORT_EMPTY).setSearch("text"),
                        String.format("%s?offset=5&limit=20&search=text&sort=&filter=", URL),
                        String.format("%s?offset=45&limit=20&search=text&sort=&filter=", URL)
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setLimit(5).setSort(SORT_EMPTY).setSearch("text"),
                        String.format("%s?offset=20&limit=5&search=text&sort=&filter=", URL),
                        String.format("%s?offset=30&limit=5&search=text&sort=&filter=", URL)
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setLimit(5).setSort(APISort.deserialize("email_a"))
                                .setSearch("text"),
                        String.format("%s?offset=20&limit=5&search=text&sort=email_a&filter=", URL),
                        String.format("%s?offset=30&limit=5&search=text&sort=email_a&filter=", URL)
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setLimit(5).setSort(APISort.deserialize("state_d"))
                                .setSearch("text"),
                        String.format("%s?offset=20&limit=5&search=text&sort=state_d&filter=", URL),
                        String.format("%s?offset=30&limit=5&search=text&sort=state_d&filter=", URL)
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setLimit(5).setSort(APISort.deserialize("state_d"))
                                .setSearch("text").addFilter(new NumberFilterEntry(ID, EQ, 10)),
                        String.format("%s?offset=20&limit=5&search=text&sort=state_d&filter=n_id_EQ_10", URL),
                        String.format("%s?offset=30&limit=5&search=text&sort=state_d&filter=n_id_EQ_10", URL)
                },
                {
                        100,
                        new Context<>(APIUser.class).setOffset(25).setLimit(5).setSort(APISort.deserialize("state_d"))
                                .setSearch(EMPTY)
                                .addFilter(new ListStringFilterEntry(NAME, IN, Arrays.asList("Nowak", "Skrobak"))),
                        String.format("%s?offset=20&limit=5&search=&sort=state_d&filter=name_IN_Nowak|Skrobak", URL),
                        String.format("%s?offset=30&limit=5&search=&sort=state_d&filter=name_IN_Nowak|Skrobak", URL)
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
