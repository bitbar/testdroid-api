package com.testdroid.api.util;

import org.apache.commons.io.IOUtils;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BitbarUtils {

    public static File loadFile(String name) {
        return new File(BitbarUtils.class.getResource(name).getFile());
    }

    public static Path loadPath(String name) throws URISyntaxException {
        return Paths.get(loadURI(name));
    }

    public static URI loadURI(String name) throws URISyntaxException {
        return BitbarUtils.class.getResource(name).toURI();
    }

    public static String loadString(String name) throws IOException {
        return IOUtils.toString(loadStream(name), StandardCharsets.UTF_8);
    }

    public static InputStream loadStream(String path) {
        return BitbarUtils.class.getResourceAsStream(path);
    }

    public static InputSource loadInputSource(String path) {
        return toInputSource(loadStream(path));
    }

    public static InputSource toInputSource(File file) throws FileNotFoundException {
        return toInputSource(new FileInputStream(file));
    }

    private static InputSource toInputSource(InputStream inputStream) {
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        InputSource result = new InputSource(reader);
        result.setEncoding(StandardCharsets.UTF_8.name());
        return result;
    }

}
