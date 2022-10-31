import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Language {



    private final List<String> separators;
    private final List<String> separatorsRegex;
    private final List<String> operators;
    private final List<String> operatorsRegex;
    private final List<String> reservedWords;

    public Language() {
        this.separatorsRegex = Arrays.asList("\\(", "\\)", "\\[", "\\]", "\\{", "\\}", "\\:", "\\;", " ", ",");
        this.separators = Arrays.asList("(", ")", "[", "]", "{", "}", ":", ";", " ", ",");
        this.operatorsRegex = Arrays.asList("\\+", "-", "\\*", "/", ":=", "=", "!=", "<", ">", "<=", ">=", "%" );
        this.operators = Arrays.asList("+", "-", "*", "/", ":=", "=", "!=", "<", ">", "<=", ">=", "%" );
        this.reservedWords = Arrays.asList("array", "char", "const", "do", "else", "if", "integer", "of", "program", "read", "then", "var", "while", "write", "begin", "end" );
    }

    public List<String> getReservedWords() {return reservedWords;}
    public List<String> getSeparatorsForRegex() {return separatorsRegex;}
    public List<String> getOperatorsForRegex() {return operatorsRegex;}

    public boolean isASeparator(String token){
        return separators.contains(token);
    }

    public boolean isAnOperator(String token){
        return operators.contains(token);
    }

    public boolean isAReservedWord(String token){
        return reservedWords.contains(token);
    }

    public boolean isIdentifier(String token){
        String regex = "^[a-zA-Z]([a-z|A-Z|0-9])*$";
        return token.matches(regex);
    }

    public boolean isANumber(String token){
        String numericPattern = "^0|[+|-][1-9]([0-9])*|[1-9]([0-9])*|[+|-][1-9]([0-9])*\\.([0-9])*|[1-9]([0-9])*\\.([0-9])*$";
        return token.matches(numericPattern);
    }
    public boolean isConstant(String token) {
        String numericPattern = "^0|[+|-][1-9]([0-9])*|[1-9]([0-9])*|[+|-][1-9]([0-9])*\\.([0-9])*|[1-9]([0-9])*\\.([0-9])*$";
        String charPattern = "^\'[a-zA-Z0-9_?!#*.%+=<>;)(}{_]\'";
        String stringPattern = "^\"[a-zA-Z0-9_?!#*.%+=<>;)(}{_]+\"";
        return token.matches(numericPattern) ||
                token.matches(charPattern) ||
                token.matches(stringPattern);
    }

}
