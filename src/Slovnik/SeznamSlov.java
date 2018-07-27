package Slovnik;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SeznamSlov {
    private static String transScriptor(String s) {
        return s;
    }

    public static void main(String[] args) {
        File file = new File("Slovnik/syn2010_lemma_cba.txt");
//        Scanner sc = new Scanner();
        if(file.exists()) {
            System.out.println(file.getName() +  "exists!");
        }
        else {
            System.out.println("The file does not exist");
        }
    }
}
