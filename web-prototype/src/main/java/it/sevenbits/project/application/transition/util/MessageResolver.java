package it.sevenbits.project.application.transition.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.HashMap;
import java.util.Locale;

/**
 * Message Resolver for all localisation formatting purposes
 * for use inside presenters
 */
public class MessageResolver {

    /** Spring Message Source */
    @Autowired
    private MessageSource messageSource;

    public MessageResolver() {
    }

    /**
     * Resolves message by code
     * @param code      Code to resolve
     * @param locale    Locale to use
     * @return localised message
     */
    public String message(final String code, final Locale locale) {
        try {
            return messageSource.getMessage(code, null, locale);
        } catch (Exception e) {

            return code;
        }
    }

    /**
     * Builds localised message using {@link MessageBuilder} as template builder
     * @param code       Message code
     * @param hashMap    Map with parameters used by template builder
     * @param locale     Locale
     * @return localised message
     */
    public String buildMessage(final String code, final HashMap<String, String> hashMap, final Locale locale) {
        try {
            String template = messageSource.getMessage(code, null, locale);
            return MessageBuilder.createMessage(template, hashMap);
        } catch (Exception e) {

            return code;
        }
    }
}
