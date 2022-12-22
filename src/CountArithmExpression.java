import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountArithmExpression {
    public static void CountExp(File input, File output) throws IOException {

        BufferedReader br = null;

        Pattern p = Pattern.compile("((\\d+(\\.\\d+)?)\\s*(\\+|\\-|\\*|\\/)\\s*)?(\\s*\\()?\\s*(\\d+(\\.\\d+)?)\\s*(\\+|\\-|\\*|\\/)\\s*(\\d+(\\.\\d+)?)\\s*(\\)?)\\s*(\\s*(\\+|\\-|\\*|\\/)\\s*(\\d+(\\.\\d+)?))?");
        Matcher m;

        PrintWriter pwout = new PrintWriter(output);

        br = new BufferedReader(new FileReader(input));
        String s;
        while ((s = br.readLine()) != null) {
            m = p.matcher(s);
            String expr = null;
            while (m.find()) {
                expr = m.group();
                expr = expr.trim();
                if (expr.contains("(") && !expr.contains(")"))
                    expr = expr.replace("(", "");
                if (expr.contains(")") && !expr.contains("("))
                    expr = expr.replace(")", "");

                List<ArithmeticExpression.Lexeme> lexemes = ArithmeticExpression.analyse(expr);
                ArithmeticExpression.LexemeBuffer lexemeBuffer = new ArithmeticExpression.LexemeBuffer(lexemes);
                String val = String.valueOf(ArithmeticExpression.expression(lexemeBuffer));
                s = s.replace(expr, val);
            }
            pwout.println(s);
        }
        pwout.close();
    }
}
