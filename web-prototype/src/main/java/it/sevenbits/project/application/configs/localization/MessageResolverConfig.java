package it.sevenbits.project.application.configs.localization;

import it.sevenbits.project.application.transitions.utils.MessageResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Message resolver configuration
 */
@Configuration
public class MessageResolverConfig {

    /**
     * Create Bean for Message Resolver
     * (used in transition layer and view templates for localisation messages)
     * @return Message Resolver
     */
    @Bean
    public MessageResolver messageResolver() {
        return new MessageResolver();
    }
}