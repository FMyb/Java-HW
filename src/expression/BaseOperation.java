package expression;


import expression.exceptions.DBZEEException;
import expression.exceptions.OverflowEEException;

import java.util.function.BinaryOperator;

public abstract class BaseOperation extends AbstractExpression implements Operation {
    private final String operation;
    private final BinaryOperator<Integer> operatorLong;
    private final BinaryOperator<Double> operatorDouble;

    public BaseOperation(final MyExpression firstArgument, final MyExpression secondArgument, final String operation,
                         final BinaryOperator<Integer> operatorLong, final BinaryOperator<Double> operatorDouble) {
        super(firstArgument, secondArgument);
        this.operation = operation;
        this.operatorLong = operatorLong;
        this.operatorDouble = operatorDouble;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(firstArgument.toString());
        sb.append(' ');
        sb.append(operation);
        sb.append(' ');
        sb.append(secondArgument.toString());
        sb.append(')');
        return sb.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (operation.equals("*") || operation.equals("/")) {
            check(sb, firstArgument, Position.FIRST);
            sb.append(' ');
            sb.append(operation);
            sb.append(' ');
            if (operation.equals("/") && (secondArgument.getClass() == Multiply.class || secondArgument.getClass() == Add.class || secondArgument.getClass() == Subtract.class)) {
                sb.append('(');
                sb.append(secondArgument.toMiniString());
                sb.append(')');
            } else {
                check(sb, secondArgument, Position.SECOND);
            }
        } else {
            firstArgument.setV(false);
            secondArgument.setV(false);
            sb.append(firstArgument.toMiniString());
            sb.append(' ');
            sb.append(operation);
            sb.append(' ');
            if (operation.equals("-")) {
                check(sb, secondArgument, Position.SECOND);
            } else {
                sb.append(secondArgument.toMiniString());
            }
        }
        return sb.toString();
    }

    private void check(StringBuilder sb, MyExpression argument, Position pos) {
        if (argument.getClass() == Const.class || argument.getClass() == Variable.class
                || argument.getClass() == Multiply.class || argument.getClass() == Divide.class && pos == Position.FIRST) {
            argument.setV(false);
            sb.append(argument.toMiniString());
        } else {
            sb.append('(');
            sb.append(argument.toMiniString());
            sb.append(')');
        }
    }

    @Override
    public int evaluate(int x) {
        return operatorLong.apply(firstArgument.evaluate(x), secondArgument.evaluate(x));

    }

    @Override
    public double evaluate(double x) {
        return operatorDouble.apply(firstArgument.evaluate(x), secondArgument.evaluate(x));
    }

    public int calcwithcheckcorrect(int x, int y) throws OverflowEEException, DBZEEException {
        if (operation.equals("+")) {
            if (x > 0 && Integer.MAX_VALUE - x < y || y > 0 && Integer.MAX_VALUE - y < x) {
                throw new OverflowEEException("overflow");
            }
            if (x < 0 && Integer.MIN_VALUE - x > y || y < 0 && Integer.MIN_VALUE - y > x) {
                throw new OverflowEEException("overflow");
            }
            return x + y;
        }
        if (operation.equals("-")) {
            if (y < 0 && Integer.MAX_VALUE + y < x || y > 0 && Integer.MIN_VALUE + y > x) {
                throw new OverflowEEException("overflow");
            }
            return x - y;
        }
        if (operation.equals("*")) {
            if (1L * x * y > 1L* Integer.MAX_VALUE || 1L * x * y < 1L * Integer.MIN_VALUE) {
                throw new OverflowEEException("overflow");
            }
            return x * y;
        }
        if (operation.equals("/")) {
            if (x == Integer.MIN_VALUE && y == -1 || y == 0) {
                throw new OverflowEEException("overflow");
            }
            return x / y;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowEEException, DBZEEException {
        int calcFirst = firstArgument.evaluate(x, y, z);
        int calcSecond = secondArgument.evaluate(x, y, z);
        return calcwithcheckcorrect(calcFirst, calcSecond);
    }
}
