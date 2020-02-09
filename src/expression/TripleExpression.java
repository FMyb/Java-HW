package expression;

import expression.exception.DBZEEException;
import expression.exception.OverflowEEException;

public interface TripleExpression extends ToMiniString {
    int evaluate(int x, int y, int z) throws OverflowEEException, DBZEEException;
}
