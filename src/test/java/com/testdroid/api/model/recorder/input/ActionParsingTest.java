package com.testdroid.api.model.recorder.input;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static com.testdroid.api.APIEntity.OBJECT_MAPPER;
import static com.testdroid.cloud.test.categories.TestTags.UNIT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
@Tag(UNIT)
class ActionParsingTest {

    private static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {"""
                {
                  "id": "302d5a7b-4622-4451-8e03-eca56d1b9201",
                  "type": "click",
                  "createTime": 1771929306399,
                  "updatedTime": 1771929306399,
                  "duration": 76,
                  "point": {
                    "x": 0.1880108991825613,
                    "y": 0.48473748473748474
                  }
                }
                """, ActionType.CLICK},
                {"""
                {
                  "id": "c8c23538-831c-4fc9-80fb-c5584f1b71de",
                  "type": "swipe",
                  "createTime": 1771929335443,
                  "updatedTime": 1771929335443,
                  "duration": 276,
                  "startPoint": {
                    "x": 0.17166212534059946,
                    "y": 0.6007326007326007
                  },
                  "endPoint": {
                    "x": 0.782016348773842,
                    "y": 0.326007326007326
                  }
                }
                """, ActionType.SWIPE},
                {"""
                {
                  "id": "fe64df95-9b71-4d25-8f4d-faf686f568fe",
                  "type": "type",
                  "createTime": 1771933413487,
                  "updatedTime": 1771933414158,
                  "duration": 432,
                  "text": "recorder test"
                }
                """, ActionType.TYPE},
        };
        return Arrays.asList(data);
    }

    @MethodSource("data")
    @ParameterizedTest
    void parseAction(String json, ActionType expectedActionType) throws Exception {
        Action action = OBJECT_MAPPER.readValue(json, Action.class);
        assertThat(action.getType()).isEqualTo(expectedActionType);
    }
}
