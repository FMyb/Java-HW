package expression.generic;

import java.util.function.BinaryOperator;

/**
 * @author Yaroslav Ilin
 */

public class LongCalculator extends AbstractCalculator<Long> {
    @Override
    public BinaryOperator<Long> add() {
        return Long::sum;
    }

    @Override
    public BinaryOperator<Long> sub() {
        return (x, y) -> x - y;
    }

    @Override
    public BinaryOperator<Long> mul() {
        return (x, y) -> x * y;
    }

    @Override
    public BinaryOperator<Long> div() {
        return (x, y) -> x / y;
    }

    @Override
    public BinaryOperator<Long> max() {
        return Long::max;
    }

    @Override
    public BinaryOperator<Long> min() {
        return Long::min;
    }

    @Override
    public Long count(Long value) {
        return (long) Long.bitCount(value);
    }

    @Override
    public Long cnst(String value) {
        return Long.parseLong(value);
    }

    @Override
    public Long cnst(int value) {
        return (long) value;
    }
}

