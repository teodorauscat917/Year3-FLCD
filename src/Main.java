public class Main {

    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(7);


        String[] symbols = {"a1", "1a", "b2", "c3", "d4", "a1", "b5"};

        for (String symbol : symbols) {
            symbolTable.addSymbol(symbol);
            System.out.println(symbolTable.addSymbol(symbol));
        }
        System.out.println(symbolTable.hashArray);

        for (String symbol : symbols) {
            System.out.println(symbolTable.isInTable(symbol));
        }


    }
}
