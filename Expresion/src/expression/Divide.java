package expression;

import expression.exceptions.DBZEEException;
import expression.exceptions.OverflowEEException;
import expression.generic.Calculator;

import java.math.BigInteger;

public class Divide<T extends Number> extends BaseOperation<T> {
    public Divide(MyExpression<T> firstArgument, MyExpression<T> secondArgument, Calculator<T> calculator) {
        super(firstArgument, secondArgument, "/", calculator.div());
    }

    public static int checkCorrect(int x, int y) throws OverflowEEException, DBZEEException {
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowEEException("overflow");
        }
        if (y == 0) {
            throw new DBZEEException("division by zero");
        }
        return x / y;
    }
}
