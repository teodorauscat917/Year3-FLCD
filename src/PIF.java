import java.util.ArrayList;

public class PIF {
    private final ArrayList<Pair> tuples;

    public PIF() {
        this.tuples = new ArrayList<>();
    }

    public void addReservedWord(String token) {
        Pair reservedPos = new Pair(-1, -1);
        Pair reservedPair = new Pair(token, reservedPos);
        this.tuples.add(reservedPair);
    }

    public void addConstant(Pair pos) {
        Pair constant = new Pair("const", pos);
        this.tuples.add(constant);
    }

    public void addIdentifier(Pair pos) {
        Pair identifier = new Pair("id", pos);
        this.tuples.add(identifier);
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        for (Pair<Integer, Pair<Integer, Integer>> pair : tuples) {
            result.append(pair.getFirst()).append(" -> (").append(pair.getSecond().getFirst()).append(", ").append(pair.getSecond().getSecond()).append(")\n");
        }
        return result.toString();
    }
}


