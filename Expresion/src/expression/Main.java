package expression;

import expression.exceptions.*;
import expression.exceptions.IllegalArgumentException;
import expression.generic.Calculator;
import expression.generic.DoubleCalculator;
import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws IllegalSymbolException, OverflowEEException, DBZEEException, IllegalArgumentException, IllegalOperationException, IllegalNumberException {
        String s = "wrong input";
        System.out.println("Выражение для разбора: " + s);
//        (x << (1593491994 - ((-1 * x) - ((x / 1866500826) * -2034520121))))*(x << (1593491994 - ((-1 * x) - ((x / 1866500826) * -2034520121))))
        Calculator<Double> calc = new DoubleCalculator();
        ExpressionParser<Double> ep = calc.getParserType();
        MyExpression<Double> result = ep.parse(s, calc);
        System.out.println("Результат разбора: " + result.toString());
        System.out.println("Результат разбора в короткой форме: " + result.toMiniString());
        System.out.println("Результат вычислений в Double при x = -5.0, y = -18.0, z = -19.0: " + result.calc(-5.0, -18.0, -19.0));
    }
}
