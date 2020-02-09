package expression;

import expression.exception.EEException;

import java.util.Objects;

public class Const implements MyExpression {
    private int valueInt;
    private boolean isValueInt = false;
    private double valueDouble;
    private boolean isValueDouble;
    private boolean f = true;

    public Const(int valueInt) {
        this.valueInt = valueInt;
        this.isValueInt = true;
    }

    public Const(double valueDouble) {
        this.valueDouble = valueDouble;
        this.isValueDouble = true;
    }

    @Override
    public int evaluate(int x) {
        return valueInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return valueInt == aConst.valueInt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    @Override
    public void setV(boolean f) {
        this.f = f;
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
        return isValueInt ? String.valueOf(valueInt) : String.valueOf(valueDouble);
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public double evaluate(double x) {
        if (isValueDouble){
            return valueDouble;
        } else {
            return valueInt;
        }
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return valueInt;
    }
}
