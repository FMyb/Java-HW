/*
package expression;

import expression.exceptions.DBZEEException;
import expression.exceptions.OverflowEEException;

import java.util.Objects;

public class Log extends BaseOperation{
    boolean f;

    public Log(MyExpression firstArgument, MyExpression secondArgument) {
        super(firstArgument, secondArgument, "//", Integer::sum, Double::sum);
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException, DBZEEException {
        int ans = 0;
        int first = firstArgument.evaluate(x, y, z);
        int second = secondArgument.evaluate(x, y, z);
        if (second <= 0 || first <= 0 || second == 1) {
            throw new IllegalArgumentException();
        }
        int k = 1;
        try {
            for (int i = 0; Multiply.checkCorrect(k, second) <= first && i < 31; i++) {
                k *= second;
                ans = i + 1;
            }
        } catch (OverflowEEException e) {
            return ans;
        }
        return ans;
    }
}
*/
