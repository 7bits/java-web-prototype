package it.sevenbits.project.application.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 * Multipart resolver configuration
 */
@Configuration
public class MultipartResolverConfig {

    /**
    * Create Bean for  Multi-part Resolver
    * (needed for upload files in forms)
    * @return Multi-part resolver
    */
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}