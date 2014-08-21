package it.sevenbits.project.application.transition.util;

import java.util.HashMap;

/**
 * Text templates compiler
 */
public final class MessageBuilder {

    /** Template Variable insert begin */
    private static final String BEGIN_BROKE = "#{";
    /** Template Variable insert end */
    private static final String END_BROKE = "}";

    private MessageBuilder() {
    }

    /**
     * Message compiler from template, modifies #{key} entries to key values,
     * obtained from map object
     * @param template     Text with #{key} in text. Example: Hello #{name}!
     * @param hashMap      Key-value storage
     * @return compiled message
     */
    public static String createMessage(final String template, final HashMap<String, String> hashMap) {
        if (template == null || hashMap == null) {
            return "";
        }

        StringBuilder result = new StringBuilder(template);
        int beginIndex = 0;
        int endIndex = 0;
        int beginBrokeLen = BEGIN_BROKE.length();
        int endBrokeLen = END_BROKE.length();

        while ((beginIndex = result.indexOf(BEGIN_BROKE, beginIndex)) != -1) {
            endIndex = result.indexOf(END_BROKE, beginIndex);
            if (endIndex != -1) {
                String key = result.substring(beginIndex + beginBrokeLen, endIndex);
                String value = hashMap.get(key);
                if (value != null) {
                    result.replace(beginIndex, endIndex + endBrokeLen, value);
                }
            }
        }

        return result.toString();
    }
}

