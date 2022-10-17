import java.util.ArrayList;

public class SymbolTable {

    public  ArrayList<ArrayList<String>> hashArray;
    private int size;

    public SymbolTable(int size){
        this.size = size;
        this.hashArray = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            this.hashArray.add(new ArrayList<>());
        }
    }

    private int hash(String symbol) {
        return symbol.codePoints().sum() % size;
    }

    public boolean addSymbol(String symbol) {
        int hashValue = hash(symbol);

        if (hashArray.get(hashValue).contains(symbol)) {
            return false;
        }

        hashArray.get(hashValue).add(symbol);
        return true;
    }

    public boolean isInTable(String symbol) {
        return hashArray.get(hash(symbol)).contains(symbol);
    }

}