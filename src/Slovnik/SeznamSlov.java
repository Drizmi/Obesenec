package Slovnik;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.Formatter;
import java.util.Scanner;

public class SeznamSlov {
    private static boolean filter(String s) { //if string is only 3 characters long returns true
        if (s.length() <= 3 ) {
            return true;
        }
        return false;
    }

    private static String transScriptor(String s) {
        s.toLowerCase(); //changes string to lowercase !!!NEFUNGUJE!!!
        Normalizer.normalize(s, Normalizer.Form.NFD); //normalizes string !!!NEFUNGUJE!!!
        s.replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}+", ""); //replaces all diacritics with "" !!!NEFUNGUJE!!!
//        char[] arr = s.toCharArray();
//        for (char el: arr) {
//
//        }
        return s;
    }

    public static void makeList() {
        File file = new File("syn2010_lemma_cba.txt");
        String string;
        try {
            Scanner sc = new Scanner(file,  "windows-1250"); //creates new scanner object
            while (sc.hasNextLine()) { //loops if scanner finds text on line
                string = sc.findInLine("[\\p{IsLatin}]+"); //saves next string
                if (filter(string)) {
                    string = sc.findInLine("[\\d]+"); //finds numbers in line
                    sc.nextLine();
                    continue;
                }


                String out = transScriptor(string); //saves transcription to string s

                System.out.println(out);
                string = sc.findInLine("[\\d]+"); //finds numbers in line
                sc.nextLine();
            }
            sc.close(); //scanner closes file
        } catch (FileNotFoundException sce) {
            System.out.println("File not found");
            sce.printStackTrace();
        }
    }

    public static void main(String[] args) {
        makeList();
//        if(file.exists()) {
//            System.out.println(file.getName() +  " exists!");
//        }
//        else {
//            System.out.println("The file does not exist");
//        }
    }
}
