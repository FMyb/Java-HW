package expression;

import expression.generic.Calculator;

import java.math.BigInteger;
import java.util.function.BinaryOperator;

/**
 * @author Yaroslav Ilin
 */

public class Max<T extends Number> extends BaseOperation<T> {
    public Max(MyExpression<T> firstArgument, MyExpression<T> secondArgument, Calculator<T> calculator) {
        super(firstArgument, secondArgument, "max", calculator.max());
    }
}
