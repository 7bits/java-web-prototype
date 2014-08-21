package it.sevenbits.project.application.config.util;

import it.sevenbits.project.application.web.util.SessionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Session utils configuration
 */
@Configuration
public class SessionUtilsConfig {

    @Bean
    public SessionUtils sessionUtils() {
        return new SessionUtils();
    }
}