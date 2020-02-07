package expression.exception;

import expression.Expression;

/**
 * @author Yaroslav Ilin
 */
public class OverflowEEException extends EEException {

    public OverflowEEException(Expression e, int x, int y, int z) {
        super("Overflow " + e.toString() + " with x = " + x + ", y = " + y + ", z = " + z);
    }
}
