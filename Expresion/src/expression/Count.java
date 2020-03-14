package expression;

import expression.exceptions.DBZEEException;
import expression.exceptions.OverflowEEException;
import expression.generic.Calculator;

import java.math.BigInteger;

/**
 * @author Yaroslav Ilin
 */

public class Count<T extends Number> implements MyExpression<T> {
    private final MyExpression<T> value;
    private final Calculator<T> calculator;
    boolean f;

    public Count(MyExpression<T> value, Calculator<T> calculator) {
        this.value = value;
        this.calculator = calculator;
    }

    @Override
    public void setV(boolean f) {
        this.f = f;
    }

    @Override
    public T calc(T x, T y, T z) {
        return calculator.count(value.calc(x, y, z));
    }

    @Override
    public double evaluate(double x) {
        return 0;
    }

    @Override
    public int evaluate(int x) {
        return 0;
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException, DBZEEException {
        return Integer.bitCount((int) value.evaluate(x, y, z));
    }
}
