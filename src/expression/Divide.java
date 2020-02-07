package expression;

public class Divide extends BaseOperation {
    public Divide(MyExpression firstArgument, MyExpression secondArgument) {
        super(firstArgument, secondArgument, "/", (x, y) -> x / y, (x, y) -> x / y);
    }
}
