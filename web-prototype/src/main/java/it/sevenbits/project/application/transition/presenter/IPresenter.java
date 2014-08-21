package it.sevenbits.project.application.transition.presenter;

import java.util.Locale;
import java.util.Map;

/**
 * Interface for Presenter with successPresent, errorPresent and exceptionPresent methods
 * @param <Model> a model class for present
 * @param <View> a view class for present
 */
public interface IPresenter<Model, View> {

    /**
     * Presents success view model
     * @param model    Input model
     * @param locale   Locale
     * @return presented localized view model
     */
    View successPresent(Model model, Locale locale);

    /**
     * Presents error view model
     * @param locale   Locale
     * @return presented localized view model
     */
    View errorPresent(Locale locale);

    /**
     * Localizes errors map
     * @param errors    Map with errors <field, key>
     * @param locale    Locale
     * @return localized error map
     */
    Map<String, String> localizeErrors(Map<String, String> errors, Locale locale);

    /**
     * Presents exception
     * @param exception    Exception
     * @param locale       Locale
     * @return View model for exception case
     */
    View exceptionPresent(Exception exception, Locale locale);
}
