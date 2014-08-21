package it.sevenbits.project.application.config.util;

import it.sevenbits.project.application.transition.util.MessageResolver;
import it.sevenbits.project.application.web.util.HandlerInterceptor;
import it.sevenbits.project.application.web.util.SessionUtils;
import it.sevenbits.project.application.web.util.UrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Handler interceptor Configuration
 */
@Configuration
public class HandlerInterceptorConfig {

    @Autowired
    private MessageResolver messageResolver;
    @Autowired
    private UrlResolver urlResolver;
    @Autowired
    private SessionUtils sessionUtils;

    /**
     * Main interceptor is used to add all needed Helpers
     * and models to template engine processor
     * Should be added as interceptor in WebMvcConfig
     */
    @Bean(name = "handlerInterceptor")
    public HandlerInterceptor handlerInterceptor() {
        HandlerInterceptor handlerInterceptor = new HandlerInterceptor();
        handlerInterceptor.setMessageResolver(this.messageResolver);
        handlerInterceptor.setUrlResolver(this.urlResolver);
        handlerInterceptor.setSessionUtils(this.sessionUtils);

        return handlerInterceptor;
    }
}