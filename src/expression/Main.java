package expression;

import expression.exception.OverflowEEException;
import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        String s = "2/x";
//        (x << (1593491994 - ((-1 * x) - ((x / 1866500826) * -2034520121))))*(x << (1593491994 - ((-1 * x) - ((x / 1866500826) * -2034520121))))
        ExpressionParser ep = new ExpressionParser();
        TripleExpression temp2 = ep.parse(s);
        try {
            System.out.println(temp2.evaluate(0, 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
