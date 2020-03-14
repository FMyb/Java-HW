package expression.exceptions;

/**
 * @author Yaroslav Ilin
 */

public class EEException extends RuntimeException {
    public EEException(String message) {
        super(message);
    }

    public EEException (String message, Throwable cause) {
        super (message, cause);
    }
}
