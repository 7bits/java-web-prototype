package it.sevenbits.project.application.repository.exception;

/**
 * General purpose exception for Repository Layer
 */
public class RepositoryException extends Exception {

    /** Unique serial id */
    private static final long serialVersionUID = 8558300234466130073L;

    /**
     * Exception with message only
     * @param message    String message
     */
    public RepositoryException(final String message) {
        super(message);
    }

    /**
     * Exception with message and cause of Exception
     * @param message    String message
     * @param e          Exception
     */
    public RepositoryException(final String message, final Exception e) {
        super(message, e);
    }
}
