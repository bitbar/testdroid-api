package com.testdroid.api;

import okio.BufferedSink;
import okio.Okio;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.testdroid.api.model.APIDevice.OsType.ANDROID;
import static com.testdroid.api.model.APIDevice.OsType.DESKTOP;
import static com.testdroid.cloud.test.categories.TestTags.UNIT;
import static org.assertj.core.api.Assertions.assertThat;

@Tag(UNIT)
class AbstractAPIClientTest {

    @Test
    void buildFromMap() throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("string", "stri%g");
        data.put("int", 123);
        data.put("long", 123L);
        data.put("boolean", true);
        data.put("double", 123.0);
        data.put("enum", ANDROID);
        data.put("enumArray[]", List.of(ANDROID, DESKTOP));
        // @formatter:off
        String expected = "boolean=true&string=stri%25g&double=123.0&enumArray[]=ANDROID&enumArray[]=DESKTOP&int=123&long=123&enum=ANDROID";
        // @formatter:on

        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
             BufferedSink sink = Okio.buffer(Okio.sink(os))) {
            AbstractAPIClient.buildFromMap(data).writeTo(sink);
            sink.flush();
            assertThat(os).hasToString(expected);
        }
    }
}
