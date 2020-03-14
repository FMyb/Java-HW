package expression;

import expression.exceptions.OverflowEEException;
import expression.generic.Calculator;

import java.math.BigInteger;

public class Multiply<T extends Number> extends BaseOperation<T> {

    public Multiply(MyExpression<T> firstArgument, MyExpression<T> secondArgument, Calculator<T> calculator) {
        super(firstArgument, secondArgument, "*", calculator.mul());
    }

    private static void throwOverflow() throws OverflowEEException {
        throw new OverflowEEException("overflow");
    }

    public static int checkCorrect(int x, int y) throws OverflowEEException {
        if (x == Integer.MIN_VALUE && y == -1) {
            throwOverflow();
        }
        if (y == Integer.MIN_VALUE && x == -1) {
            throwOverflow();
        }
        if (x == -1 || y == -1) {
            return x * y;
        }
        if (x >= 0) {
            if (y >= 0) {
                if (y != 0 && Integer.MAX_VALUE / y < x) {
                    throwOverflow();
                } else if (x != 0 && Integer.MAX_VALUE / x < y) {
                    throwOverflow();
                }
            } else {
                if (Integer.MIN_VALUE / y < x) {
                    throwOverflow();
                } else if (x != 0 && Integer.MIN_VALUE / x > y) {
                    throwOverflow();
                }
            }
        } else {
            if (y >= 0) {
                if (y != 0 && Integer.MIN_VALUE / y > x) {
                    throwOverflow();
                } else if (Integer.MIN_VALUE / x < y) {
                    throwOverflow();
                }
            } else {
                if (Integer.MAX_VALUE / x > y) {
                    throwOverflow();
                } else if (Integer.MAX_VALUE / y > x) {
                    throwOverflow();
                }
            }
        }
        return x * y;
    }
}
