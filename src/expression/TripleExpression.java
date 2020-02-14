package expression;

import expression.exceptions.DBZEEException;
import expression.exceptions.OverflowEEException;

public interface TripleExpression extends ToMiniString {
    int evaluate(int x, int y, int z) throws OverflowEEException, DBZEEException;
}
