package com.puppetlabs.http.client;

import javax.net.ssl.SSLContext;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * This class represents is a wrapper around a number of options for use in
 * making requests with the simple request functions contained in the Sync class.
 * It is a combination of the options from ClientOptions and RequestOptions.
 *
 * @author Chris Price
 * @author Preben Ingvaldsen
 * @see com.puppetlabs.http.client.ClientOptions#ClientOptions(javax.net.ssl.SSLContext, String, String, String, String[], String[], boolean, boolean, boolean)
 * @see com.puppetlabs.http.client.RequestOptions#RequestOptions(java.net.URI, java.util.Map, Object, boolean, ResponseBodyType)
 */
public class SimpleRequestOptions {
    private URI uri;
    private Map<String, String> headers;
    private SSLContext sslContext;
    private String sslCert;
    private String sslKey;
    private String sslCaCert;
    private String[] sslProtocols;
    private String[] sslCipherSuites;
    private boolean insecure = false;
    private Object body;
    private boolean decompressBody = true;
    private ResponseBodyType as = ResponseBodyType.STREAM;
    private boolean forceRedirects = false;
    private boolean followRedirects = true;

    public SimpleRequestOptions (String url) throws URISyntaxException { this.uri = new URI(url); }
    public SimpleRequestOptions(URI uri) {
        this.uri = uri;
    }

    public URI getUri() {
        return uri;
    }
    public SimpleRequestOptions setUri(URI uri) {
        this.uri = uri;
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
    public SimpleRequestOptions setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public SSLContext getSslContext() {
        return sslContext;
    }
    public SimpleRequestOptions setSslContext(SSLContext sslContext) {
        this.sslContext = sslContext;
        return this;
    }

    public String getSslCert() {
        return sslCert;
    }
    public SimpleRequestOptions setSslCert(String sslCert) {
        this.sslCert = sslCert;
        return this;
    }

    public String getSslKey() {
        return sslKey;
    }
    public SimpleRequestOptions setSslKey(String sslKey) {
        this.sslKey = sslKey;
        return this;
    }

    public String getSslCaCert() {
        return sslCaCert;
    }
    public SimpleRequestOptions setSslCaCert(String sslCaCert) {
        this.sslCaCert = sslCaCert;
        return this;
    }

    public String[] getSslProtocols() {
        return sslProtocols;
    }
    public SimpleRequestOptions setSslProtocols(String[] sslProtocols) {
        this.sslProtocols = sslProtocols;
        return this;
    }

    public String[] getSslCipherSuites() {
        return sslCipherSuites;
    }
    public SimpleRequestOptions setSslCipherSuites(String[] sslCipherSuites) {
        this.sslCipherSuites = sslCipherSuites;
        return this;
    }

    public boolean getInsecure() {
        return insecure;
    }
    public SimpleRequestOptions setInsecure(boolean insecure) {
        this.insecure = insecure;
        return this;
    }

    public Object getBody() {
        return body;
    }
    public SimpleRequestOptions setBody(Object body) {
        this.body = body;
        return this;
    }

    public boolean getDecompressBody() { return decompressBody; }
    public SimpleRequestOptions setDecompressBody(boolean decompressBody) {
        this.decompressBody = decompressBody;
        return this;
    }

    public ResponseBodyType getAs() {
        return as;
    }
    public SimpleRequestOptions setAs(ResponseBodyType as) {
        this.as = as;
        return this;
    }

    public boolean getForceRedirects() { return forceRedirects; }
    public SimpleRequestOptions setForceRedirects(boolean forceRedirects) {
        this.forceRedirects = forceRedirects;
        return this;
    }

    public boolean getFollowRedirects() { return followRedirects; }
    public SimpleRequestOptions setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
        return this;
    }
}