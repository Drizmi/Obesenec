
import java.util.Scanner;

import static Slovnik.SeznamSlov.*;

public class Main {
    public static String w0;    //guessing word
    private static String w1 = "";    //word as  _ _ _ _ _ ...
    private static String w2;
    private static String answer;    //input
    private static int tries;    //number of tries
    public static int play;
    private static int enough = 0;
    public static int i;
    public static char[] word;  //array of charracters from word w0
    public static Scanner sc = new Scanner(System.in);
    private static char[] w1arr;

    private static int welcome() {
        String y = "yes", n = "no";
        do {
            System.out.println("Want to play a Hangman ? yes/no");
            answer = sc.nextLine();
            if (answer.equals(y)) {
                System.out.println("Here is your word:");
                return 16;
            } else if (answer.equals(n)) {
                return -1;
            } else {
                System.out.println(answer + " is not a valid command.");
                enough++;
            }
        } while (enough < 100);
        return -2;
    }

    private static void wordGenerator() {
        w0 = chooseRandom(makeSet());
        word = (w0).toCharArray();   //array of characters of word
        w1 = hideWord(word);  //word in format _ _ _ _ _ ...
    }

    private static int playSwitch(int play) {
        Scanner sc = new Scanner(System.in);
        String a = "again", n = "next", q = "quit", yes = "yes", no = "no";
        if (play == 2 || play == 3) {
            if (play == 3) {
                System.out.println("Congratulation, the word you were guessing was " + w0 + " type 'next' for another round or 'quit' for exit");
            } else {
                System.out.println("Too bad, you are out of tries. The word you were guessing was " + w0);
                System.out.println("If you want to try again, type 'again'. If you want to quit, type 'quit'.");
            }
            answer = sc.next();
            if (answer.equals(n) || answer.equals(a)) {
                play = 16;
            } else if (answer.equals(q)) {
                play = -1;
            } else {
                System.out.println(answer + " is not a valid command.");
                ++enough;
                if (enough == 100) {
                    play = -2;
                }
            }
        } else if (play == 1) {
            System.out.println("So you want to play ? yes/no");
            answer = sc.next();
            if (answer.equals(no)) {
                play = -1;
            } else if (answer.equals(yes)) {
                play = 16;
            } else {
                System.out.println(answer + " is not a valid command.");
                ++enough;
                if (enough == 100) {
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
                ++enough;
                if (enough == 100) {
                    play = -2;
                }
            }
        } else if (play == -2) {
            System.out.println("That's enough, you are annoying !!");
            play = 0;
        }
        return play;
    }

    private static int guessing(int tries, int play) {
        if (play == 16) {
            tries = 16;
        }
        while (tries > 0 && play != 3) {
            if (tries == 16) {
                wordGenerator();
                --tries;
            } else if (w1.contains("_")) {
                System.out.println("The word you are guessing: " + w1);
                System.out.println("Remaining tries : " + tries);
                System.out.println("Enter your guess (character/word) or type '/quit' for exit: ");
                answer = sc.next();
                if (answer.equals("/quit")) {
                    play = -1;
                    break;
                } else if (w0.contains(answer)) {
                    char[] guess = answer.toCharArray();
                    for (char el : guess) {
                        int index = w0.indexOf(el);
                        while (index >= 0) {
                            word[index] = '_';
                            w1arr = w1.toCharArray();
                            w1arr[((index * 2) + 1)] = el;
                            w1 = new String(w1arr);
                            index = w0.indexOf(el, index + 1);
                        }
                    }
                } else if (answer.equals(w0)) {
                    System.out.println("Well done, the word you were guessing was " + w0 + ".");
                    System.out.println("Your score is: " + tries);
                    play = 3;
                    break;
                } else if (answer.length() == 1 && !w0.contains(answer)) {
                    System.out.println("Wrong, " + answer + " is not in the word you are guessing.");
                    --tries;
                    play = 2;
                } else {
                    System.out.println("Wrong, " + answer + " is not the word you are guessing");
                    --tries;
                    play = 2;
                }
            } else {
                play = 3;
            }
        }
        return play;
    }

    private static String hideWord(char[] arr) {
        String w = " ";
        for (int t = 0; t < arr.length; t++) {
            w = w + "_ ";
        }
        return w;
    }

    public static void main(String[] args) {
        play = welcome();
        while (play != 0) {
            while (play == 16) {
                play = guessing(tries, play);
            }
            play = playSwitch(play);
        }
    }
}
