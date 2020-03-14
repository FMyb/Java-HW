package expression.generic;

import expression.parser.ExpressionParser;

import java.util.function.BinaryOperator;

/**
 * @author Yaroslav Ilin
 */

public abstract class AbstractCalculator<T extends Number> implements Calculator<T> {
    @Override
    public abstract BinaryOperator<T> add();

    @Override
    public abstract BinaryOperator<T> sub();

    @Override
    public abstract BinaryOperator<T> mul();

    @Override
    public abstract BinaryOperator<T> div();

    @Override
    public abstract BinaryOperator<T> max();

    @Override
    public abstract BinaryOperator<T> min();

    @Override
    public BinaryOperator<T> operation(String s) {
        switch (s){
            case "+":
                return add();
            case "-":
                return sub();
            case "*":
                return mul();
            case "/":
                return div();
            case "max":
                return max();
            case "min":
                return min();
        }
        return null;
    }

    @Override
    public ExpressionParser<T> getParserType() {
        return new ExpressionParser<>();
    }
}
