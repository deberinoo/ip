package juno;

/**
 * Custom exception class for handling errors in the Juno application.
 * This exception is thrown when there is an issue specific to the application's logic,
 * such as invalid commands or file errors.
 */
public class JunoException extends Exception {
    /**
     * Constructs a new JunoException with the specified detail message.
     * 
     * @param message The detail message that explains the reason for the exception.
     */
    public JunoException(String message) {
        super(message);
    }
}