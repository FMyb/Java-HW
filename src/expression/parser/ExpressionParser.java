package expression.parser;

import expression.*;
import expression.exceptions.IllegalSymbolException;

import java.util.Map;
import java.util.Stack;

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

    private String nextLexem() throws IllegalSymbolException {
        StringBuilder sb = new StringBuilder();
        while (notEnd() && s.charAt(position) == '-') {
            sb.append(s.charAt(position));
            position++;
        }
        boolean f = false;
        while (notEnd() && !convertOperation.containsKey(s.charAt(position)) && !(s.charAt(position) == '<') && !(s.charAt(position) == '>')) {
            sb.append(s.charAt(position));
            position++;
            f = true;
        }
        if (f && notEnd() && s.charAt(position) == '(') {
            throw new IllegalSymbolException("find " + nextOperation());
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

    private OperationType nextOperation() throws IllegalSymbolException {
        skipSpace();
        if (correctOperation != OperationType.NOTHING) {
            return correctOperation;
        }
        if (s.charAt(position) == '*') {
            position++;
            if (notEnd() && s.charAt(position) == '*') {
                position++;
                correctOperation = OperationType.POW;
            } else {
                if (!notEnd()) {
                    throw new IllegalSymbolException("No argument");
                }
                correctOperation = OperationType.MUL;
            }
            return correctOperation;
        }
        if (s.charAt(position) == '/') {
            position++;
            if (notEnd() && s.charAt(position) == '/') {
                position++;
                correctOperation = OperationType.LOG;
            } else {
                if (!notEnd()) {
                    throw new IllegalSymbolException("No argument");
                }
                correctOperation = OperationType.DIV;
            }
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

    private MyExpression parseShifts() throws IllegalSymbolException {
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

    private MyExpression parseAddOrSubstrac() throws IllegalSymbolException {
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

    private MyExpression parseMulOrDiv() throws IllegalSymbolException {
        MyExpression current = parsePowOrLog();
        while (notEnd()) {
            OperationType nextOperation = nextOperation();
            if (!(nextOperation == OperationType.MUL || nextOperation == OperationType.DIV)) {
                break;
            }
            correctOperation = OperationType.NOTHING;
            if (nextOperation == OperationType.MUL) {
                current = new Multiply(current, parsePowOrLog());
            } else {
                current = new Divide(current, parsePowOrLog());
            }
        }
        return current;
    }

    private MyExpression parsePowOrLog() throws IllegalSymbolException {
        MyExpression current = parseBracket();
        while (notEnd()) {
            OperationType nextOperation = nextOperation();
            if (!(nextOperation == OperationType.LOG || nextOperation == OperationType.POW)) {
                break;
            }
            correctOperation = OperationType.NOTHING;
            if (nextOperation == OperationType.POW) {
                current = new Pow(current, parseBracket());
            } else {
                current = new Log(current, parseBracket());
            }
        }
        return current;
    }

    private MyExpression parseBracket() throws IllegalSymbolException {
        OperationType nextOpertion = nextOperation();
        if (nextOpertion == OperationType.CLOSE_BRACKET) {
            throw new IllegalSymbolException("Illegal operation, find " + nextOpertion);
        }
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
        } else if (nextOpertion == OperationType.ADD || nextOpertion == OperationType.MUL
                || nextOpertion == OperationType.DIV || nextOpertion == OperationType.POW
                || nextOpertion == OperationType.LOG
        ) {
            throw new IllegalSymbolException("Illegal operation, find " + nextOpertion);
        }
        return parseVariableOrConst(false);
    }

    private MyExpression parseVariableOrConst(boolean f) throws IllegalSymbolException {
        int sign = parseUnaryMinus(f ? -1 : 1);
        if (nextString("log2")) {
            if (sign == -1) {
                return new Multiply(new Const(-1), new Log2(parseBracket()));
            }
            return new Log2(parseBracket());
        }
        if (nextString("pow2")) {
            if (sign == -1) {
                return new Multiply(new Const(-1), new Pow2(parseBracket()));
            }
            return new Pow2(parseBracket());
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

    private void isRightBrackets(String brackets) throws IllegalSymbolException {
        int sum = 0;
        for (int i = 0; i < brackets.length(); i++) {
            if (brackets.charAt(i) == '(') {
                sum++;
            } else {
                sum--;
                if (sum < 0) {
                    throw new IllegalSymbolException("No correct expression, find CLOSE_BRACKET");
                }
            }
        }
        if (sum != 0) {
            throw new IllegalSymbolException("No correct expression, find OPEN_BRACKET");
        }
    }

    @Override
    public TripleExpression parse(String expression) throws IllegalSymbolException {
        StringBuilder sb = new StringBuilder();
        StringBuilder brackets = new StringBuilder();
        boolean f = false;
        boolean g = false;
        boolean u = false;
        boolean k = false;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == 'l') {
                String s = "log2";
                if (expression.length() - 4 <= i) throw new IllegalSymbolException("found illegal symbol");
                for (int j = i; j < i + 4; j++) {
                    sb.append(expression.charAt(j));
                    if (expression.charAt(j) != s.charAt(j - i)) {
                        throw new IllegalSymbolException("found illegal symbol");
                    }
                }
                i = i + 3;
                if (i + 1 == expression.length() || Character.isLetter(expression.charAt(i + 1)) || Character.isDigit(expression.charAt(i + 1))) {
                    throw new IllegalArgumentException();
                }
                g = false;
                f = false;
                continue;
            }
            if (expression.charAt(i) == 'p') {
                String s = "pow2";
                if (expression.length() - 4 <= i) throw new IllegalSymbolException("found illegal symbol");
                for (int j = i; j < i + 4; j++) {
                    sb.append(expression.charAt(j));
                    if (expression.charAt(j) != s.charAt(j - i)) {
                        throw new IllegalSymbolException("found illegal symbol");
                    }
                }
                i = i + 3;
                if (i + 1 == expression.length() || Character.isLetter(expression.charAt(i + 1)) || Character.isDigit(expression.charAt(i + 1))) {
                    throw new IllegalArgumentException();
                }
                g = false;
                f = false;
                continue;
            }
            if (Character.isWhitespace(expression.charAt(i))) {
                g = true;
                continue;
            }
            if (expression.charAt(i) == '*') {
                if (u && g) {
                    throw new IllegalSymbolException("Not found operation * *");
                }
                u = true;
            } else {
                u = false;
            }
            if (expression.charAt(i) == '/') {
                if (k && g) {
                    throw new IllegalSymbolException("not found operation * *");
                }
                k = true;
            } else {
                k = false;
            }
            if (Character.isDigit(expression.charAt(i))) {
                if (f && g) {
                    throw new IllegalSymbolException("found spase in digit");
                }
                f = true;
            } else {
                f = false;
            }
            g = false;
            if (expression.charAt(i) == '(' || expression.charAt(i) == ')') {
                brackets.append(expression.charAt(i));
            }
            if (!Character.isDigit(expression.charAt(i))
                    && !(expression.charAt(i) == 'x') && !(expression.charAt(i) == 'y') && !(expression.charAt(i) == 'z')
                    && !(expression.charAt(i) == '+') && !(expression.charAt(i) == '-') && !(expression.charAt(i) == '*')
                    && !(expression.charAt(i) == '/') && !(expression.charAt(i) == '(') && !(expression.charAt(i) == ')')
            ) {
                throw new IllegalSymbolException("found illegal symbol: " + expression.charAt(i));
            }
            sb.append(expression.charAt(i));
        }
        isRightBrackets(brackets.toString());
        position = 0;
        correctOperation = OperationType.NOTHING;
        s = sb.toString();
//        System.err.println(s);
        return parseShifts();
    }
}
