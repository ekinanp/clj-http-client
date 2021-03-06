package com.puppetlabs.http.client.impl;

import com.codahale.metrics.MetricRegistry;
import com.puppetlabs.http.client.HttpClientException;
import com.puppetlabs.http.client.Response;
import com.puppetlabs.http.client.RequestOptions;
import com.puppetlabs.http.client.HttpMethod;
import com.puppetlabs.http.client.SyncHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PersistentSyncHttpClient implements SyncHttpClient {
    private CloseableHttpAsyncClient client;
    private MetricRegistry metricRegistry;
    private String metricNamespace;
    private boolean enableURLMetrics;
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistentSyncHttpClient.class);

    public PersistentSyncHttpClient(CloseableHttpAsyncClient client,
                                    MetricRegistry metricRegistry,
                                    String metricNamespace,
                                    boolean enableURLMetrics) {
        this.client = client;
        this.metricRegistry = metricRegistry;
        this.metricNamespace = metricNamespace;
        this.enableURLMetrics = enableURLMetrics;
    }

    public MetricRegistry getMetricRegistry() {
        return metricRegistry;
    }

    public String getMetricNamespace() {
        return metricNamespace;
    }

    public Response request(RequestOptions requestOptions, HttpMethod method) {
        final Promise<Response> promise = new Promise<>();
        final JavaResponseDeliveryDelegate responseDelivery = new JavaResponseDeliveryDelegate(promise);
        JavaClient.requestWithClient(requestOptions, method, null, client,
                responseDelivery, metricRegistry, metricNamespace, enableURLMetrics);
        final Response response;
        try {
            response = promise.deref();
            if (response.getError() != null) {
                LOGGER.warn("Error executing http request", response.getError());
                throw new HttpClientException("Error executing http request", response.getError());
            }
        } catch (InterruptedException e) {
            LOGGER.warn("Error while waiting for http response", e);
            throw new HttpClientException("Error while waiting for http response", e);
        }
        return response;
    }

    public void close() throws IOException {
        client.close();
    }

    public Response get(String url) throws URISyntaxException {
        return get(new URI(url));
    }
    public Response get(URI uri) {
        return get(new RequestOptions(uri));
    }
    public Response get(RequestOptions requestOptions) {
        return request(requestOptions, HttpMethod.GET);
    }

    public Response head(String url) throws URISyntaxException {
        return head(new URI(url));
    }
    public Response head(URI uri) {
        return head(new RequestOptions(uri));
    }
    public Response head(RequestOptions requestOptions) {
        return request(requestOptions, HttpMethod.HEAD);
    }

    public Response post(String url) throws URISyntaxException {
        return post(new URI(url));
    }
    public Response post(URI uri) {
        return post(new RequestOptions(uri));
    }
    public Response post(RequestOptions requestOptions) {
        return request(requestOptions, HttpMethod.POST);
    }

    public Response put(String url) throws URISyntaxException {
        return put(new URI(url));
    }
    public Response put(URI uri) {
        return put(new RequestOptions(uri));
    }
    public Response put(RequestOptions requestOptions) {
        return request(requestOptions, HttpMethod.PUT);
    }

    public Response delete(String url) throws URISyntaxException {
        return delete(new URI(url));
    }
    public Response delete(URI uri) {
        return delete(new RequestOptions(uri));
    }
    public Response delete(RequestOptions requestOptions) {
        return request(requestOptions, HttpMethod.DELETE);
    }

    public Response trace(String url) throws URISyntaxException {
        return trace(new URI(url));
    }
    public Response trace(URI uri) {
        return trace(new RequestOptions(uri));
    }
    public Response trace(RequestOptions requestOptions) {
        return request(requestOptions, HttpMethod.TRACE);
    }

    public Response options(String url) throws URISyntaxException {
        return options(new URI(url));
    }
    public Response options(URI uri) {
        return options(new RequestOptions(uri));
    }
    public Response options(RequestOptions requestOptions) {
        return request(requestOptions, HttpMethod.OPTIONS);
    }

    public Response patch(String url) throws URISyntaxException {
        return patch(new URI(url));
    }
    public Response patch(URI uri) {
        return patch(new RequestOptions(uri));
    }
    public Response patch(RequestOptions requestOptions) {
        return request(requestOptions, HttpMethod.PATCH);
    }
}
