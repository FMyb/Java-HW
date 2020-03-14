package expression.generic;

import expression.MyExpression;
import expression.exceptions.IllegalArgumentException;
import expression.exceptions.IllegalNumberException;
import expression.exceptions.IllegalOperationException;
import expression.exceptions.IllegalSymbolException;
import expression.parser.ExpressionParser;

/**
 * @author Yaroslav Ilin
 */

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws IllegalSymbolException, IllegalOperationException, IllegalArgumentException, IllegalNumberException {
        Calculator<? extends Number> calculator;
        switch (mode) {
            case "i":
                calculator = new IntegerCalculator();
                break;
            case "d":
                calculator = new DoubleCalculator();
                break;
            case "bi":
                calculator = new BigIntegerCalculator();
                break;
            case "l":
                calculator = new LongCalculator();
                break;
            case "s":
                calculator = new ShortCalculator();
                break;
            case "u":
                calculator = new UnckeckedIntegerCalculator();
                break;
            default:
                calculator = new IntegerCalculator();
        }
        return calc(expression, calculator, x1, x2, y1, y2, z1, z2);
    }

    private <T extends Number> Object[][][] calc(String expression, Calculator<T> calculator, int x1, int x2, int y1, int y2, int z1, int z2) throws IllegalSymbolException, IllegalOperationException, IllegalArgumentException, IllegalNumberException {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        ExpressionParser<T> parser = calculator.getParserType();
        MyExpression<T> parseExpression = parser.parse(expression, calculator);
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        result[i][j][k] = parseExpression.calc(calculator.cnst(x1 + i), calculator.cnst(y1 + j), calculator.cnst(z1 + k));
                    } catch (Exception e) {
                        result[i][j][k] = null;
                    }
                }
            }
        }
        return result;
    }
}
