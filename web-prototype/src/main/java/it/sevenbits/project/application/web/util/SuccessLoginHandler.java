package it.sevenbits.project.application.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom login handler
 */
public class SuccessLoginHandler implements AuthenticationSuccessHandler {

//    private UserRepository userRepository;
    @Autowired
    private UrlResolver urlResolver;
    @Autowired
    private CookieLocaleResolver cookieLocaleResolver;


//    public SuccessLoginHandler(final UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    /**
     * Login Handler, saves user to session.
     * Also, you may put timeout connection parameter, security context
     * and other attributes to session;
     * send redirect to different urls for different users after login etc
     * @param request           Http Request
     * @param response          Http Response
     * @param authentication    Authentication
     * @throws java.io.IOException if user information cannot be obtained
     * @throws javax.servlet.ServletException if servlet fails
     */
    @Override
    public void onAuthenticationSuccess(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Authentication authentication
    ) throws IOException, ServletException {

    }
}
