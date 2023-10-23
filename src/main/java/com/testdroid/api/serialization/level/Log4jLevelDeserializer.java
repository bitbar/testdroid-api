package com.testdroid.api.serialization.level;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.logging.log4j.Level;

import java.io.IOException;

/**
 * @author Artur Ćwikliński <artur.cwiklinski@smartbear.com>
 */
public class Log4jLevelDeserializer extends StdDeserializer<Level> {

    protected Log4jLevelDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Level deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String name = jsonParser.getText();
        return Level.getLevel(name);
    }
}
