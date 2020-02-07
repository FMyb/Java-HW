package expression;

public class Subtract extends BaseOperation {
    public Subtract(MyExpression firstArgument, MyExpression secondArgument) {
        super(firstArgument, secondArgument, "-", (x, y) -> (x - y), (x, y) -> (x - y));
    }
}
