package expression;

import java.util.function.BinaryOperator;

public interface MyExpression<T extends Number> extends Expression, TripleExpression, DoubleExpression {
    String toString();
    boolean equals(Object other);
    void setV(boolean f);
    T calc(T x, T y, T z);
}
