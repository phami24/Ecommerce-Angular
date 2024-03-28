package phamiz.ecommerce.backend.exception;

/**
 * Custom exception class for user-related errors.
 */
public class UserException extends Exception {

    /**
     * Constructs a new UserException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public UserException(String message) {
        super(message);
    }
}
