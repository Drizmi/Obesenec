package Slovnik;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.Formatter;
import java.util.Scanner;

public class SeznamSlov {
    private static int filter(String s) {
        if (s.length() < 3) {
            return 0;
        } else {
            return 1;
        }
    }
    private static String transScriptor(String s) {
        s.toLowerCase(); //changes string to lowercase
        Normalizer.normalize(s, Normalizer.Form.NFD); //normalizes string
        s.replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}+", ""); //replaces all diacritics with ""
//        char[] arr = s.toCharArray();
//        for (char el: arr) {
//
//        }
        return s;
    }
    public static void makeList() {
        File file = new File("C:\\Tutorial\\Obesenec\\src\\Slovnik\\syn2010_lemma_cba.txt");
        try {
            Scanner sc = new Scanner(file); //creates new scanner object
            try {
                Formatter f = new Formatter("C:\\Tutorial\\Obesenec\\src\\Slovnik\\seznam.txt"); //creates new file

                while (sc.hasNextLine()) { //loops if scanner finds text on line
                    switch (filter(sc.findInLine(sc.nextLine()))) {
                        case 0:
                            
                    }
                    String s = transScriptor(sc.findInLine(sc.nextLine())); //saves transcription to string s
                    f.format("%s", s + "\r\n"); //saves transcription to file

                }
                sc.close(); //scanner closes file
            } catch (FileNotFoundException sce) {
                sce.printStackTrace();
            }
        } catch (Exception fe) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {

//        if(file.exists()) {
//            System.out.println(file.getName() +  " exists!");
//        }
//        else {
//            System.out.println("The file does not exist");
//        }
    }
}
