
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Random;

import static Slovnik.SeznamSlov.makeList;

public class Main {
    public static void welcome() {
        Scanner sc = new Scanner(System.in);
        int q = 0;
        while (q != 1) {
            System.out.println("Wan to play a Hangman ? yes/no");
            String answer = sc.nextLine();
            if (answer.equals(new String("no"))) {
                System.out.println("Too bad.");
                break;
            } else if (answer.equals(new String("yes"))) {
                System.out.println("Let's begin.");
                System.out.println("Goodbye.");
            } else {
                System.out.println(answer + " is not a valid command.");
            }
            continue;
        }
    }

    public static void main(String[] args) {
        welcome();


    }
}