package expression.parser;

import expression.TripleExpression;
import expression.Variable;
import expression.exceptions.OverflowEEException;

/**
 * @author Yaroslav Ilin
 */
public class CheckedAdd implements TripleExpression {
    private Variable x;
    private Variable y;

    public CheckedAdd(Variable x, Variable y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException {
        if (x > 0 && Integer.MAX_VALUE - x < y || y > 0 && Integer.MAX_VALUE - y < x) {
            throw new OverflowEEException("overflow");
        }
        if (x < 0 && Integer.MIN_VALUE - x > y || y < 0 && Integer.MIN_VALUE - y > x) {
            throw new OverflowEEException("overflow");
        }
        return x + y;
    }
}
