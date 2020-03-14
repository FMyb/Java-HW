package expression.exceptions;

/**
 * @author Yaroslav Ilin
 */
public class IllegalOperationException extends PEException {
    public IllegalOperationException(String message, String prefix) {
        super(message + " an error was found in: \"" + prefix + "\"");
    }
}
