package it.sevenbits.project.application.web.util;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom failure login handler
 */
public class FailLoginHandler implements AuthenticationFailureHandler {

    /**
     * Fail Handler, may be used for custom actions after failure auth
     * @param request           Http Request
     * @param response          Http Response
     * @param e                 Failure reason exception
     * @throws IOException if user information cannot be obtained
     * @throws ServletException if servlet fails
     */
    @Override
    public void onAuthenticationFailure(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final AuthenticationException e
    ) throws IOException, ServletException {

    }
}
