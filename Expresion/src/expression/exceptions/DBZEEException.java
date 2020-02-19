package expression.exceptions;

/**
 * @author Yaroslav Ilin
 */
public class DBZEEException extends EEException {

    public DBZEEException(String message) {
        super("division by zero: " + message);
    }
}
