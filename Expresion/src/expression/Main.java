package expression;

import expression.exceptions.*;
import expression.exceptions.IllegalArgumentException;
import expression.generic.Calculator;
import expression.generic.DoubleCalculator;
import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws IllegalSymbolException, OverflowEEException, DBZEEException, IllegalArgumentException, IllegalOperationException, IllegalNumberException {
        String s = "10";
//        (x << (1593491994 - ((-1 * x) - ((x / 1866500826) * -2034520121))))*(x << (1593491994 - ((-1 * x) - ((x / 1866500826) * -2034520121))))
        Calculator<Double> calc = new DoubleCalculator();
        ExpressionParser<Double> ep = calc.getParserType();
        System.out.println(ep.parse(s, calc).calc(-5.0, -18.0, -19.0));
    }
}
