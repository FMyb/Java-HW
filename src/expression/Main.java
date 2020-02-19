package expression;

import expression.exceptions.*;
import expression.exceptions.IllegalArgumentException;
import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws IllegalSymbolException, OverflowEEException, DBZEEException, IllegalArgumentException, IllegalOperationException, IllegalNumberException {
        String s = "x * y *";
//        (x << (1593491994 - ((-1 * x) - ((x / 1866500826) * -2034520121))))*(x << (1593491994 - ((-1 * x) - ((x / 1866500826) * -2034520121))))
        ExpressionParser ep = new ExpressionParser();
        System.out.println(ep.parse(s).toString());
    }
}
