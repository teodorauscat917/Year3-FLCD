import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Language {
    private final List<String> reservedWords;
    private final List<String> operators;
    private final List<String> separators;


    public Language() {
        this.reservedWords = Arrays.asList("array", "const", "int", "string", "do", "char", "of", "program", "then", "var", "write", "begin", "while", "if", "else", "read", "print");
        this.operators = Arrays.asList("+", "-", "*", "/", "%", ":=", "!=", "<", ">", "<=", ">=");
        this.separators = Arrays.asList("(", ")", ";", "{", "}", "[", "]", " ", "\n");
    }


    public boolean isReservedWord(String token) {
        return reservedWords.contains(token);
    }

    public boolean isOperator(String token) {
        return operators.contains(token);
    }

    public boolean isSeparator(String token) {
        return separators.contains(token);
    }

    public boolean isIdentifier(String token) {
        String pattern = "^[a-zA-Z]([a-z|A-Z|0-9])*$";
        return token.matches(pattern);
    }

    public boolean isConstant(String token) {
        String numberRegex = "^0|[+|-][1-9]([0-9])*|[1-9]([0-9])*|[+|-][1-9]([0-9])*\\.([0-9])*|[1-9]([0-9])*\\.([0-9])*$";
        String charRegex = "^\'[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]\'";
        String stringRegex = "^\"[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]+\"";
        return token.matches(numberRegex ) ||
                token.matches(charRegex) ||
                token.matches(stringRegex);
    }

}
