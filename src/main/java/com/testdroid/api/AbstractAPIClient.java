package com.testdroid.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.testdroid.api.dto.Context;
import com.testdroid.api.dto.MappingKey;
import com.testdroid.api.dto.Operand;
import com.testdroid.api.filter.FilterEntry;
import com.testdroid.api.model.APIDevice;
import com.testdroid.api.model.APIDeviceProperty;
import com.testdroid.api.model.APILabelGroup;
import com.testdroid.api.model.APIUser;
import com.testdroid.api.util.TypeReferenceFactory;
import okhttp3.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import static com.testdroid.api.APIEntity.OBJECT_MAPPER;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public abstract class AbstractAPIClient implements APIClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAPIClient.class);

    protected static final String API_URI = "/api/v2";

    protected static final String ACCEPT_HEADER = "application/json";

    protected static final MediaType APPLICATION_JSON = MediaType.get(ACCEPT_HEADER);

    protected static final MediaType FORM_URLENCODED = MediaType.get("application/x-www-form-urlencoded");

    protected static final String DEVICES_URI = "/devices";

    protected static final String LABEL_GROUPS_URI = "/label-groups";

    private static final String FAILED_TO_EXECUTE_API_CALL_WITH_REASON = "Failed to execute API call: %s. Reason: %s";

    private static final UnaryOperator<String> URL_ENCODE = s -> URLEncoder.encode(s, UTF_8);

    protected int clientConnectTimeout = 20000;

    protected int clientRequestTimeout = 60000;

    protected boolean skipCheckCertificate;

    protected Proxy proxy = Proxy.NO_PROXY;

    protected String proxyUser;

    protected String proxyPassword;

    protected String apiURL;

    protected final OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (skipCheckCertificate) {
            configureToIgnoreCertificate(builder);
        }
        if (StringUtils.isNoneBlank(proxyUser, proxyPassword)) {
            builder.proxyAuthenticator((route, response) -> {
                String credential = Credentials.basic(proxyUser, proxyPassword);
                return response.request().newBuilder().header("Proxy-Authorization", credential).build();
            });
        }
        return builder
                .addInterceptor(chain -> chain.proceed(chain.request().newBuilder()
                        .addHeader("Content-Type", ACCEPT_HEADER)
                        .addHeader("Accept", ACCEPT_HEADER)
                        .build()))
                .addInterceptor(getInterceptor())
                .proxy(proxy)
                .connectTimeout(clientConnectTimeout, TimeUnit.MILLISECONDS)
                .callTimeout(clientRequestTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    @Override
    public void setConnectTimeout(int timeout) {
        clientConnectTimeout = timeout;
    }

    @Override
    public void setRequestTimeout(int timeout) {
        clientRequestTimeout = timeout;
    }

    @Override
    public <T extends APIEntity> T get(String uri, Class<T> type) throws APIException {
        return getOnce(uri, null, TypeReferenceFactory.getTypeRef(type));
    }

    @Override
    public InputStream get(String uri) throws APIException {
        return getStream(uri);
    }

    @Override
    public <T extends APIEntity> APIList<T> get(String uri, Context<T> context) throws APIException {
        return getOnce(uri, context, TypeReferenceFactory.getListTypeRef(context.getType()));
    }

    protected abstract Interceptor getInterceptor();

    /**
     * Tries to call API once. Returns expected entity or throws exception.
     */
    protected <T extends APIEntity> T getOnce(String uri, Context<?> context, TypeReference<T> type)
            throws APIException {
        try (Response response = getHttpResponse(uri, context)) {
            T result = fromJson(Objects.requireNonNull(response.body()).string(), type);
            result.client = this;
            if (result.selfURI == null) {
                result.selfURI = uri;
            }
            return result;
        } catch (IOException | RuntimeException ex) {
            throw new APIException(String.format(FAILED_TO_EXECUTE_API_CALL_WITH_REASON, uri, ex.getMessage()), ex);
        }
    }

    protected InputStream getStream(String uri) throws APIException {
        try {
            return Objects.requireNonNull(getHttpResponse(uri, null).body()).byteStream();
        } catch (RuntimeException ex) {
            throw new APIException(String.format(FAILED_TO_EXECUTE_API_CALL_WITH_REASON, uri, ex.getMessage()), ex);
        }
    }

    @Override
    public Response getHttpResponse(String uri, Context<?> context) throws APIException {
        //Fix for https://jira.bitbar.com/browse/TD-12086
        //caused by https://github.com/googleapis/google-http-java-client/issues/398
        //We should use pure Apache Http Client
        uri = uri.replaceAll("\\+", "%2B"); // TODO check if needed
        try {
            OkHttpClient client = getClient();
            Request request = new Request.Builder().url(buildUrl(apiURL + uri, context)).build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw getAPIException(response);
            }
            return response;
        } catch (IOException ex) {
            throw new APIException(String.format(FAILED_TO_EXECUTE_API_CALL_WITH_REASON, uri, ex.getMessage()), ex);
        }
    }

    @Override
    public <T extends APIEntity> T post(String uri, Object body, Class<T> type) throws APIException {
        return postOnce(uri, body, emptyMap(), null, TypeReferenceFactory.getTypeRef(type));
    }

    protected <T extends APIEntity> T postOnce(
            String uri, Object body, Map<String, String> fileExtraParams, String contentType, TypeReference<T> type)
            throws APIException {
        if (contentType == null) {
            contentType = ACCEPT_HEADER;
        }
        try {
            RequestBody requestBody = buildRequestBody(body, fileExtraParams, contentType);
            OkHttpClient client = getClient();
            Request request = new Request.Builder().url(apiURL + uri).post(requestBody).build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw getAPIException(response);
                }
                if (type != null) {
                    T result = fromJson(Objects.requireNonNull(response.body()).string(), type);
                    result.client = this;
                    if (result.selfURI == null) {
                        result.selfURI = uri;
                        // In case of entity creation, we need to update its url
                        if (response.code() == HTTP_CREATED && result.getId() != null) {
                            result.selfURI += String.format("/%s", result.getId());
                        }
                    }
                    return result;
                } else {
                    return null;
                }
            }
        } catch (IOException ex) {
            throw new APIException(String.format(FAILED_TO_EXECUTE_API_CALL_WITH_REASON, uri, ex.getMessage()), ex);
        }
    }

    @SuppressWarnings("rawtypes")
    private RequestBody buildRequestBody(Object body, Map<String, String> fileExtraParams, String contentType)
            throws IOException {
        MediaType mediaType = MediaType.parse(contentType);
        RequestBody requestBody;
        if (body instanceof File file) {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getName(), RequestBody.create(file, mediaType));
            for (Map.Entry<String, String> entry : fileExtraParams.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
            requestBody = builder.build();
        } else if (body instanceof InputStream inputStream) {
            requestBody = RequestBody.create(IOUtils.toByteArray(inputStream), mediaType);
        } else if (body instanceof APIEntity entity) {
            requestBody = RequestBody.create(OBJECT_MAPPER.writeValueAsString(entity), APPLICATION_JSON);
        } else if (body instanceof RequestBody) {
            requestBody = (RequestBody) body;
        } else if (body instanceof Map map) {
            requestBody = buildFromMap(map);
        } else if (body == null) {
            requestBody = RequestBody.create(EMPTY, null);
        } else {
            requestBody = RequestBody.create(body.toString(), mediaType);
        }
        return requestBody;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected static RequestBody buildFromMap(Map map) {
        // a bit retarded, but it is how it works in okhttp - AI generated comment
        // above comment saved for future generations - it's even more retarded
        // Set empty strings for nulls - otherwise it is not passed at all to server and parameters is ignored
        List<String> elements = new ArrayList<>();
        ((Map<String, Object>) map).forEach((k, v) -> {
            boolean isArray = isArray(v);
            boolean isCollection = isCollection(v);
            if (endsWith(k, "[]") && (isArray || isCollection)) {
                Collection<Object> multiValues = isArray ? asList((Object[]) v) : (Collection<Object>) v;
                String key = URL_ENCODE.apply(removeEnd(k, "[]"));
                multiValues.forEach(val ->
                        elements.add(String.format("%s[]=%s", key,
                                ofNullable(val).map(Object::toString).map(URL_ENCODE).orElse(EMPTY))));
            } else {
                elements.add(String.format("%s=%s", URL_ENCODE.apply(k),
                        ofNullable(v).map(Object::toString).map(URL_ENCODE).orElse(EMPTY)));
            }
        });
        return RequestBody.create(String.join("&", elements), FORM_URLENCODED);
    }

    @Override
    public <T extends APIEntity> T postFile(
            String uri, String contentType, File file, Map<String, String> fileExtraParams, Class<T> type)
            throws APIException {
        return postOnce(uri, file, fileExtraParams, contentType, TypeReferenceFactory.getTypeRef(type));
    }

    @Override
    public void delete(String uri) throws APIException {
        deleteOnce(uri);
    }

    protected void deleteOnce(String uri) throws APIException {
        OkHttpClient client = getClient();
        Request request = new Request.Builder().url(apiURL + uri).delete().build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw getAPIException(response);
            }
        } catch (IOException ex) {
            throw new APIException(String.format(FAILED_TO_EXECUTE_API_CALL_WITH_REASON, uri, ex.getMessage()), ex);
        }
    }

    @Override
    public APIUser me() throws APIException {
        return get("/me", APIUser.class);
    }

    @Override
    public APIListResource<APIDevice> getDevices() {
        return new APIListResource<>(this, DEVICES_URI, APIDevice.class);
    }

    @Override
    public APIListResource<APIDevice> getDevices(Context<APIDevice> context) {
        return new APIListResource<>(this, DEVICES_URI, context);
    }

    @Override
    public APIListResource<APILabelGroup> getLabelGroups() {
        return new APIListResource<>(this, LABEL_GROUPS_URI, APILabelGroup.class);
    }

    @Override
    public APIListResource<APILabelGroup> getLabelGroups(Context<APILabelGroup> context) {
        return new APIListResource<>(this, LABEL_GROUPS_URI, context);
    }

    @Override
    public Optional<APIDeviceProperty> findDevicePropertyInLabelGroup(String groupName, String labelName)
            throws APIException {
        Optional<APIDeviceProperty> result = Optional.empty();
        Context<APILabelGroup> ctx = new Context<>(APILabelGroup.class);
        ctx.addFilter(new FilterEntry(MappingKey.NAME, Operand.EQ, groupName));
        List<APILabelGroup> labelGroups = this.getLabelGroups(ctx).getEntity().getData();
        if (CollectionUtils.isNotEmpty(labelGroups)) {
            Context<APIDeviceProperty> lCtx = new Context<>(APIDeviceProperty.class);
            lCtx.addFilter(new FilterEntry(MappingKey.NAME, Operand.EQ, labelName));
            result = labelGroups.get(0).getDevicePropertiesResource(lCtx).getEntity().getData().stream().findFirst();
        }
        return result;
    }

    protected <T> T fromJson(InputStream inputStream, TypeReference<T> type) throws APIException {
        try {
            return OBJECT_MAPPER.readValue(inputStream, type);
        } catch (IOException e) {
            try {
                String content = IOUtils.toString(inputStream, UTF_8);
                throw new APIException(String.format("Failed to parse response as %s: %s", type.getType().getTypeName(),
                        content));
            } catch (IOException ex) {
                throw new APIException(String.format("Failed to parse response as %s", type.getType().getTypeName()));
            }
        }
    }

    protected <T> T fromJson(String content, TypeReference<T> type) throws APIException {
        try {
            return OBJECT_MAPPER.readValue(content, type);
        } catch (IOException e) {
            throw new APIException(String.format("Failed to parse response as %s: %s", type.getType().getTypeName(),
                    content));
        }
    }

    protected <T extends APIEntity> String buildUrl(String url, Context<T> context) {
        HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (context != null) {
            for (Map.Entry<String, Collection<Object>> entry : context.build().asMap().entrySet()) {
                for (Object value : entry.getValue()) {
                    builder.addQueryParameter(entry.getKey(), value == null ? EMPTY : value.toString());
                }
            }
        }
        return builder.build().toString();
    }

    protected APIException getAPIException(Response response) {
        String message = response.message();
        if (Objects.nonNull(response.body())) {
            try {
                APIExceptionMessage exceptionMessage = fromJson(Objects.requireNonNull(response.body()).string(),
                        TypeReferenceFactory.getTypeRef(APIExceptionMessage.class));
                message = exceptionMessage.getMessage();
            } catch (IOException e) {
                return new APIException(response.code(), "Response has no body", e);
            } catch (APIException e) {
                return e;
            }
        }
        return new APIException(response.code(), message);
    }

    @SuppressWarnings("all")
    private OkHttpClient.Builder configureToIgnoreCertificate(OkHttpClient.Builder builder) {
        LOGGER.warn("Ignore Ssl Certificate");
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create a ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            LOGGER.warn("Exception while configuring IgnoreSslCertificate", e);
        }
        return builder;
    }

    private static boolean isArray(Object o) {
        return o != null && o.getClass().isArray();
    }

    private static boolean isCollection(Object o) {
        return o != null && Collection.class.isAssignableFrom(o.getClass());
    }
}
