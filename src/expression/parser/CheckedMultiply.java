package expression.parser;

import expression.TripleExpression;
import expression.Variable;
import expression.exceptions.OverflowEEException;

/**
 * @author Yaroslav Ilin
 */
public class CheckedMultiply implements TripleExpression {
    private Variable x;
    private Variable y;

    public CheckedMultiply(Variable x, Variable y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException {
        if (1L * x * y > 1L* Integer.MAX_VALUE || 1L * x * y < 1L * Integer.MIN_VALUE) {
            throw new OverflowEEException("overflow");
        }
        return x * y;
    }
}
