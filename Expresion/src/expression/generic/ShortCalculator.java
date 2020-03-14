package expression.generic;

import java.util.function.BinaryOperator;

/**
 * @author Yaroslav Ilin
 */

public class ShortCalculator extends AbstractCalculator<Short> {
    @Override
    public BinaryOperator<Short> add() {
        return (x, y) -> (short) (x + y);
    }

    @Override
    public BinaryOperator<Short> sub() {
        return (x, y) -> (short) (x - y);
    }

    @Override
    public BinaryOperator<Short> mul() {
        return (x, y) -> (short) (x * y);
    }

    @Override
    public BinaryOperator<Short> div() {
        return (x, y) -> (short) (x / y);
    }

    @Override
    public BinaryOperator<Short> max() {
        return (x, y) -> x > y ? x : y;
    }

    @Override
    public BinaryOperator<Short> min() {
        return (x, y) -> x < y ? x : y;
    }

    @Override
    public Short count(Short value) {
        return value < 0 ? (short) (Integer.bitCount(value) - 16)
                : (short) Integer.bitCount(value);
    }

    @Override
    public Short cnst(String value) {
        return Short.parseShort(value);
    }

    @Override
    public Short cnst(int value) {
        return (short) value;
    }
}
