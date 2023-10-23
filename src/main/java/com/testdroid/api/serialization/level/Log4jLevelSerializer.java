package com.testdroid.api.serialization.level;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.logging.log4j.Level;

import java.io.IOException;

/**
 * @author Artur Ćwikliński <artur.cwiklinski@smartbear.com>
 */
public class Log4jLevelSerializer extends StdSerializer<Level> {

    protected Log4jLevelSerializer(Class<Level> t) {
        super(t);
    }

    @Override
    public void serialize(Level level, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(level.name());
    }
}
