
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static Slovnik.SeznamSlov.makeList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = 1;
        while (i < 100) {
            System.out.println("Wan to play a Hangman ? yes/no");
            String answer = sc.nextLine();
            if (answer.equals(new String("no"))) {
                System.out.println("Too bad.");
                break;
            } else if (answer.equals(new String("yes"))) {
                System.out.println("Let's begin.");
                break;
            } else {
                System.out.println(answer + " is not a valid command.");
                i++;
            }
            continue;
        }
        System.out.println("Ok that's enough.");
    }
}
