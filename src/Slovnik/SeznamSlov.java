package Slovnik;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SeznamSlov {
    private static boolean filter(String s) { //if string is only 3 characters long returns true
        if (s.length() <= 3) {
            return true;
        }
        return false;
    }

    private static String transScriptor(String s) {
        s = s.toLowerCase(); //changes string to lowercase !!!NEFUNGUJE!!!
        return Normalizer.normalize(s, Normalizer.Form.NFD) //normalizes string !!!NEFUNGUJE!!!
                .replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}+", ""); //replaces all diacritics with "" !!!NEFUNGUJE!!!
    }


    public static List<String> makeList() {
        File file = new File("syn2010_lemma_cba.txt");
        List<String> l = new LinkedList();
        try {
            Scanner sc = new Scanner(file, "windows-1250"); //creates new scanner object
            while (sc.hasNextLine()) { //loops if scanner finds text on line
                String string = sc.findInLine("[\\p{IsLatin}]+"); //saves next string
                if (filter(string)) {
                    string = sc.findInLine("[\\d]+"); //finds numbers in line
                    sc.nextLine();
                    continue;
                }
                String out = transScriptor(string); //saves transcription to string s
                l.add(out);


                string = sc.findInLine("[\\d]+"); //finds numbers in line
                sc.nextLine();
            }
            sc.close(); //scanner closes file
        } catch (FileNotFoundException sce) {
            System.out.println("File not found");
            sce.printStackTrace();
        }
        return l;
    }

    public static void main(String[] args) {
        int i = 0;
        for (String el : makeList()) {
            i++;
            System.out.println(el);
            if (i > 10) {
                break;
            }
        }
    }
}
