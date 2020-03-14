package expression;

import java.util.Objects;

public abstract class AbstractExpression<T extends Number> implements MyExpression<T> {
    final protected MyExpression<T> firstArgument;
    final protected MyExpression<T> secondArgument;
    protected boolean f = true;

    public AbstractExpression(final MyExpression<T> firstArgument, final MyExpression<T> secondArgument) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }

    @Override
    public void setV(boolean f) {
        this.f = f;
    }

//    @Override
    private boolean equals(MyExpression<T> other) {
        return this.toString().equals(other.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        return this.toString().equals(o.toString());
        AbstractExpression<T> that = (AbstractExpression<T>) o;
        return this.equals(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }


}
