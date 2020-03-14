package expression.generic;

import java.util.function.BinaryOperator;

/**
 * @author Yaroslav Ilin
 */

public class UnckeckedIntegerCalculator extends AbstractCalculator<Integer> {
    @Override
    public BinaryOperator<Integer> add() {
        return Integer::sum;
    }

    @Override
    public BinaryOperator<Integer> sub() {
        return (x, y) -> (x - y);
    }

    @Override
    public BinaryOperator<Integer> mul() {
        return (x, y) -> (x * y);
    }

    @Override
    public BinaryOperator<Integer> div() {
        return (x, y) -> (x / y);
    }

    @Override
    public BinaryOperator<Integer> max() {
        return Integer::max;
    }

    @Override
    public BinaryOperator<Integer> min() {
        return Integer::min;
    }

    @Override
    public Integer count(Integer value) {
        return Integer.bitCount(value);
    }

    @Override
    public Integer cnst(String value) {
        return Integer.parseInt(value);
    }

    @Override
    public Integer cnst(int value) {
        return value;
    }
}
