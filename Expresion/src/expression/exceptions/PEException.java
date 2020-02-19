package expression.exceptions;

/**
 * @author Yaroslav Ilin
 */
public class PEException extends Exception {
    public PEException(String message) {
        super(message);
    }

    public PEException (String message, Throwable cause) {
        super (message, cause);
    }
}
