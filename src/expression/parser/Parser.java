package expression.parser;

import expression.TripleExpression;

/**
 * @author Yaroslav Ilin
 */

public interface Parser {
    TripleExpression parse(String expression);
}
