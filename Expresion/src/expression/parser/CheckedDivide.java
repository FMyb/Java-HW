package expression.parser;

import expression.TripleExpression;
import expression.Variable;
import expression.exceptions.OverflowEEException;

/**
 * @author Yaroslav Ilin
 */
public class CheckedDivide implements TripleExpression {
    private Variable x;
    private Variable y;

    public CheckedDivide(Variable x, Variable y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException {
        if (x == Integer.MIN_VALUE && y == -1 || y == 0) {
            throw new OverflowEEException("overflow");
        }
        return x / y;
    }
}
