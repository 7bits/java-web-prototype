package it.sevenbits.project.application.web.controllers;

import it.sevenbits.project.application.web.utils.UrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Controller routing every role to its starting page
 * It's first controller after successful login
 */
@Controller
public class Dashboard {

    /** Url resolver for building application urls */
    @Autowired
    private UrlResolver urlResolver;

    /**
     * Map role1 to role1 start page, role2 to
     * role2 start page, otherwise redirect to login page
     * @param request    Http Request
     * @return redirect role1 to role1 start page, role2 to
     * role2 start page, otherwise redirect to login page with
     * appropriate locale
     */
    public ModelAndView loginPage(
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        Locale locale = RequestContextUtils.getLocale(request);
        ModelAndView mav = new ModelAndView("index.jade");

        return mav;
    }
}