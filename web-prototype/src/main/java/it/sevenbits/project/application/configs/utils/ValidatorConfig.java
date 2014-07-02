package it.sevenbits.project.application.configs.utils;

import it.sevenbits.modules.validator.sevices.validators.PrimitiveFieldValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Validator Configuration
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public PrimitiveFieldValidator primitiveFieldValidator() {

        return new PrimitiveFieldValidator();
    }
}