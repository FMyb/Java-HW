package expression.parser;

import expression.TripleExpression;
import expression.exceptions.IllegalSymbolException;

/**
 * @author Yaroslav Ilin
 */

public interface Parser {
    TripleExpression parse(String expression) throws IllegalSymbolException;
}
