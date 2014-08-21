package it.sevenbits.project.application.transition.form;

import java.util.Locale;

/**
 * Base class for persistent incoming data from client with locale field
 */
public class AbstractForm {

    private Locale locale;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }
}
