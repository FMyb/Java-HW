package expression;

import java.math.BigInteger;
import java.util.Objects;

public class Variable<T extends Number> implements MyExpression<T> {
    private final String var;
    private boolean f = true;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(int x) {
        return x;
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

    private <T extends Number> Number cast(int x, String mode) {
        switch (mode) {
            case "u":
            case "i":
                return (Integer) x;
            case "d":
                return Double.valueOf(String.valueOf(x));
            case "bi":
                return new BigInteger(String.valueOf(x));
            case "l":
                return Long.valueOf(String.valueOf(x));
            case "s":
//                if ((int) x <= 32767 && (int) x >= -32768) {
//                    return Short.valueOf(String.valueOf(x));
//                }
                return (short) x;
        }
        return null;
    }

    @Override
    public T calc(T x, T y, T z) {
        switch (var) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (var) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new IllegalArgumentException();
        }
    }
}
