package expression.generic;

import java.util.function.BinaryOperator;

/**
 * @author Yaroslav Ilin
 */

public class DoubleCalculator extends AbstractCalculator<Double> {
    @Override
    public BinaryOperator<Double> add() {
        return Double::sum;
    }

    @Override
    public BinaryOperator<Double> sub() {
        return (x, y) -> x - y;
    }

    @Override
    public BinaryOperator<Double> mul() {
        return (x, y) -> x * y;
    }

    @Override
    public BinaryOperator<Double> div() {
        return (x, y) -> x / y;
    }

    @Override
    public BinaryOperator<Double> max() {
        return Double::max;
    }

    @Override
    public BinaryOperator<Double> min() {
        return Double::min;
    }

    @Override
    public Double count(Double value) {
        return (double) Long.bitCount(Double.doubleToLongBits(value));
    }

    @Override
    public Double cnst(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public Double cnst(int value) {
        return Double.parseDouble(String.valueOf(value));
    }
}
