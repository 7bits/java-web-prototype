package it.sevenbits.project.application.configs;

import it.sevenbits.project.application.web.filters.LocaleUrlFilter;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/*
 * need if spring security will used
 */
//import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * need if upload file feature will used
 */
//import org.springframework.web.multipart.support.MultipartFilter;
//import javax.servlet.MultipartConfigElement;

/**
 * Spring Context Config
 * Equals web.xml setup
 * Wired after security initializer, if it exists
 */
@Order(2)
public class ContextConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    /** Logger */
    private final Logger log = Logger.getLogger(ContextConfig.class.getName());

    /**
     * All configs except web setup
     * @return All Configs
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
         return new Class<?>[] {
//                 MyBatisConfig.class
                 ConfigurationComponentScan.class
                 /******************************************/
                 /*   Should be enabled if security need   */
                 /******************************************/
                 //SecurityConfig.class
         };
    }

    /**
     * Web Configuration setup
     * @return Web Config
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
                WebMvcConfig.class
        };
    }

    /**
     * Root Mapping
     * @return root mapping for servlets
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    /**
     * Filters setup
     * @return filters
     */
    @Override
    protected Filter[] getServletFilters() {
        LocaleUrlFilter localeUrlFilter = new LocaleUrlFilter();
        return new Filter[]{
                localeUrlFilter,
                /**
                 * need if upload file feature will used
                 */
//                new MultipartFilter(),
                new HiddenHttpMethodFilter()
        };
    }

    /**
     * Dispatcher Servlet setup
     * @param servletContext    Servlet Context
     */
    @Override
    protected void registerDispatcherServlet(final ServletContext servletContext) {
        super.registerDispatcherServlet(servletContext);

        /* need if spring security will used */
//        servletContext.addListener(new HttpSessionEventPublisher());
    }

    /**
     * Custom Registration
     * Used to initialize Multipart Config (needed for upload in forms)
     * @param registration    Registration
     */
    @Override
    protected void customizeRegistration(final ServletRegistration.Dynamic registration) {

        /**
         * need if upload file feature will used
         */
//        MultipartConfigElement multipartConfigElement =
//                new MultipartConfigElement("");
//        registration.setMultipartConfig(multipartConfigElement);
    }
}