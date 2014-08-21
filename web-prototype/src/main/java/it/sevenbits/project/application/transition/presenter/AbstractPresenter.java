package it.sevenbits.project.application.transition.presenter;

import it.sevenbits.project.application.transition.util.MessageResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Abstract Presenter with successPresent, errorPresent and exceptionPresent methods
 * Extended class should be annotated with {@link org.springframework.stereotype.Service}
 * @param <Model>
 * @param <View>
 */
public abstract class AbstractPresenter<Model, View> implements IPresenter<Model, View> {

    /** Message Resolver */
    @Autowired
    private MessageResolver messageResolver;

    /**
     * Presents success view model
     * @param model    Input model
     * @param locale   Locale
     * @return presented localized view model
     */
    public View successPresent(final Model model, final Locale locale) {
        return null;
    }

    /**
     * Presents error view model
     * @param locale   Locale
     * @return presented localized view model
     */
    public View errorPresent(final Locale locale) {
        return null;
    }

    /**
     * Presents exception
     * @param exception    Exception
     * @param locale       Locale
     * @return View model for exception case
     */
    public View exceptionPresent(final Exception exception, final Locale locale) {
        return null;
    }

    /**
     * Localizes errors map
     * @param errors    Map with errors <field, key>
     * @param locale    Locale
     * @return localized error map
     */
    public Map<String, String> localizeErrors(final Map<String, String> errors, final Locale locale) {
        Map<String, String> errorsLocalized = new HashMap<>();
        if (errors != null) {
            for (String field: errors.keySet()) {
                errorsLocalized.put(field, messageResolver.message(errors.get(field), locale));
            }
        }

        return errorsLocalized;
    }
}
