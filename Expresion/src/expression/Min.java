package expression;

import expression.generic.Calculator;

import java.math.BigInteger;

/**
 * @author Yaroslav Ilin
 */

public class Min<T extends Number> extends BaseOperation<T> {
    public Min(MyExpression<T> firstArgument, MyExpression<T> secondArgument, Calculator<T> calculator) {
        super(firstArgument, secondArgument, "min", calculator.min());
    }
}
