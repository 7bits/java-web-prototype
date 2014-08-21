package it.sevenbits.project.application.web.helper;

import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.http.HttpServletRequest;

/**
 * CSRF Token Resolver for
 * resolving csrf tokens in templates
 */
public class CsrfResolver {

    /** CSRF Token object */
    private CsrfToken token;

    /**
     * Constructor
     * We should have request to get CSRF Token for it
     * @param request    Http Request
     */
    public CsrfResolver(final HttpServletRequest request) {

        token = (CsrfToken) request.getAttribute("_csrf");
    }

    /**
     * Get name of CSRF Token parameter
     * @return name of CSRF Token
     */
    public String getParameterName() {

        return token != null ? token.getParameterName() : null;
    }

    /**
     * Get CSRF Token for current request
     * @return CSRF Token
     */
    public String getToken() {

        return token != null ? token.getToken() : null;
    }

    /**
     * Set csrf token
     * @param token    Csrf token
     */
    public void setToken(final CsrfToken token) {
        this.token = token;
    }
}
