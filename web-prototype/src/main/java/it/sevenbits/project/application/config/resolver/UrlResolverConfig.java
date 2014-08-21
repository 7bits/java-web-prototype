package it.sevenbits.project.application.config.resolver;

import it.sevenbits.project.application.web.util.UrlResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.inject.Inject;

/**
 * Url resolver configuration
 */
@Configuration
@PropertySource("file:${project.config}/configurations/application/server.properties")
public class UrlResolverConfig {

    @Inject
    private org.springframework.core.env.Environment environment;

    /**
     * Create and configure bean for Url Resolver,
     * used to generate all uris
     * and urls across whole application
     * @return Url resolver singleton
     */
    @Bean
    public UrlResolver urlResolver() {
        UrlResolver urlResolver = new UrlResolver();
        urlResolver.setProtocol(environment.getProperty("server.protocol"));
        urlResolver.setServer(environment.getProperty("server.server"));
        urlResolver.setPort(environment.getProperty("server.port"));
        urlResolver.setApplicationName(environment.getProperty("server.application-name"));
        urlResolver.setResourcesLocation(environment.getProperty("server.resources-location"));

        return urlResolver;
    }
}