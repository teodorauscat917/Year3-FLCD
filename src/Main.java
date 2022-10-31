import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("---------------Problem 1----------------------");
        MyScanner scanner = new MyScanner("src/p1.txt");
        scanner.scan();
        System.out.println("----------------Problem 2---------------------");
        MyScanner scanner2 = new MyScanner("src/p2.txt");
        scanner2.scan();
        System.out.println("-------------------Problem 3------------------");
        MyScanner scanner3 = new MyScanner("src/p3.txt");
        scanner3.scan();
        System.out.println("------------------Problem error-------------------");
        MyScanner scannerErr = new MyScanner("src/p1err.txt");
        scannerErr.scan();


    }



}
