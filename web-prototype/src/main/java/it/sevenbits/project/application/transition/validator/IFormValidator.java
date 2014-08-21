package it.sevenbits.project.application.transition.validator;

import it.sevenbits.project.application.transition.form.AbstractForm;

import java.util.Map;

/**
 * Validator Interface
 * @param <Form> incoming form for check
 */
public interface IFormValidator<Form extends AbstractForm> {

    /**
     * Validates form
     * @param form Input form
     * @return map with errors in form in <field, key> style
     */
    Map<String, String> validate(final Form form);
}
