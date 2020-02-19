package expression.parser;

import expression.TripleExpression;
import expression.Variable;
import expression.exceptions.OverflowEEException;

/**
 * @author Yaroslav Ilin
 */
public class CheckedSubtract implements TripleExpression {
    private Variable x;
    private Variable y;

    public CheckedSubtract(Variable x, Variable y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException {
        if (y < 0 && Integer.MAX_VALUE + y < x || y > 0 && Integer.MIN_VALUE + y > x) {
            throw new OverflowEEException("overflow");
        }
        return x - y;
    }
}