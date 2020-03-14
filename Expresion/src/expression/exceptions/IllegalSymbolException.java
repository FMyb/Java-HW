package expression.exceptions;

/**
 * @author Yaroslav Ilin
 */
public class IllegalSymbolException extends PEException {
    public IllegalSymbolException(String message, String prefix) {
        super(message + " an error was found in: \"" + prefix + "\"");
    }
}
