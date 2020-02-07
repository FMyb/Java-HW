package expression;

public class Multiply extends BaseOperation {

    public Multiply(MyExpression firstArgument, MyExpression secondArgument) {
        super(firstArgument, secondArgument, "*", (x, y) -> (x * y), (x, y) -> (x * y));
    }
}
