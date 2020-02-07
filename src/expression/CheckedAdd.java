package expression;

import expression.exception.OverflowEEException;

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
        if (Integer.MAX_VALUE - x < y) {
            throw new OverflowEEException(new Add(this.x, this.y), x, y, z);
        }
        return x + y;
    }
}
