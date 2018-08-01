
import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.*;


public class Main {
    public static String w0;    //guessing word
    private static String w1 = "";    //word as  _ _ _ _ _ ...
    private static String answer;    //input
    private static Set<String> set = makeSet();
    public static int play;
    private static int difficulty;
    private static int enough = 0;
    public static char[] word;  //array of characters from word w0
    public static Scanner sc = new Scanner(System.in);
    public static Iterator<String> it = set.iterator();

    private static boolean filter(String s) { //if string is only 3 characters long returns true
        return s.length() <= 3;
    }

    private static String transScriptor(String s) {
        s = s.toLowerCase(); //changes string to lowercase
        return Normalizer.normalize(s, Normalizer.Form.NFD) //normalizes string
                .replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}+", ""); //replaces all diacritics with ""
    }

    private static Set<String> makeSet() {
        File file = new File("syn2010_lemma_cba.txt");
        Set<String> set = new LinkedHashSet<String>();
        try {
            Scanner sc = new Scanner(file, "windows-1250"); //creates new scanner object
            while (sc.hasNextLine()) { //loops if scanner finds text on line
                String string = sc.findInLine("[\\p{IsLatin}]+"); //saves next string
                if (filter(string)) {
                    sc.nextLine();
                    continue;
                }
                String out = transScriptor(string); //saves transcription to string s
                set.add(out);
                sc.nextLine();
            }
            sc.close(); //scanner closes file
        } catch (FileNotFoundException sce) {
            System.out.println("File not found");
            sce.printStackTrace();
        }
        return set;
    }

    private static String chooseRandom(Set s) {
        Random rand = new Random(); //creates new RNG
        int rnum = rand.nextInt(s.size()); //rolls random number
        Iterator<String> it = s.iterator(); //creates new iterator
        int i = 1; //counter set on 1 (1st element in set)
        while (i != rnum && it.hasNext()) {
            if (i == s.size()) {
                break;
            }
            it.next();
            i++;
        }
        String string = it.next();
        return (string); //returns random element from set of strings
    }

    private static int welcome() {
        do {
            System.out.println("Want to play a Hangman ? yes/no");
            switch (sc.nextLine()) {
                case "yes":
                    return 16;
                case "no":
                    return -1;
                default:
                    System.out.println(answer + " is not a valid command.");
                    enough++;
            }
        } while (enough < 100);
        return -2;
    }

    private static int chooseDifficulty() {
        System.out.println("Choose your difficulty (easy/medium/hard):");
        while (enough < 100) {
            switch (sc.next()) {
                case "easy":
                    return 1;
                case "medium":
                    return 2;
                case "hard":
                    return 3;
                default:
                    ++enough;
                    System.out.println("That is not an option.");
            }
        }
        return 0;
    }

    private static void wordGenerator() {
        int min = 0;
        int max = 22;
        int l;
        switch (difficulty) {
            case 1:
                min = 4;
                max = 9;
                break;
            case 2:
                min = 10;
                max = 16;
                break;
            case 3:
                min = 17;
                max = 22;
                break;
        }
        w0 = chooseRandom(set);
        l = w0.length();
        while (l < min && l > max && it.hasNext()) {
            w0 = it.next();
            l = w0.length();
        }
        word = (w0).toCharArray();   //array of characters of word
        w1 = hideWord(word);  //word in format _ _ _ _ _ ...
    }

    private static int playSwitch(int play) {
        switch (play) {
            case 3:
                System.out.println("Congratulation, the word you were guessing was " + w0 + " type 'next' for another round or 'quit' for exit");
                break;
            case 2:
                System.out.println("Too bad, you are out of tries. The word you were guessing was " + w0);
                System.out.println("If you want to try again, type 'again'. If you want to quit, type 'quit'.");
                break;
            case 1:
                System.out.println("So you want to play ? yes/no");
                break;
            case -1:
                System.out.println("Do you really want to quit? y/n");
                break;
            case -2:
                System.out.println("That's enough, you are annoying !!");
                play = 0;
                break;
            default:
                System.out.println(answer + " is not a valid command.");
                ++enough;
                if (enough == 100) {
                    play = -2;
                }
                break;
        }
        switch (answer = sc.next()) {
            case "yes":
            case "again":
            case "next":
                play = 16;
                break;
            case "quit":
            case "no":
                play = -1;
                break;
            case "y":
                play = 0;
                break;
            case "n":
                play = 1;
                break;
            default:
                break;
        }
        return play;
    }

    private static int guessing(int play) {
        int tries = 0;
        if (play == 16) {
            difficulty = chooseDifficulty();
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
                            char[] w1arr = w1.toCharArray();
                            w1arr[((index * 2) + 1)] = el;
                            w1 = new String(w1arr);
                            index = w0.indexOf(el, index + 1);
                        }
                    }
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
                System.out.println("Well done, the word you were guessing was " + w0 + ".");
                System.out.println("Your score is: " + tries);
            }
        }
        return play;
    }

    private static String hideWord(char[] arr) {
        String w = " ";
        for (char el : arr) {
            w = w + "_ ";
        }
        return w;
    }

    public static void main(String[] args) {
        play = welcome();
        while (play != 0) {
            while (play == 16) {
                play = guessing(play);
            }
            play = playSwitch(play);
        }
    }
}
