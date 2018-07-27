package Slovnik;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class SeznamSlov {
    private static String transScriptor(String s) {

        return s;
    }

    public static void main(String[] args) {
        File file = new File("C:\\Tutorial\\Obesenec\\src\\Slovnik\\syn2010_lemma_cba.txt");
        try {
            Scanner sc = new Scanner(file);
            try {
                Formatter f = new Formatter("C:\\Tutorial\\Obesenec\\src\\Slovnik\\seznam.txt");

                while (sc.hasNextLine()) {

                    String s = transScriptor(sc.findInLine());
                    f.format("%s", s + "\r\n");

                }
                sc.close();
            } catch (FileNotFoundException sce) {
                sce.printStackTrace();
            }
        } catch (Exception fe) {
            System.out.println("Error");
        }
//        if(file.exists()) {
//            System.out.println(file.getName() +  " exists!");
//        }
//        else {
//            System.out.println("The file does not exist");
//        }
    }
}
