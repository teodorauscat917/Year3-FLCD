import java.util.ArrayList;

public class SymbolTable {

    public ArrayList<ArrayList<String>> hashArray;
    private final int size;

    public SymbolTable(int size) {
        this.size = size;
        this.hashArray = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            this.hashArray.add(new ArrayList<>());
        }
    }

    private int hash(String symbol) {
        return symbol.codePoints().sum() % size;
    }

    public Pair addSymbol(String symbol) {
        int hashValue = hash(symbol);

        if (!hashArray.get(hashValue).contains(symbol)) {
            hashArray.get(hashValue).add(symbol);
        }

        return getPositionByElement(symbol);
    }

    public boolean isInTable(String symbol) {
        return hashArray.get(hash(symbol)).contains(symbol);
    }
    public Pair getPositionByElement(String symbol) {
        if (!isInTable(symbol))
            return null;

        return new Pair(hash(symbol), hashArray.get(hash(symbol)).indexOf(symbol));
    }
}
