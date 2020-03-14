package expression;

import java.math.BigInteger;
import java.util.Objects;

public class Const<T extends Number> implements MyExpression<T> {
    private final Number value;
    private boolean f = true;

    public Const(Number value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return (int) value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number aConst = (Number) o;
        return value.equals(o);
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
    public T calc(T x, T y, T z) {
        return (T) value;
    }


//    @Override
//    public String toString() {
//        return valueInt >= 0 ? String.valueOf(valueInt) : '(' + String.valueOf(valueInt) + ')';
//    }

//    @Override
//    public String toMiniString() {
//        return !f && valueInt < 0 ? '(' + String.valueOf(valueInt) + ')' : String.valueOf(valueInt);
//    }
    @Override
    public String toString(){
        return String.valueOf(value);
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public double evaluate(double x) {
        return (double) value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) value;
    }
}
