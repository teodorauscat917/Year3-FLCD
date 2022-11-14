import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Language {



    private List<String> separators;
    private final List<String> separatorsRegex;
    private List<String> operators;
    private final List<String> operatorsRegex;
    private List<String> reservedWords;

    public Language(String tokenFile) throws FileNotFoundException {
        this.initialise(tokenFile);
        this.separatorsRegex = Arrays.asList("\\(", "\\)", "\\[", "\\]", "\\{", "\\}", "\\:", "\\;", " ", ",");
        this.operatorsRegex = Arrays.asList("\\+", "-", "\\*", "/", ":=", "=", "!=", "<", ">", "<=", ">=", "%" );

    }

    public void initialise(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner reader = new Scanner(file);

        int lineNr = 1;
        boolean isOk = true;

        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(", ");
            switch(lineNr){
                case 1: {
                    this.separators = List.of(line);
                }
                case 2: {
                    this.operators = List.of(line);
                }
                case 3: {
                    this.reservedWords = List.of(line);
                }
            }
            ++lineNr;
            }


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
        String number = "^0|[+|-][1-9]([0-9])*|[1-9]([0-9])*|[+|-][1-9]([0-9])*\\.([0-9])*|[1-9]([0-9])*\\.([0-9])*$";
        return token.matches(number);
    }
    public boolean isConstant(String token) {
        String number = "^0|[+|-][1-9]([0-9])*|[1-9]([0-9])*|[+|-][1-9]([0-9])*\\.([0-9])*|[1-9]([0-9])*\\.([0-9])*$";
        String character = "^\'[a-zA-Z0-9_?!#*.%+=<>;)(}{_]\'";
        String str = "^\"[a-zA-Z0-9_?!#*.%+=<>;)(}{_]+\"";
        return token.matches(number) ||
                token.matches(character) ||
                token.matches(str);
    }

}
