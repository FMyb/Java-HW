/*
package expression;

import expression.exceptions.DBZEEException;
import expression.exceptions.OverflowEEException;

import java.util.Objects;

public class Pow2 implements MyExpression {
    private final MyExpression value;
    boolean f;

    public Pow2(MyExpression value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return 1 << value.evaluate(x);
    }

    //    @Override
    private boolean equals(MyExpression other) {
        return this.toString().equals(other.toString());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return this.equals(variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    @Override
    public void setV(boolean f) {
        this.f = f;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("2**(");
        sb.append(value.toString());
        sb.append(')');
        return sb.toString();
    }

    @Override
    public String toMiniString() {
        return value.toMiniString();
    }

    @Override
    public double evaluate(double x) {
        return evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException, DBZEEException {
        int k = value.evaluate(x, y, z);
        if (k >= 32) {
            throw new IllegalArgumentException();
        }
        if (k < 0) throw new IllegalArgumentException();
        return 1 << k;
    }
}
*/
