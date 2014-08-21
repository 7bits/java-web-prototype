package it.sevenbits.project.application.transition.executor;

import it.sevenbits.project.application.model.AbstractEntity;
import it.sevenbits.project.application.service.exception.ServiceException;
import it.sevenbits.project.application.transition.form.AbstractForm;
import it.sevenbits.project.application.transition.presenter.IPresenter;
import it.sevenbits.project.application.transition.validator.IFormValidator;
import it.sevenbits.project.application.transition.util.MessageResolver;
import it.sevenbits.project.application.transition.view.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.Map;

/**
 * Abstract executor
 * @param <Form> a form class for action in sequence
 * @param <View> a view class for action in sequence
 * @param <Model> a model class for action in sequence
 */
public abstract class AbstractExecutor<Form extends AbstractForm, View extends AbstractView, Model extends AbstractEntity> {

    /** Message Resolver */
    @Autowired
    private MessageResolver messageResolver;
    /** Validator - if null then executor will be skip validation step */
    private IFormValidator validator;
    /** Presenter */
    private IPresenter presenter;

    /**
     * Runs validator
     * @param form    Form to run validator on
     * @return Errors map <field, key>
     * @throws ServiceException
     */
    private Map<String, String> validator(final Form form) throws ServiceException {
        if (validator == null || form == null) {
            return null;
        }

        return validator.validate(form);
    }

    /**
     * Runs all service tasks
     * @param form    Input form object
     * @return result of service(s) calls
     * @throws ServiceException in all other cases
     */
    protected Model service(
            final Form form
    ) throws ServiceException {
        return null;
    }

    /**
     * Executes following tasks:
     * 1) Validates form
     * 2) If errors found, presents them and returns
     * 3) If no errors found, calls service and presents its result
     * @param form      Input form
     * @param locale    User locale
     * @return localized view
     * @throws ServiceException in all other cases
     */
    public View execute(
            final Form form,
            final Locale locale
    ) throws ServiceException {
        Map<String, String> errors = validator(form);
        if (errors.size() == 0) {
            try {
                Model model = service(form);
                return (View) presenter.successPresent(model, locale);
            } catch (Exception e) {
                return (View) presenter.exceptionPresent(e, locale);
            }
        } else {
            View view = (View) presenter.errorPresent(locale);
            view.setErrors(presenter.localizeErrors(errors, locale));
            return view;
        }
    }

    /**
     * Sets validator. You should override it to have working executor
     * Validator should be type of {@link it.sevenbits.project.application.transition.validator.IFormValidator}
     * @param validator    Validator
     */
    protected void setValidator(final IFormValidator validator) {
        this.validator = validator;
    }

    /**
     * Sets presenter. You should setup it to have working executor
     * Presenter should be type of {@link it.sevenbits.project.application.transition.presenter.IPresenter}
     * @param presenter    Presenter
     */
    public void setPresenter(final IPresenter presenter) {
        this.presenter = presenter;
    }

}
