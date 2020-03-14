package expression.parser;

import expression.*;
import expression.exceptions.IllegalArgumentException;
import expression.exceptions.IllegalNumberException;
import expression.exceptions.IllegalOperationException;
import expression.exceptions.IllegalSymbolException;
import expression.generic.Calculator;

import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

/**
 * @author Yaroslav Ilin
 */

public class ExpressionParser<T extends Number> implements Parser<T> {
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
    private String[] variable = new String[3];
    private String mode = "";
    private Calculator<T> calculator;


    private String nextLexem() throws IllegalSymbolException, IllegalArgumentException, IllegalOperationException {
        StringBuilder sb = new StringBuilder();
        while (notEnd() && s.charAt(position) == '-') {
            sb.append(s.charAt(position));
            position++;
        }
        boolean f = false;
        while (notEnd() && !convertOperation.containsKey(s.charAt(position)) && !(s.charAt(position) == '<') && !(s.charAt(position) == '>')
                && !(s.charAt(position) == 'm')) {
            sb.append(s.charAt(position));
            position++;
            f = true;
        }
        if (f && notEnd() && s.charAt(position) == '(') {
            throw new IllegalOperationException("find " + nextOperation(),
                    s.substring(max(0, position - 10), min(position + 10, s.length())));
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

    private boolean nextString(String correct) throws IllegalOperationException {
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
            position -= i;
            return false;
        }
        return true;
    }

    private OperationType nextOperation() throws IllegalSymbolException, IllegalArgumentException, IllegalOperationException {
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
                    throw new IllegalArgumentException("No argument with operation",
                            s.substring(max(0, position - 10), min(position + 10, s.length())));
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
                    throw new IllegalArgumentException("don't have argument with operation",
                            s.substring(max(0, position - 10), min(position + 10, s.length())));
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
        } else if (nextString("max")) {
            correctOperation = OperationType.MAX;
        } else if (nextString("min")) {
            correctOperation = OperationType.MIN;
        }
        return correctOperation;
    }

    /*private MyExpression<T> parseShifts() throws IllegalSymbolException, IllegalArgumentException,
            IllegalOperationException, IllegalNumberException {
        MyExpression<T> current = parseAddOrSubstrac();
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
    }*/

    private MyExpression<T> parseMinOrMax() throws IllegalSymbolException, IllegalOperationException, IllegalArgumentException, IllegalNumberException {
        MyExpression<T> current = parseAddOrSubstrac();
        while (notEnd()) {
            OperationType nextOperation = nextOperation();
            if (!(nextOperation == OperationType.MAX || nextOperation == OperationType.MIN)) {
                break;
            }
            correctOperation = OperationType.NOTHING;
            if (nextOperation == OperationType.MAX) {
                current = new Max<T>(current, parseAddOrSubstrac(), calculator);
            } else {
                current = new Min<T>(current, parseAddOrSubstrac(), calculator);
            }
        }
        return current;
    }

    private MyExpression<T> parseAddOrSubstrac() throws IllegalSymbolException, IllegalOperationException,
            IllegalArgumentException, IllegalNumberException {
        MyExpression<T> current = parseMulOrDiv();
        while (notEnd()) {
            OperationType nextOperation = nextOperation();
            if (!(nextOperation == OperationType.ADD || nextOperation == OperationType.SUB)) {
                break;
            }
            correctOperation = OperationType.NOTHING;
            if (nextOperation == OperationType.ADD) {
                current = new Add<T>(current, parseMulOrDiv(), calculator);
            } else {
                current = new Subtract<T>(current, parseMulOrDiv(), calculator);
            }
        }
        return current;
    }

    private MyExpression<T> parseMulOrDiv() throws IllegalSymbolException, IllegalArgumentException,
            IllegalOperationException, IllegalNumberException {
        MyExpression<T> current = parseBracket();
        while (notEnd()) {
            OperationType nextOperation = nextOperation();
            if (!(nextOperation == OperationType.MUL || nextOperation == OperationType.DIV)) {
                break;
            }
            correctOperation = OperationType.NOTHING;
            if (nextOperation == OperationType.MUL) {
                current = new Multiply<T>(current, parseBracket(), calculator);
            } else {
                current = new Divide<T>(current, parseBracket(), calculator);
            }
        }
        return current;
    }

    /*private MyExpression<T> parsePowOrLog() throws IllegalSymbolException, IllegalOperationException,
            IllegalArgumentException, IllegalNumberException {
        MyExpression<T> current = parseBracket();
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
    }*/

    private MyExpression<T> parseBracket() throws IllegalSymbolException, IllegalOperationException,
            IllegalArgumentException, IllegalNumberException {
        OperationType nextOpertion = nextOperation();
        if (nextOpertion == OperationType.CLOSE_BRACKET) {
            throw new IllegalSymbolException("Illegal operation, find " + nextOpertion,
                    s.substring(max(0, position - 10), min(position + 10, s.length())));
        }
        if (nextOpertion == OperationType.OPEN_BRACKET) {
            correctOperation = OperationType.NOTHING;
            MyExpression<T> current = parseAddOrSubstrac();
            nextOpertion = nextOperation();
            correctOperation = OperationType.NOTHING;
            if (nextOpertion == OperationType.CLOSE_BRACKET) {
                return current;
            } else {
                throw new IllegalOperationException("not found CLOSE_BRACKET",
                        s.substring(max(0, position - 10), min(position + 10, s.length())));
            }
        }
        if (nextOpertion == OperationType.SUB) {
            correctOperation = OperationType.NOTHING;
            return parseVariableOrConst(true);
        } else if (nextOpertion == OperationType.ADD || nextOpertion == OperationType.MUL
                || nextOpertion == OperationType.DIV || nextOpertion == OperationType.POW
                || nextOpertion == OperationType.LOG
        ) {
            throw new IllegalSymbolException("Illegal operation, find " + nextOpertion,
                    s.substring(max(0, position - 10), min(position + 10, s.length())));
        }
        return parseVariableOrConst(false);
    }

    private MyExpression<T> parseVariableOrConst(boolean f) throws IllegalSymbolException,
            IllegalOperationException, IllegalArgumentException, IllegalNumberException {
        int sign = parseUnaryMinus(f ? -1 : 1);
        if (nextString("count")) {
            if (sign == -1) {
                return new Multiply<T>(new Const<T>(calculator.cnst("-1")), new Count<T>(parseBracket(), calculator), calculator);
            }
            return new Count<T>(parseBracket(), calculator);
        }
        /*if (nextString("log2")) {
            if (sign == -1) {
                return new Multiply<T>(new Const<T>(-1), new Log2(parseBracket()));
            }
            return new Log2(parseBracket());
        }
        if (nextString("pow2")) {
            if (sign == -1) {
                return new Multiply<T>(new Const<T>(-1), new Pow2(parseBracket()));
            }
            return new Pow2(parseBracket());
        }*/
        String s = nextLexem();
        int i = 0;
        if (s.length() == 0) {
            if (sign == -1) {
                return new Multiply<T>(new Const<T>(calculator.cnst("-1")), parseBracket(), calculator);
            }
            return parseBracket();
        }


        String var = nextVariable(s);
        if (!var.equals("")) {
            if (sign == -1) {
                return new Multiply<T>(new Const<T>(calculator.cnst("-1")), new Variable<T>(var), calculator);
            }
            return new Variable<T>(var);
        }
        return parseConst(s, i, sign);
    }

    private MyExpression<T> parseConst(String s, int i, int sign) throws IllegalArgumentException, IllegalNumberException {
        StringBuilder x = new StringBuilder();
        for (; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)) && !(mode.equals("d") && s.charAt(i) == '.')) {
                throw new IllegalArgumentException("expected digit",
                        s.substring(max(0, position - 10), min(position + 10, s.length())));
            }
            x.append(s.charAt(i));
        }
        StringBuilder ans = new StringBuilder();
        if (sign == -1) {
            ans.append('-');
        }
        ans.append(x);
        try {
            return new Const<>(calculator.cnst(ans.toString()));
        } catch (NumberFormatException e) {
            throw new IllegalNumberException("found illegal number",
                    s.substring(max(0, position - 10), min(position + 10, s.length())));
        }
    }

    private String nextVariable(String s) throws IllegalOperationException {
        for (int i = 0; i < variable.length; i++) {
            if (s.equals(variable[i])) {
                return variable[i];
            }
        }
        return "";
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
                    throw new IllegalSymbolException("No correct expression",
                            s.substring(max(0, position - 10), min(position + 10, s.length())));
                }
            }
        }
        if (sum != 0) {
            throw new IllegalSymbolException("No correct expression",
                    s.substring(max(0, position - 10), min(position + 10, s.length())));
        }
    }

    @Override
    public TripleExpression parse(String expression) throws IllegalSymbolException, IllegalOperationException, IllegalArgumentException, IllegalNumberException {
        StringBuilder sb = new StringBuilder();
        StringBuilder brackets = new StringBuilder();
        variable[0] = "x";
        variable[1] = "y";
        variable[2] = "z";
        boolean f = false;
        boolean g = false;
        boolean u = false;
        boolean k = false;
        for (int i = 0; i < expression.length(); i++) {
            /*if (expression.charAt(i) == 'l') {
                String s = "log2";
                if (expression.length() - 4 <= i)
                    throw new IllegalOperationException("found illegal operation",
                            expression.substring(max(i - 10, 0), min(i + 10, expression.length())));
                for (int j = i; j < i + 4; j++) {
                    sb.append(expression.charAt(j));
                    if (expression.charAt(j) != s.charAt(j - i)) {
                        throw new IllegalOperationException("found illegal operation",
                                expression.substring(max(i - 10, 0), min(i + 10, expression.length())));
                    }
                }
                i = i + 3;
                if (i + 1 == expression.length() || Character.isLetter(expression.charAt(i + 1))
                        || Character.isDigit(expression.charAt(i + 1))) {
                    throw new IllegalOperationException("found illegal operation",
                            expression.substring(max(i - 10, 0), min(i - 3, expression.length())));
                }
                g = false;
                f = false;
                continue;
            }
            if (expression.charAt(i) == 'p') {
                String s = "pow2";
                if (expression.length() - 4 <= i)
                    throw new IllegalOperationException("found illegal operation",
                            expression.substring(max(i - 10, 0), min(i + 10, expression.length())));
                for (int j = i; j < i + 4; j++) {
                    sb.append(expression.charAt(j));
                    if (expression.charAt(j) != s.charAt(j - i)) {
                        throw new IllegalSymbolException("found illegal symbol",
                                expression.substring(max(i - 10, 0), min(i + 10, expression.length())));
                    }
                }
                i = i + 3;
                if (i + 1 == expression.length() || Character.isLetter(expression.charAt(i + 1))
                        || Character.isDigit(expression.charAt(i + 1))) {
                    throw new IllegalOperationException("found illegal operation", expression.substring(0, i - 3));
                }
                g = false;
                f = false;
                continue;
            }*/
            if (Character.isWhitespace(expression.charAt(i))) {
                g = true;
                continue;
            }
            /*if (expression.charAt(i) == '*') {
                if (u && g) {
                    throw new IllegalOperationException("found operation * *",
                            expression.substring(max(i - 10, 0), min(i + 10, expression.length())));
                }
                u = true;
            } else {
                u = false;
            }*/
            /*if (expression.charAt(i) == '/') {
                if (k && g) {
                    throw new IllegalOperationException("found operation / /",
                            expression.substring(max(i - 10, 0), min(i + 10, expression.length())));
                }
                k = true;
            } else {
                k = false;
            }*/
            if (Character.isDigit(expression.charAt(i))) {
                if (f && g) {
                    throw new IllegalSymbolException("found spase in digit",
                            expression.substring(max(i - 10, 0), min(i + 10, expression.length())));
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
                throw new IllegalSymbolException("found illegal symbol: " + expression.charAt(i),
                        expression.substring(max(i - 10, 0), min(i + 10, expression.length())));
            }
            sb.append(expression.charAt(i));
        }
        isRightBrackets(brackets.toString());
        position = 0;
        correctOperation = OperationType.NOTHING;
        s = sb.toString();
//        System.err.println(s);
        try {
            return parseAddOrSubstrac();
        } catch (NumberFormatException e) {
            throw new IllegalNumberException("found illegal number", "");
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("found illegal argument", "last operation doesn't have argument");
        }
    }

    private String correctInput(String expression, Set<Character> correctSymbol) throws IllegalSymbolException {
        StringBuilder sb = new StringBuilder();
        StringBuilder brackets = new StringBuilder();
        boolean f = false;
        boolean g = false;
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isWhitespace(expression.charAt(i))) {
                g = true;
                continue;
            }
            if (Character.isDigit(expression.charAt(i))) {
                if (f && g) {
                    throw new IllegalSymbolException("found spase in digit",
                            expression.substring(max(i - 10, 0), min(i + 10, expression.length())));
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
                    && !correctSymbol.contains(expression.charAt(i))
            ) {
                throw new IllegalSymbolException("found illegal symbol: " + expression.charAt(i),
                        expression.substring(max(i - 10, 0), min(i + 10, expression.length())));
            }
            sb.append(expression.charAt(i));
        }
        isRightBrackets(brackets.toString());
        return sb.toString();
    }

    @Override
    public MyExpression<T> parse(String expression, Calculator<T> calculator) throws IllegalSymbolException, IllegalOperationException, IllegalArgumentException, IllegalNumberException {
        this.calculator = calculator;
        variable[0] = "x";
        variable[1] = "y";
        variable[2] = "z";
        position = 0;
        correctOperation = OperationType.NOTHING;
        s = correctInput(expression, Set.of('x', 'y', 'z', '+', '-', '*', '/', '(', ')', 'm', 'a', 'i', 'n', 'c', 'o', 'u', 't'));

        try {
            return parseMinOrMax();
        } catch (NumberFormatException e) {
            throw new IllegalNumberException("found illegal number", "");
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("found illegal argument", "last operation doesn't have argument");
        }
    }
}
