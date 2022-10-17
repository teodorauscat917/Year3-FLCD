public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(7);

        String[] symbols = {"a", "b", "test", "c", "c"};

        for (String symbol : symbols) {
            symbolTable.addSymbol(symbol);
        }
        System.out.println(symbolTable.hashArray);

        for (int i = 0; i < symbols.length; ++i) {
            System.out.println(symbolTable.isInTable(symbols[i]));
        }
    }
}
