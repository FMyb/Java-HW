package expression.generic;

import expression.MyExpression;
import expression.parser.ExpressionParser;

import java.util.function.BinaryOperator;

/**
 * @author Yaroslav Ilin
 */

public interface Calculator<T extends Number> {
    BinaryOperator<T> add();
    BinaryOperator<T> sub();
    BinaryOperator<T> mul();
    BinaryOperator<T> div();
    BinaryOperator<T> max();
    BinaryOperator<T> min();
    BinaryOperator<T> operation(String s);
    T count(T value);
    ExpressionParser<T> getParserType();
    T cnst(String value);
    T cnst(int value);
}
