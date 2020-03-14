package expression.generic;

import expression.parser.ExpressionParser;

import java.math.BigInteger;
import java.util.function.BinaryOperator;

/**
 * @author Yaroslav Ilin
 */

public class BigIntegerCalculator extends AbstractCalculator<BigInteger> {
    @Override
    public BinaryOperator<BigInteger> add() {
        return BigInteger::add;
    }

    @Override
    public BinaryOperator<BigInteger> sub() {
        return BigInteger::subtract;
    }

    @Override
    public BinaryOperator<BigInteger> mul() {
        return BigInteger::multiply;
    }

    @Override
    public BinaryOperator<BigInteger> div() {
        return BigInteger::divide;
    }

    @Override
    public BinaryOperator<BigInteger> max() {
        return BigInteger::max;
    }

    @Override
    public BinaryOperator<BigInteger> min() {
        return BigInteger::min;
    }

    @Override
    public BigInteger count(BigInteger value) {
        return new BigInteger(String.valueOf(value.bitCount()));
    }

    @Override
    public BigInteger cnst(String value) {
        return new BigInteger(value);
    }
    @Override
    public BigInteger cnst(int value) {
        return new BigInteger(String.valueOf(value));
    }
}
