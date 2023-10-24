package com.testdroid.api.serialization.level;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.logging.log4j.Level;

/**
 * @author Artur Ćwikliński <artur.cwiklinski@smartbear.com>
 *
 * This class is responsible for serialization and deserialization of log4j2 Level class.
 */
public class Log4jLevelModule extends SimpleModule {

    public Log4jLevelModule() {
        this.addSerializer(Level.class, new Log4jLevelSerializer(Level.class));
        this.addDeserializer(Level.class, new Log4jLevelDeserializer(Level.class));
    }
}
