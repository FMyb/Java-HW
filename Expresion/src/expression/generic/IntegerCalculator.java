package expression.generic;

import expression.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author Yaroslav Ilin
 */

public class IntegerCalculator extends AbstractCalculator<Integer> {
    @Override
    public BinaryOperator<Integer> add() {
        return Add::checkCorrect;
    }

    @Override
    public BinaryOperator<Integer> sub() {
        return Subtract::checkCorrect;
    }

    @Override
    public BinaryOperator<Integer> mul() {
        return Multiply::checkCorrect;
    }

    @Override
    public BinaryOperator<Integer> div() {
        return Divide::checkCorrect;
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
