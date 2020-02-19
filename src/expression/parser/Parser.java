package expression.parser;

import expression.TripleExpression;
import expression.exceptions.IllegalArgumentException;
import expression.exceptions.IllegalNumberException;
import expression.exceptions.IllegalOperationException;
import expression.exceptions.IllegalSymbolException;

/**
 * @author Yaroslav Ilin
 */

public interface Parser {
    TripleExpression parse(String expression) throws IllegalSymbolException, IllegalOperationException, IllegalArgumentException, IllegalNumberException;
}
