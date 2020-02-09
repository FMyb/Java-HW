package expression;

import expression.exception.EEException;

public interface MyExpression extends Expression, TripleExpression, DoubleExpression {
    String toString();
    boolean equals(Object other);
    void setV(boolean f);
}
