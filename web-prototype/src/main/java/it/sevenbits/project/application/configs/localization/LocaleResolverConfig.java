package it.sevenbits.project.application.configs.localization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

/**
 * Locale resolver config
 */
@Configuration
public class LocaleResolverConfig {

    /** Name of locale cookie */
    public static final String COOKIE_NAME = "LOCALE";

    /**
     * Configure Locale Resolver with country and language codes,
     * for which our application have translations
     * @return Locale Resolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        cookieLocaleResolver.setCookieMaxAge(-1);
        cookieLocaleResolver.setCookieName(COOKIE_NAME);

        return cookieLocaleResolver;
    }
}
