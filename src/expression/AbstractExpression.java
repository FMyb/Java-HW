package expression;

import java.util.Objects;

public abstract class AbstractExpression implements MyExpression {
    final protected MyExpression firstArgument;
    final protected MyExpression secondArgument;
    protected boolean f = true;

    public AbstractExpression(final MyExpression firstArgument, final MyExpression secondArgument) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }

    @Override
    public void setV(boolean f) {
        this.f = f;
    }

//    @Override
    private boolean equals(MyExpression other) {
        return this.toString().equals(other.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        return this.toString().equals(o.toString());
        AbstractExpression that = (AbstractExpression) o;
        return this.equals(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }
}
