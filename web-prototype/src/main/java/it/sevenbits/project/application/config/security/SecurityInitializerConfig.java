package it.sevenbits.project.application.config.security;

import it.sevenbits.project.application.web.filter.LocaleUrlFilter;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletContext;

/**
 * Spring Security Initialization
 * Runs before all other configurations
 */
@Order(1)
public class SecurityInitializerConfig extends AbstractSecurityWebApplicationInitializer {

    /**
     * Before Spring Security handler
     * We should include all filters there which should be
     * initialised before Spring Security
     * @param servletContext    Servlet context
     */
    @Override
    protected void beforeSpringSecurityFilterChain(final ServletContext servletContext) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        insertFilters(servletContext, characterEncodingFilter);
    }

    /**
     * After Spring Security Handler
     * We should include there filters that should be
     * initialised after spring security     *
     * @param servletContext    Servlet Context
     */
    @Override
    protected void afterSpringSecurityFilterChain(final ServletContext servletContext) {
        LocaleUrlFilter localeUrlFilter = new LocaleUrlFilter();
        insertFilters(servletContext, localeUrlFilter);
    }
}
