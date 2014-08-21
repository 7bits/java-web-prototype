package it.sevenbits.project.application.web.filter;

import it.sevenbits.project.application.config.localization.LocaleResolverConfig;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Locale url filter - filters out part of URL,
 * recognizes locale from it, adds it to session attributes
 * Works with /language/ and /language/country/ 2 letters codes
 * immediately after application name in URL
 */
public class LocaleUrlFilter implements Filter {

    /** Pattern to recognize (1) - language, (2) - country, (3) - rest of url */
    private static final Pattern LOCALE_PATTERN = Pattern.compile("^/([a-zA-Z]{2})(?:_([a-zA-Z]{2}))?(/.*)?");
    /** Language group */
    private static final int GROUP_LANGUAGE = 1;
    /** Country group */
    private static final int GROUP_COUNTRY = 2;
    /** URI Fragment group */
    private static final int GROUP_FRAGMENT = 3;

    /**
     * Filter destroy method
     */
    public void destroy() {
    }

    /**
     * Locale filter logic:
     * Filter removes locale from uri to pass locale-free uri further to Filter Chain
     * @param servletRequest     Http request
     * @param servletResponse    Http response
     * @param filterChain        Filter chain
     */
    public void doFilter(
            final ServletRequest servletRequest,
            final ServletResponse servletResponse,
            final FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI().substring(request.getContextPath().length());
        Matcher matcher = LOCALE_PATTERN.matcher(url);
        Locale locale = Locale.getDefault();
        if (matcher.matches()) {
            String language = matcher.group(GROUP_LANGUAGE);
            String country = matcher.group(GROUP_COUNTRY);
            if (country != null && !country.isEmpty()) {
                locale = new Locale(language.toLowerCase(), country.toLowerCase());
            } else {
                locale = new Locale(language.toLowerCase());
            }
            request.setAttribute(CookieLocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME, locale);
            Cookie cookie = WebUtils.getCookie(request, LocaleResolverConfig.COOKIE_NAME);
            if (cookie != null) {
                cookie.setValue(locale.toString());
            } else {
                cookie = new Cookie(LocaleResolverConfig.COOKIE_NAME, locale.toString());
            }
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
            String restOfUrl;
            if (matcher.group(GROUP_FRAGMENT) == null) {
                restOfUrl = "/";
            } else {
                restOfUrl = matcher.group(GROUP_FRAGMENT);
            }
            request.getRequestDispatcher(restOfUrl)
                    .forward(servletRequest, servletResponse);
        } else {
            Cookie cookie = WebUtils.getCookie(request, LocaleResolverConfig.COOKIE_NAME);
            if (cookie != null) {
                locale = parseCookie(cookie, "_");
            } else {
                Locale clientLocale = request.getLocale();
                if (clientLocale != null) {
                    cookie = new Cookie(LocaleResolverConfig.COOKIE_NAME, clientLocale.toString());
                } else {
                    cookie = new Cookie(LocaleResolverConfig.COOKIE_NAME, locale.toString());
                }
            }
            request.setAttribute(CookieLocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME, locale);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
            filterChain.doFilter(request, response);
        }
    }

    /**
     * parse cookie to locale
     * @param cookie given cookie
     * @param separator separator symbol for separate language and country
     * @return locale
     */
    private Locale parseCookie(final Cookie cookie, final String separator) {
        if (cookie != null) {
            String[] cookieLocaleParts = cookie.getValue().toLowerCase().split(separator);
            if (cookieLocaleParts.length > 1) {
                return new Locale(cookieLocaleParts[0], cookieLocaleParts[1]);
            } else {
                return new Locale(cookieLocaleParts[0]);
            }
        }

        return null;
    }

    /**
     * Filter initialise method
     * @param arg0 incoming argument
     * @throws ServletException
     */
    public void init(final FilterConfig arg0) throws ServletException {}
}
