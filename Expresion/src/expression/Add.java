package expression;

import expression.exceptions.OverflowEEException;
import expression.generic.Calculator;

import java.math.BigInteger;

public class Add<T extends Number> extends BaseOperation<T> {
    public Add(MyExpression<T> firstArgument, MyExpression<T> secondArgument, Calculator<T> calculator) {
        super(firstArgument, secondArgument, "+", calculator.add());
    }

    public static int checkCorrect(int x, int y) {
        if (x > 0 && Integer.MAX_VALUE - x < y || y > 0 && Integer.MAX_VALUE - y < x) {
            throw new OverflowEEException("overflow");
        }
        if (x < 0 && Integer.MIN_VALUE - x > y || y < 0 && Integer.MIN_VALUE - y > x) {
            throw new OverflowEEException("overflow");
        }
        return x + y;
    }
}
