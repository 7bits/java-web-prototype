package it.sevenbits.project.application.web.utils;

import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/**
 *  All url and uri related methods
 *  using to build urls and uris to navigate across whole web application
 *  Uris and urls are made including locale
 */
public class UrlResolver {

    /** Application protocol, http or smth. else */
    private String protocol;
    /** Application server name */
    private String server;
    /** Application hosted port */
    private String port;
    /** Application name, as part of url */
    private String applicationName;
    /** Resource location url */
    private String resourcesLocation;
    /** Default string size, used for string builder */
    private static final Integer DEFAULT_STRING_SIZE = 255;

    public UrlResolver() {
    }

    /**
     * Provides resources url
     * @param uri    Uri of resource
     * @return resources url
     */
    public String getResourceUrl(final String uri) {
        StringBuilder resourceUrl = new StringBuilder(DEFAULT_STRING_SIZE);
        resourceUrl.append(this.resourcesLocation);
        if (uri != null) {
            if (!uri.startsWith("/")) {
                resourceUrl.append("/");
            }
        }
        resourceUrl.append(uri);
        return resourceUrl.toString();
    }

    /**
     * Provide full application url with locale
     * @return url of our app
     */
    public StringBuilder getApplicationUrl() {
        StringBuilder url = new StringBuilder(DEFAULT_STRING_SIZE);
        url.append(protocol);
        url.append("://");
        url.append(server);
        if (!StringUtils.isBlank(port)) {
            url.append(":");
            url.append(port);
        }
        if (!StringUtils.isBlank(applicationName)) {
            url.append("/");
            url.append(applicationName);
        }
        return url;
    }

    /**
     * Provides full url with protocol, port, locale etc
     * @param uri    Uri path
     * @return full url with path
     */
    public String getFullUrl(final String uri) {
        StringBuilder builtUrl = new StringBuilder(DEFAULT_STRING_SIZE);
        builtUrl.append(getApplicationUrl());
        if (uri != null) {
            if (!uri.startsWith("/")) {
                builtUrl.append("/");
            }
        }
        builtUrl.append(uri);
        return builtUrl.toString();
    }

    /**
     * Generates part of url with locale
     * @param locale    Locale
     * @return url part with locale like "/en/us"
     */
    private StringBuilder makeLocaleUrlPart(final Locale locale) {
        StringBuilder urlPart = new StringBuilder(DEFAULT_STRING_SIZE);
        if (locale != null) {
            String country = locale.getCountry().toLowerCase();
            String language = locale.getLanguage().toLowerCase();
            if (!language.isEmpty()) {
                urlPart.append("/");
                urlPart.append(language);
                if (!country.isEmpty()) {
                    urlPart.append("/");
                    urlPart.append(country);
                }
            }
        }
        return urlPart;
    }

    /**
     * Build uri for redirect
     * @param uri       Page uri
     * @param locale    Locale
     * @return complete uri with redirect:
     */
    public String buildRedirectUri(final String uri, final Locale locale) {
        StringBuilder redirectUri = new StringBuilder(DEFAULT_STRING_SIZE);
        redirectUri.append("redirect:");
        redirectUri.append(makeLocaleUrlPart(locale));
        if (uri != null) {
            if (!uri.startsWith("/")) {
                redirectUri.append("/");
            }
        }
        redirectUri.append(uri);
        return redirectUri.toString();
    }

    /**
     * Build uri suitable for web links (with application name in path)
     * @param uri       Page uri
     * @param locale    Locale
     * @return complete uri with redirect:
     */
    public String buildFullUri(final String uri, final Locale locale) {
        StringBuilder fullUri = new StringBuilder(DEFAULT_STRING_SIZE);
        if (!applicationName.isEmpty()) {
            fullUri.append("/");
            fullUri.append(applicationName);
        }
        fullUri.append(makeLocaleUrlPart(locale));
        if (uri != null) {
            if (!uri.startsWith("/")) {
                fullUri.append("/");
            }
        }
        fullUri.append(uri);
        return fullUri.toString();
    }

    /** Returns application protocol */
    public String getProtocol() {
        return protocol;
    }

    /** Sets application protocol */
    public void setProtocol(final String protocol) {
        this.protocol = protocol;
    }

    /** Returns server name */
    public String getServer() {
        return server;
    }

    /** Sets server name */
    public void setServer(final String server) {
        this.server = server;
    }

    /** Returns application port */
    public String getPort() {
        return port;
    }

    /** Sets application port */
    public void setPort(final String port) {
        this.port = port;
    }

    /** Returns application name */
    public String getApplicationName() {
        return applicationName;
    }

    /** Sets application name */
    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }

    /** Returns resource location url */
    public String getResourcesLocation() {
        return resourcesLocation;
    }

    /** Sets resource location url */
    public void setResourcesLocation(final String resourcesLocation) {
        this.resourcesLocation = resourcesLocation;
    }
}
