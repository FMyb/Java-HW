package expression;

import expression.exceptions.DBZEEException;
import expression.exceptions.OverflowEEException;
import expression.parser.CheckedMultiply;

import java.util.Objects;

public class Pow extends BaseOperation {
    boolean f;

    public Pow(MyExpression firstArgument, MyExpression secondArgument) {
        super(firstArgument, secondArgument, "**", Integer::sum, Double::sum);
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException, DBZEEException {
        int ans = 1;
        int first = firstArgument.evaluate(x, y, z);
        int second = secondArgument.evaluate(x, y, z);
        if (second < 0 || (first == 0 && second == 0)) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < second; i++) {
            if (1L * ans * first > 1L* Integer.MAX_VALUE || 1L * ans * first < 1L * Integer.MIN_VALUE) {
                throw new OverflowEEException("overflow");
            }
            ans *= first;
        }
        return ans;
    }
}
