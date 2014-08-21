package it.sevenbits.project.application.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Session utilities
 */
public class SessionUtils {

    /**
     * Resolve flash message from session and nullify it in session
     * @param request    Http Request
     * @return flash message
     */
    public String getFlashMessage(final HttpServletRequest request) {
        Object message = request.getSession().getAttribute("flashMessage");
        request.getSession().setAttribute("flashMessage", null);
        if (message instanceof String) {
            return (String) message;
        }
        return null;
    }

    /**
     * Sets flash message and stores it in session
     * @param request         Http Request
     * @param flashMessage    Message
     */
    public void setFlashMessage(final HttpServletRequest request, final String flashMessage) {
        request.getSession().setAttribute("flashMessage", flashMessage);
    }
}
