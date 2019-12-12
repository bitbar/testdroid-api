package com.testdroid.api.http;

import com.google.api.client.http.*;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by sakari on 6/10/13.
 */
public class MultipartFormDataContent extends AbstractHttpContent {

    private static final String NEWLINE = "\r\n";

    private static final String TWO_DASHES = "--";

    /**
     * Parts of the HTTP multipart request.
     */
    private ArrayList<Part> parts = new ArrayList<>();

    public MultipartFormDataContent() {
        super(new HttpMediaType("multipart/form-data").setParameter("boundary", "__END_OF_PART__"));
    }

    public void writeTo(OutputStream out) throws IOException {
        Writer writer = new OutputStreamWriter(out, getCharset());
        String boundary = getBoundary();

        HttpHeaders headers;
        String contentDisposition;
        HttpContent content;
        StreamingContent streamingContent;
        for (Part part : parts) {
            // analyze the headers
            headers = new HttpHeaders().setAcceptEncoding(null);
            if (part.headers != null) {
                headers.fromHttpHeaders(part.headers);
            }
            headers.setContentEncoding(null)
                    .setUserAgent(null)
                    .setContentType(null)
                    .setContentLength(null)
                    .set("Content-Transfer-Encoding", null);

            // Write disposition
            if (part.getName() != null) {
                contentDisposition = String.format("form-data; name=\"%s\"", part.name);
                // Do we have a filename?
                // Then add to the content dispos
                if (part.filename != null) {
                    contentDisposition += String.format("; filename=\"%s\"", part.filename);
                }
                headers.set("Content-Disposition", contentDisposition);
            }

            // analyze the content
            content = part.content;
            streamingContent = null;
            if (content != null) {
                headers.setContentType(content.getType());
                headers.set("Content-Transfer-Encoding", Collections.singletonList("binary"));
                final HttpEncoding encoding = part.encoding;
                if (encoding == null) {
                    streamingContent = content;
                } else {
                    headers.setContentEncoding(encoding.getName());
                    streamingContent = new HttpEncodingStreamingContent(content, encoding);
                }
            }

            // write separator
            writer.write(TWO_DASHES);
            writer.write(boundary);
            writer.write(NEWLINE);
            // Write Headers
            HttpHeaders.serializeHeadersForMultipartRequests(headers, null, null, writer);
            // write content
            if (streamingContent != null) {
                writer.write(NEWLINE);
                writer.flush();
                streamingContent.writeTo(out);
                writer.write(NEWLINE);
            }
        }
        // write end separator
        writer.write(TWO_DASHES);
        writer.write(boundary);
        writer.write(TWO_DASHES);
        writer.write(NEWLINE);
        writer.flush();
    }

    @Override
    public boolean retrySupported() {
        for (Part part : parts) {
            if (!part.content.retrySupported()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public MultipartFormDataContent setMediaType(HttpMediaType mediaType) {
        super.setMediaType(mediaType);
        return this;
    }

    /**
     * Returns an unmodifiable view of the parts of the HTTP multipart request.
     */
    public final Collection<Part> getParts() {
        return Collections.unmodifiableCollection(parts);
    }

    /**
     * Sets the parts of the HTTP multipart request.
     * <p/>
     * <p>
     * Overriding is only supported for the purpose of calling the super implementation and changing
     * the return type, but nothing else.
     * </p>
     */
    public MultipartFormDataContent setParts(Collection<Part> parts) {
        this.parts = new ArrayList<>(parts);
        return this;
    }

    /**
     * Adds an HTTP multipart part.
     * <p/>
     * <p>
     * Overriding is only supported for the purpose of calling the super implementation and changing
     * the return type, but nothing else.
     * </p>
     */
    public MultipartFormDataContent addPart(Part part) {
        parts.add(Preconditions.checkNotNull(part));
        return this;
    }

    /**
     * Returns the boundary string to use.
     */
    public final String getBoundary() {
        return getMediaType().getParameter("boundary");
    }

    /**
     * Sets the boundary string to use.
     * <p/>
     * <p>
     * Defaults to {@code "__END_OF_PART__"}.
     * </p>
     * <p/>
     * <p>
     * Overriding is only supported for the purpose of calling the super implementation and changing
     * the return type, but nothing else.
     * </p>
     */
    public MultipartFormDataContent setBoundary(String boundary) {
        getMediaType().setParameter("boundary", Preconditions.checkNotNull(boundary));
        return this;
    }

    /**
     * Single part of a multi-part request.
     * <p/>
     * <p>
     * Implementation is not thread-safe.
     * </p>
     */
    public static final class Part {

        /**
         * HTTP content or {@code null} for none.
         */
        private HttpContent content;

        /**
         * HTTP encoding or {@code null} for none.
         */
        private HttpEncoding encoding;

        /**
         * FileName of the File being uploaded or {@code null} for none.
         */
        private String filename;

        /**
         * HTTP header or {@code null} for none.
         */
        private HttpHeaders headers;

        /**
         * Name of this FormPart
         */
        private String name;

        public Part() {
            this(null, null);
        }

        /**
         * @param name    HTTP headers or {@code null} for none
         * @param content HTTP content or {@code null} for none
         */
        public Part(String name, HttpContent content) {
            setName(name);
            setContent(content);
        }

        /**
         * Returns the HTTP content or {@code null} for none.
         */
        public HttpContent getContent() {
            return content;
        }

        /**
         * Sets the HTTP content or {@code null} for none.
         */
        public Part setContent(HttpContent content) {
            this.content = content;
            if (content instanceof FileContent) {
                final File file = ((FileContent) content).getFile();
                if (file != null && file.exists()) {
                    setFilename(file.getName());
                }
            }
            return this;
        }

        public String getName() {
            return name;
        }

        public Part setName(final String name) {
            this.name = name;
            return this;
        }

        public String getFilename() {
            return filename;
        }

        /**
         * Set the HTTP form-part filename or null for none.
         *
         * @param filename
         */
        public Part setFilename(final String filename) {
            this.filename = filename;
            return this;
        }

        /**
         * Returns the HTTP headers or {@code null} for none.
         */
        public HttpHeaders getHeaders() {
            return headers;
        }

        /**
         * Sets the HTTP headers or {@code null} for none.
         */
        public Part setHeaders(final HttpHeaders headers) {
            this.headers = headers;
            return this;
        }

        /**
         * Returns the HTTP encoding or {@code null} for none.
         */
        public HttpEncoding getEncoding() {
            return encoding;
        }

        /**
         * Sets the HTTP encoding or {@code null} for none.
         */
        public Part setEncoding(HttpEncoding encoding) {
            this.encoding = encoding;
            return this;
        }
    }
}