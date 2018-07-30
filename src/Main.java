
import java.util.Scanner;
import static Slovnik.SeznamSlov.*;

public class Main {
    public static int welcome() {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        do {
            System.out.println("Wan to play a Hangman ? yes/no");
            String answer = sc.nextLine();
            if (answer.equals(new String("yes"))) {
                System.out.println("Here is your word:");
                return 1;
            } else if (answer.equals(new String("no"))) {
                System.out.println("Too bad.");
                return 0;
            } else {
                System.out.println(answer + " is not a valid command.");
                i++;
            }
        } while (i < 100);
        return 0;
    }

    public static char[] hideWord(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = '_';
        }
        return arr;
    }

    public static void main(String[] args) {
        if (welcome() == 1) {
            char[] chars = (chooseRandom(makeSet())).toCharArray();
            char[] hiddenchars = (hideWord(chars));
            System.out.println(hiddenchars);
        } else {

        }
    }
}