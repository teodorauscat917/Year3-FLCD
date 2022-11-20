import java.io.File;
import java.util.*;

public class FiniteAutomaton {
    public Set<String> alphabet, states, finalStates;
    public String initialState;
    public Map<Pair<String, String>, Set<String>> transitions;

    public FiniteAutomaton(String filename) {
        this.states = new HashSet<>();
        this.alphabet = new HashSet<>();
        this.finalStates = new HashSet<>();
        this.transitions = new HashMap<>();

        readFiniteAutomaton(filename);
    }

    private void readFiniteAutomaton(String filename){
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            String statesLine = reader.nextLine();
            states = new HashSet<>(Arrays.asList(statesLine.split(" ")));

            String alphabetLine = reader.nextLine();
            alphabet = new HashSet<>(Arrays.asList(alphabetLine.split(" ")));

            initialState = reader.nextLine();

            String finalStatesLine = reader.nextLine();
            finalStates = new HashSet<>(Arrays.asList(finalStatesLine.split(" ")));

            while(reader.hasNextLine()){
                String transitionLine = reader.nextLine();
                String[] transitionElements = transitionLine.split(" ");

                if(states.contains(transitionElements[0]) && states.contains(transitionElements[2]) && alphabet.contains(transitionElements[1])) {

                    Pair<String, String> transitionStates = new Pair<>(transitionElements[0], transitionElements[1]);

                    if (!transitions.containsKey(transitionStates)) {
                        Set<String> transitionStatesSet = new HashSet<>();
                        transitionStatesSet.add(transitionElements[2]);
                        transitions.put(transitionStates, transitionStatesSet);
                    } else {
                        transitions.get(transitionStates).add(transitionElements[2]);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String writeAlphabet(){
        StringBuilder builder = new StringBuilder();
        builder.append("Alphabet: ");
        for (String a : alphabet){
            builder.append(a).append(" ");
        }

        return builder.toString();
    }

    public String writeStates(){
        StringBuilder builder = new StringBuilder();
        builder.append("States: ");
        for (String s : states){
            builder.append(s).append(" ");
        }

        return builder.toString();
    }

    public String writeFinalStates(){
        StringBuilder builder = new StringBuilder();
        builder.append("Final states: ");
        for (String fs : finalStates){
            builder.append(fs).append(" ");
        }

        return builder.toString();
    }

    public String writeTransitions(){
        StringBuilder builder = new StringBuilder();
        builder.append("Transitions: \n");
        transitions.forEach((K, V) -> {
            builder.append("<").append(K.key).append(",").append(K.value).append("> -> ").append(V).append("\n");
        });

        return builder.toString();
    }

    public boolean checkIfDFA(){
        for (Pair<String, String> p : transitions.keySet()) {
            if (transitions.get(p).size() > 1) {
                return false;
            }
        }
        return true;
    }

    public boolean checkSequence(String sequence){
        if(sequence.length() == 0)
            return finalStates.contains(initialState);

        String currentState = initialState, s = "";
        Pair p;
        for (int i = 0; i < sequence.length(); i += 1) {
            s = String.valueOf(sequence.charAt(i));
            p = new Pair(currentState, s);

            try {
                if (!transitions.get(p).isEmpty()) {
                    currentState = transitions.get(p).iterator().next();
                }
            } catch (Exception e) {
                return false;
            }
        }
        return finalStates.contains(currentState);
    }

    @Override
    public String toString() {
        return "FiniteAutomaton{" +
                "alphabet=" + alphabet +
                ", states=" + states +
                ", finalStates=" + finalStates +
                ", initialState='" + initialState + '\'' +
                ", transitions=" + transitions +
                '}';
    }
}