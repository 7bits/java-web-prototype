package it.sevenbits.project.application.configs.localization;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.inject.Inject;

/**
 * Message Source Configuration
 */
@Configuration
@PropertySource("file:${project.config}/configurations/application/server.properties")
public class MessageSourceConfig {

    @Inject
    private org.springframework.core.env.Environment environment;

    /**
     * Configure Spring Message Source
     * @return Message Source
     */
    @Bean
    public MessageSource messageSource() {
        String messageLocation = environment.getProperty("server.message-location");
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                messageLocation + "/validation",
                messageLocation + "/presenter",
                messageLocation + "/pages"
        );
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }
}