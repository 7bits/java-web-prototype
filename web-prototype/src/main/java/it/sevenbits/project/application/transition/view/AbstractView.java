package it.sevenbits.project.application.transition.view;

import java.io.Serializable;
import java.util.Map;

/**
 * Base class for persistent views with success, error and errors fields
 */
public class AbstractView implements Serializable {

    /** Unique generated serial id */
    private static final long serialVersionUID = 4177202071953526234L;
    /** Success message */
    private String successMessage;
    /** Error message */
    private String errorMessage;
    /** Map with specific errors */
    private Map<String, String> errors;

    /** Returns success message */
    public String getSuccessMessage() {
        return successMessage;
    }

    /** Sets success message */
    public void setSuccessMessage(final String successMessage) {
        this.successMessage = successMessage;
    }

    /** Returns error message */
    public String getErrorMessage() {
        return errorMessage;
    }

    /** Sets error message */
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /** Returns errors map */
    public Map<String, String> getErrors() {
        return errors;
    }

    /** Sets errors map */
    public void setErrors(final Map<String, String> errors) {
        this.errors = errors;
    }
}
