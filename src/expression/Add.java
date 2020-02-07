package expression;

public class Add extends BaseOperation {
    public Add(MyExpression firstArgument, MyExpression secondArgument) {
        super(firstArgument, secondArgument, "+", Integer::sum, Double::sum);
    }
}
