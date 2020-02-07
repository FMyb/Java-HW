package expression.parser;

import expression.*;

import java.util.Map;

/**
 * @author Yaroslav Ilin
 */

public class ExpressionParser implements Parser {
    private int position = 0;
    private OperationType correctOperation = OperationType.NOTHING;
    private String s = "";
    private Map<Character, OperationType> convertOperation = Map.of(
            '+', OperationType.ADD,
            '-', OperationType.SUB,
            '*', OperationType.MUL,
            '/', OperationType.DIV,
            '(', OperationType.OPEN_BRACKET,
            ')', OperationType.CLOSE_BRACKET);

    private String nextLexem() {
        StringBuilder sb = new StringBuilder();
        while (notEnd() && s.charAt(position) == '-') {
            sb.append(s.charAt(position));
            position++;
        }
        while (notEnd() && !convertOperation.containsKey(s.charAt(position)) && !(s.charAt(position) == '<') && !(s.charAt(position) == '>')) {
            sb.append(s.charAt(position));
            position++;
        }
        return sb.toString();
    }

    private boolean notEnd() {
        return position != s.length();
    }

    private void skipSpace() {
        while (notEnd() && s.charAt(position) == ' ') {
            position++;
        }
    }

    private boolean nextString(String correct) {
        int i = 0;
        if (!notEnd()) {
            return false;
        }
        if (s.charAt(position) != correct.charAt(i)) {
            return false;
        }
        while (i < correct.length() && s.charAt(position) == correct.charAt(i)) {
            i++;
            position++;
        }
        if (i != correct.length()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    private OperationType nextOperation() {
        skipSpace();
        if (correctOperation != OperationType.NOTHING) {
            return correctOperation;
        }
        if (convertOperation.containsKey(s.charAt(position))) {
            correctOperation = convertOperation.get(s.charAt(position++));
        } else if (nextString("<<")) {
            correctOperation = OperationType.SHIFT_LEFT;
        } else if (nextString(">>")) {
            correctOperation = OperationType.SHIFT_RIGHT;
        }
        return correctOperation;
    }

    private MyExpression parseShifts() {
        MyExpression current = parseAddOrSubstrac();
        while (notEnd()) {
            OperationType nextOperation = nextOperation();
            if (!(nextOperation == OperationType.SHIFT_LEFT || nextOperation == OperationType.SHIFT_RIGHT)) {
                break;
            }
            correctOperation = OperationType.NOTHING;
            if (nextOperation == OperationType.SHIFT_LEFT) {
                current = new ShiftLeft(current, parseAddOrSubstrac());
            } else {
                current = new ShiftRight(current, parseAddOrSubstrac());
            }
        }
        return current;
    }

    private MyExpression parseAddOrSubstrac() {
        MyExpression current = parseMulOrDiv();
        while (notEnd()) {
            OperationType nextOperation = nextOperation();
            if (!(nextOperation == OperationType.ADD || nextOperation == OperationType.SUB)) {
                break;
            }
            correctOperation = OperationType.NOTHING;
            if (nextOperation == OperationType.ADD) {
                current = new Add(current, parseMulOrDiv());
            } else {
                current = new Subtract(current, parseMulOrDiv());
            }
        }
        return current;
    }

    private MyExpression parseMulOrDiv() {
        MyExpression current = parseBracket();
        while (notEnd()) {
            OperationType nextOperation = nextOperation();
            if (!(nextOperation == OperationType.MUL || nextOperation == OperationType.DIV)) {
                break;
            }
            correctOperation = OperationType.NOTHING;
            if (nextOperation == OperationType.MUL) {
                current = new Multiply(current, parseBracket());
            } else {
                current = new Divide(current, parseBracket());
            }
        }
        return current;
    }

    private MyExpression parseBracket() {
        OperationType nextOpertion = nextOperation();
        if (nextOpertion == OperationType.OPEN_BRACKET) {
            correctOperation = OperationType.NOTHING;
            MyExpression current = parseShifts();
            nextOpertion = nextOperation();
            correctOperation = OperationType.NOTHING;
            if (nextOpertion == OperationType.CLOSE_BRACKET) {
                return current;
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (nextOpertion == OperationType.SUB) {
            correctOperation = OperationType.NOTHING;
            return parseVariableOrConst(true);
        }
        return parseVariableOrConst(false);
    }

    private MyExpression parseVariableOrConst(boolean f) {
        int sign = parseUnaryMinus(f ? -1 : 1);
        if (nextString("abs")) {
            if (sign == -1) {
                return new Multiply(new Const(-1), new Abs(parseBracket()));
            }
            return new Abs(parseBracket());
        }
        if (nextString("square")) {
            if (sign == -1) {
                return new Multiply(new Const(-1), new Square(parseBracket()));
            }
            return new Square(parseBracket());
        }
        String s = nextLexem();
        int i = 0;
        if (s.length() == 0) {
            if (sign == -1) {
                return new Multiply(new Const(-1), parseBracket());
            }
            return parseBracket();
        }
        if (s.charAt(i) == 'x' || s.charAt(i) == 'y' || s.charAt(i) == 'z') {
            if (sign == -1) {
                return new Multiply(new Const(-1), new Variable("" + s.charAt(i)));
            }
            return new Variable("" + s.charAt(i));
        }
        StringBuilder x = new StringBuilder();
        for (; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                throw new IllegalArgumentException();
            }
            x.append(s.charAt(i));
        }
        StringBuilder ans = new StringBuilder();
        if (sign == -1) {
            ans.append('-');
        }
        ans.append(x);
        return new Const(Integer.parseInt(ans.toString()));
    }

    private int parseUnaryMinus(int c) {
        int sign = c;
        while (s.charAt(position) == '-') {
            sign *= -1;
            position++;
        }
        return sign;
    }

    @Override
    public TripleExpression parse(String expression) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isWhitespace(expression.charAt(i))) {
                continue;
            }
            sb.append(expression.charAt(i));
        }
        position = 0;
        correctOperation = OperationType.NOTHING;
        s = sb.toString();
        return parseShifts();
    }
}
