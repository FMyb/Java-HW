package expression.exceptions;

/**
 * @author Yaroslav Ilin
 */
public class IllegalArgumentException extends PEException {
    public IllegalArgumentException(String message, String prefix) {
        super(message + " an error was found in: \"" + prefix + "\"");
    }
}
