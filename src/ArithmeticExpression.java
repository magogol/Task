import java.util.ArrayList;
import java.util.List;

public class ArithmeticExpression {
    public enum TypeOfLexeme {
        LEFT_BRACKET, RIGHT_BRACKET,
        PLUS, MINUS, MULT, DIVIDE,
        NUMBER,
        END;
    }

    public static class Lexeme {
        TypeOfLexeme type;
        String value;

        public Lexeme(TypeOfLexeme type, String value) {
            this.type = type;
            this.value = value;
        }

        public Lexeme(TypeOfLexeme type, Character value) {
            this.type = type;
            this.value = value.toString();
        }

        @Override
        public String toString() {
            return type + value;
        }
    }

    public static class LexemeBuffer {
        private int pos;

        public List<Lexeme> lexemes;

        public LexemeBuffer(List<Lexeme> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexeme next() {
            return lexemes.get(pos++);
        }

        public void back() {
            pos--;
        }

        public int getPos() {
            return pos;
        }
    }

    public static List<Lexeme> analyse(String str) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos < str.length()) {
            char c = str.charAt(pos);
            switch (c) {
                case '(':
                    lexemes.add(new Lexeme(TypeOfLexeme.LEFT_BRACKET, c));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(TypeOfLexeme.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(TypeOfLexeme.PLUS, c));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(TypeOfLexeme.MINUS, c));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(TypeOfLexeme.MULT, c));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(TypeOfLexeme.DIVIDE, c));
                    pos++;
                    continue;
                default:
                    if (c <= '9' && c >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= str.length()) {
                                break;
                            }
                            c = str.charAt(pos);
                        } while (c <= '9' && c >= '0');
                        lexemes.add(new Lexeme(TypeOfLexeme.NUMBER, sb.toString()));
                    } else {
                        if (c != ' ') {
                            throw new RuntimeException("Unexpected character: " + c);
                        }
                        pos++;
                    }
            }
        }
        lexemes.add(new Lexeme(TypeOfLexeme.END, ""));
        return lexemes;
    }

    public static int expression(LexemeBuffer lexemes) {
        Lexeme lex = lexemes.next();
        if (lex.type == TypeOfLexeme.END) {
            return 0;
        } else {
            lexemes.back();
            return plusminus(lexemes);
        }
    }

    public static int plusminus(LexemeBuffer lexemes) {
        int value = multdiv(lexemes);
        while (true) {
            Lexeme lex = lexemes.next();
            switch (lex.type) {
                case PLUS:
                    value += multdiv(lexemes);
                    break;
                case MINUS:
                    value -= multdiv(lexemes);
                    break;
                case END:
                case RIGHT_BRACKET:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lex.value
                            + " at position: " + lexemes.getPos());
            }
        }
    }

    public static int multdiv(LexemeBuffer lexemes) {
        int value = multiplier(lexemes);
        while (true) {
            Lexeme lex = lexemes.next();
            switch (lex.type) {
                case MULT:
                    value *= multiplier(lexemes);
                    break;
                case DIVIDE:
                    value /= multiplier(lexemes);
                    break;
                case END:
                case RIGHT_BRACKET:
                case PLUS:
                case MINUS:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lex.value
                            + " at position: " + lexemes.getPos());
            }
        }
    }

    public static int multiplier(LexemeBuffer lexemes) {
        Lexeme lex = lexemes.next();
        switch (lex.type) {
            case NUMBER:
                return Integer.parseInt(lex.value);
            case LEFT_BRACKET:
                int value = expression(lexemes);
                lex = lexemes.next();
                if (lex.type != TypeOfLexeme.RIGHT_BRACKET) {
                    throw new RuntimeException("Unexpected token: " + lex.value
                            + " at position: " + lexemes.getPos());
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + lex.value
                        + " at position: " + lexemes.getPos());
        }
    }
}
