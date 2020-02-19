package expression;

import expression.exceptions.OverflowEEException;

public class Multiply extends BaseOperation {

    public Multiply(MyExpression firstArgument, MyExpression secondArgument) {
        super(firstArgument, secondArgument, "*", (x, y) -> (x * y), (x, y) -> (x * y));
    }

    static int checkCorrect(int x, int y) throws OverflowEEException {
        if (1L * x * y > 1L* Integer.MAX_VALUE || 1L * x * y < 1L * Integer.MIN_VALUE) {
            throw new OverflowEEException("overflow");
        }
        return x * y;
    }
}
