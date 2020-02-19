package expression.exception;

/**
 * @author Yaroslav Ilin
 */

public class EEException extends Exception {
    public EEException(String message) {
        super(message);
    }

    public EEException (String message, Throwable cause) {
        super (message, cause);
    }
}
