package it.sevenbits.project.application.web.utils;

import it.sevenbits.project.application.transitions.utils.MessageResolver;
import it.sevenbits.project.application.web.helpers.CsrfResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Main interceptor is used to add all needed Helpers
 * and models to template engine processor
 */
public class HandlerInterceptor extends HandlerInterceptorAdapter {

    /** Message resolver for resolving localised messages */
    private MessageResolver messageResolver;
    /** Url Resolver, building application urls */
    private UrlResolver urlResolver;
    /** Session utils for resolving session stored items */
    private SessionUtils sessionUtils;

    /** Domain name variable for send to view templates: consist from protocol, server_name, port and application name */
    static final String DOMAIN_NAME_VARIABLE = "domain";
    /** Name of object which will contain locale */
    static final String MODEL_LOCALE_NAME = "locale";
    /** Message resolver Helper name */
    static final String MODEL_MESSAGE_RESOLVER_NAME = "fmt";
    /** CSRF resolver Helper name */
    static final String CSRF_RESOLVER_NAME = "csrf";
    /** Current uri, needed for language switching */
    static final String CURRENT_URI = "uri";

    public HandlerInterceptor() {
    }

    /**
     * Handler is requested after each controller, before model and view render
     * Adds all necessary data to model and view, which we may need to use
     * inside template later.
     * @param request     Http Request
     * @param response    Http Response
     * @param handler     Handler
     * @param mav         Model and View
     * @throws Exception in very rare circumstances like IO errors
     */
    @Override
    public void postHandle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler,
            final ModelAndView mav
    ) throws Exception {
        if (mav != null) {
            // Adding language and country code to model
            Locale locale = RequestContextUtils.getLocale(request);
            mav.addObject(MODEL_LOCALE_NAME, locale);

            // Message resolver added
            mav.addObject(MODEL_MESSAGE_RESOLVER_NAME, messageResolver);
            // Url resolver added
            mav.addObject(DOMAIN_NAME_VARIABLE, urlResolver);

            // CSRF Token Resolver
            mav.addObject(CSRF_RESOLVER_NAME, new CsrfResolver(request));

//            // User resolver
//            mav.addObject(SECURITY_SERVICE_NAME, new UserResolver());

            // Url resolver
            mav.addObject(CURRENT_URI, request.getServletPath());

            // Flash message
            String flashMessage = sessionUtils.getFlashMessage(request);
            mav.addObject("flashMessage", flashMessage);
        }
    }

    /** Message resolver for resolving localised messages */
    public void setMessageResolver(final MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    /** Url Resolver, building application urls */
    public void setUrlResolver(final UrlResolver urlResolver) {
        this.urlResolver = urlResolver;
    }

    /** Sets Session Utils */
    public void setSessionUtils(final SessionUtils sessionUtils) {
        this.sessionUtils = sessionUtils;
    }
}
