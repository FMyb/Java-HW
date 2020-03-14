package expression.parser;

import expression.MyExpression;
import expression.TripleExpression;
import expression.exceptions.IllegalArgumentException;
import expression.exceptions.IllegalNumberException;
import expression.exceptions.IllegalOperationException;
import expression.exceptions.IllegalSymbolException;
import expression.generic.Calculator;

/**
 * @author Yaroslav Ilin
 */

public interface Parser<T extends Number> {
    TripleExpression parse(String expression) throws IllegalSymbolException, IllegalOperationException, IllegalArgumentException, IllegalNumberException;

    MyExpression<T> parse(String expression, Calculator<T> calculator) throws IllegalSymbolException, IllegalOperationException, IllegalArgumentException, IllegalNumberException;
}
