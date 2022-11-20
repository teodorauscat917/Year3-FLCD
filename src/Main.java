import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

//        System.out.println("---------------Problem 1----------------------");
//        MyScanner scanner = new MyScanner("src/p1.txt");
//        scanner.scan();
//        System.out.println("----------------Problem 2---------------------");
//        MyScanner scanner2 = new MyScanner("src/p2.txt");
//        scanner2.scan();
//        System.out.println("-------------------Problem 3------------------");
//        MyScanner scanner3 = new MyScanner("src/p3.txt");
//        scanner3.scan();
//        System.out.println("------------------Problem error-------------------");
//        MyScanner scannerErr = new MyScanner("src/p1err.txt");
//        scannerErr.scan();
        optionsForDFA();


    }

    private static void runMenu() {
        System.out.println("1. States");
        System.out.println("2. Alphabet");
        System.out.println("3. Final states.");
        System.out.println("4. Transitions");
        System.out.println("5. Verify sequence");
        System.out.println("0. Exit");
    }

    private static void optionsForDFA() {
        FiniteAutomaton finiteAutomaton = new FiniteAutomaton("src/FiniteAutomata.in");

        System.out.println("FA read from file.");
        runMenu();
        System.out.println("Your option: ");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(finiteAutomaton.writeStates());
                case 2 -> System.out.println(finiteAutomaton.writeAlphabet());
                case 3 -> System.out.println(finiteAutomaton.writeFinalStates());
                case 4 -> System.out.println(finiteAutomaton.writeTransitions());
                case 5 -> {
                    if (finiteAutomaton.checkIfDFA()) {
                        System.out.println("Your sequence: ");
                        Scanner scanner2 = new Scanner(System.in);
                        String sequence = scanner2.nextLine();

                        if (finiteAutomaton.checkSequence(sequence))
                            System.out.println("Sequence is valid");
                        else
                            System.out.println("Invalid sequence");
                    } else {
                        System.out.println("FA is not deterministic.");
                    }
                }
            }
            System.out.println();
            runMenu();
            System.out.println("Your option: ");
            option = scanner.nextInt();
        }
    }


}