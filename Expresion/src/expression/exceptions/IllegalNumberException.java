package expression.exceptions;

/**
 * @author Yaroslav Ilin
 */
public class IllegalNumberException extends PEException {
    public IllegalNumberException(String message, String prefix) {
        super(message + " an error was found in: \"" + prefix + "\"");
    }
}
