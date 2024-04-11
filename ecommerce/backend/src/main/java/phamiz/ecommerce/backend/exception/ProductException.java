package phamiz.ecommerce.backend.exception;

public class ProductException extends Exception {

    /**
     * Constructs a new ProductException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public ProductException(String message) {
        super(message);
    }
}