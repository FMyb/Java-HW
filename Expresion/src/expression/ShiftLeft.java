package expression;

/**
 * @author Yaroslav Ilin
 */
public class ShiftLeft extends BaseOperation{
    public ShiftLeft(MyExpression firstArgument, MyExpression secondArgument) {
        super(firstArgument, secondArgument, "<<", (x, y) -> (x << y), (x, y) -> (x * y));
    }
}
