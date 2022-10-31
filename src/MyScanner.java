import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MyScanner {
    private final SymbolTable identifiers;
    private final SymbolTable constants;

    private final PIF pif;
    private final Language language;

    private final String path;

    public MyScanner(String path) {
        this.identifiers = new SymbolTable(13);
        this.constants = new SymbolTable(13);
        this.pif = new PIF();
        this.language = new Language();
        this.path = path;
    }

    public void scan() {
        try {
            File file = new File(this.path);
            Scanner reader = new Scanner(file);

            int lineNr = 1;
            boolean isOk = true;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                List<String> tokens = tokenize(line);

                for (String token : tokens) {
                    if (language.isAReservedWord(token) || language.isAnOperator(token) || language.isASeparator(token)) {
                        pif.addReservedWord(token);
                    } else if (language.isIdentifier(token)) {
                        identifiers.addSymbol(token);
                        Pair pos = identifiers.getPositionByElement(token);
                        pif.addIdentifier(pos);

                    } else if (language.isConstant(token)) {
                        constants.addSymbol(token);
                        Pair pos = constants.getPositionByElement(token);
                        pif.addConstant(pos);
                    } else {
                        isOk = false;
                        System.out.println("Error found on line " + lineNr + ";Error token: " + token);
                    }
                }

                ++lineNr;
            }

            reader.close();
            if (isOk) {
                System.out.println("Passed check\n" + pif + "Identifiers:\n" + identifiers + "\n\nConstants\n" + constants);
                FileWriter myWriter = new FileWriter("src/PIF.out");
                myWriter.write("" + pif);
                myWriter.close();

                FileWriter myWriter2 = new FileWriter("src/ST.out");
                myWriter2.write("Identifier ST:\n" + identifiers + "\n\nConstants ST:\n" + constants);
                myWriter2.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> tokenize(String line) {
        ArrayList<String> tokens = new ArrayList<>();

        String regex = String.join("|", language.getOperatorsForRegex());
        regex = regex.concat("|" + String.join("|", language.getSeparatorsForRegex()));
        regex = regex.concat("|" + String.join("|", language.getReservedWords()));

        regex = "((?=(" + regex + "))|(?<=(" + regex + ")))";
        String[] result = line.split(regex);

        for (int i = 0; i < result.length; i++) {
            if (i + 1 < result.length) {
                if (Objects.equals(result[i], ":") || Objects.equals(result[i], "!") || Objects.equals(result[i], "<") || Objects.equals(result[i], ">")) {
                    if (Objects.equals(result[i + 1], "=")) {
                        tokens.add(result[i] + result[i + 1]);
                        i = i + 1;
                    } else tokens.add(result[i]);
                } else if (i - 1 >= 0) {
                    if (language.isAnOperator(result[i - 1])) {
                        if (Objects.equals(result[i], "+") || Objects.equals(result[i], "-")) {
                            if (language.isANumber(result[i + 1])) {
                                tokens.add(result[i] + result[i + 1]);
                                i = i + 1;
                            } else tokens.add(result[i]);
                        } else tokens.add(result[i]);
                    } else tokens.add(result[i]);
                } else tokens.add(result[i]);
            } else tokens.add(result[i]);
        }
        return tokens;
    }
}

