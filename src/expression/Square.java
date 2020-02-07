package expression;

import expression.exception.OverflowEEException;

import java.util.Objects;

public class Square implements MyExpression {
    private final MyExpression value;
    private boolean f;

    public Square(MyExpression value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        int ans = value.evaluate(x);
        return ans * ans;
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
        String s = value.toString();
        sb.append(s);
        sb.append('*');
        sb.append(s);
        return sb.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        String s = value.toMiniString();
        sb.append(s);
        sb.append('*');
        sb.append(s);
        return sb.toString();
    }

    @Override
    public double evaluate(double x) {
        double ans = value.evaluate(x);
        return ans * ans;
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException {
        int ans = value.evaluate(x, y, z);
        return ans * ans;
    }
}
