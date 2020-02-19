package expression.parser;

import expression.TripleExpression;
import expression.Variable;
import expression.exceptions.DBZEEException;
import expression.exceptions.OverflowEEException;

/**
 * @author Yaroslav Ilin
 */
public class CheckedNegate implements TripleExpression {
    Variable y;

    public CheckedNegate(Variable y) {
        this.y = y;
    }


    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException {
        if (y == Integer.MIN_VALUE) {
            throw new OverflowEEException("overflow");
        }
        return -y;
    }
}
