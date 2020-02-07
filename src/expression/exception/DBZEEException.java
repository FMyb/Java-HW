package expression.exception;

/**
 * @author Yaroslav Ilin
 */
public class DBZEEException extends EEException {
    private final int divident;

    public DBZEEException(int divident) {
        super("Devision by zero");
        this.divident = divident;

    }

    public int getDivident() {
        return divident;
    }
}
