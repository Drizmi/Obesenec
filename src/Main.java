
import java.util.Scanner;

import static Slovnik.SeznamSlov.*;

public class Main {
    public static String w0;
    public static String answer;
    public static String w1;
    public static int tries;
    public static int play;
    public static int i = 0;
    public static char[] word;
    public static Scanner sc = new Scanner(System.in);

    private static int welcome() {
        String y = "yes",n = "no";
        do {
            System.out.println("Want to play a Hangman ? yes/no");
            answer = sc.nextLine();
            if (answer.equals(y)) {
                System.out.println("Here is your word:");
                return 15;
            } else if (answer.equals(n)) {
                return -1;
            } else {
                System.out.println(answer + " is not a valid command.");
                i++;
            }
        } while (i < 100);
        return -2;
    }

    private static int playSwitch(int play) {
        Scanner sc = new Scanner(System.in);
        String a = "again", n = "next", q = "quit", yes = "yes", no = "no";
        if (play == 2 || play == 3) {
            if (play == 3) {
                System.out.println("Congratulation, type 'next' for another round or 'quit' for exit");
            } else {
                System.out.println("Too bad, you are out of tries. The word you were guessing was " + w0);
                System.out.println("If you want to try again, type 'again'. If you want to quit, type 'quit'.");
            }
            answer = sc.next();
            if (answer.equals(n) || answer.equals(a)) {
                play = 15;
            } else if (answer.equals(q)) {
                play = -1;
            } else {
                System.out.println(answer + " is not a valid command.");
                ++i;
                if (i == 100) {
                    play = -2;
                }
            }
        } else if (play == 1) {
            System.out.println("So you want to play ? yes/no");
            answer = sc.next();
            if (answer.equals(no)) {
                play = -1;
            } else if (answer.equals(yes)) {
                play = 15;
            } else {
                System.out.println(answer + " is not a valid command.");
                ++i;
                if (i == 100) {
                    play = -2;
                }
            }
        } else if (play == -1) {
            System.out.println("Do you really want to quit? yes/no");
            answer = sc.next();
            if (answer.equals(yes)) {
                play = 0;
            } else if (answer.equals(no)) {
                play = 1;
            } else {
                System.out.println(answer + " is not a valid command.");
                ++i;
                if (i == 100) {
                    play = -2;
                }
            }
        } else if (play == -2) {
            System.out.println("That's enough, you are annoying !!");
            play = 0;
        }
        return play;
    }

    private static int guessing(int tries){
        return play;
    }


    private static String hideWord(char[] arr) {
        String w = " ";
        for (int t = 0; t < arr.length; t++) {
            w = w + "_ ";
        }
        return w;
    }

    //    public static int guess(int tries, String word
    //, char[] word,) {
//        if (tries > 0 ) {
//            Scanner sc = new Scanner(System.in);
//            System.out.println("Guessed word: " +);
//            System.out.println("Remaining tries : " + tries);
//            System.out.println("Enter your guess (character/word): ");
//            String answer = sc.next();
//            guess(tries, word
//, word,);
//        }
//        return 2;
//    }

    public static void main(String[] args) {
        play = welcome();
        while (play != 0) {
            if (play == 15) {
                tries = 15;
            }
            while (tries > 0) {
                w0 = chooseRandom(makeSet());
                word = (w0).toCharArray();   //array of characters of word
                w1 = hideWord(word);  //word in format _ _ _ _ _ ...
                System.out.println("The word you are guessing: " + w1);
                System.out.println("Remaining tries : " + tries);
                System.out.print("Enter your guess (character/word) or type '/quit' for exit: ");
                answer = sc.next();
                String q;
                if (answer.equals(w0)) {
                    System.out.println("Well done, the word you were guessing was " + w0 + ".");
                    System.out.println("Your score is: " + tries);
                    play = 3;
                    break;
                } else if (answer.length() == 1 && w0.contains(answer)) {
                    play = 2;
                    continue;
                } else if (answer.equals(q = "/quit")) {
                    play = -1;
                    break;
                } else if (answer.length() == 1 && !w0.contains(answer)) {
                    System.out.println("Wrong, " + answer + " is not in the word you are guessing.");
                    --tries;
                } else {
                    System.out.println("Wrong, " + answer + " is not the word you are guessing");
                    --tries;
                }
            }
            play = playSwitch(play);
        }
    }
}
