/*
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

    private int power(int x, int y) throws OverflowEEException {
        if (y == 0) {
            return 1;
        }
        if (y == 1) {
            return x;
        }
        if (y % 2 == 0) {
            int ans = power(x, y / 2);
            return Multiply.checkCorrect(ans, ans);
        } else {
            int ans = power(x, y / 2);
            return Multiply.checkCorrect(x, Multiply.checkCorrect(ans, ans));
        }
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException, DBZEEException {
        int ans = 1;
        int first = firstArgument.evaluate(x, y, z);
        int second = secondArgument.evaluate(x, y, z);
        if (second < 0 || (first == 0 && second == 0)) {
            throw new IllegalArgumentException();
        }
        return power(first, second);
    }
}
*/
