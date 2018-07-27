import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static Slovnik.SeznamSlov.makeList;

public class Main {
    public static void main(String[] args) {
        try {
            File x = new File("syn2010_lemma_cba.txt");
            Scanner sc = new Scanner(x);
//            Files.readAllLines(Paths.get("syn2010_lemma_cba.txt"), Charset.forName("windows-1250")).forEach(System.out::println);
            while(sc.hasNext()) {
                System.out.println(sc.next());
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
//        File seznam = new File("C:\\Tutorial\\Obesenec\\src\\Slovnik\\seznam.txt");
//        if(seznam.exists()) {
//            System.out.println(seznam.getName() +  " exists!");
//        }
//        else {
//            makeList();
//        }
    }
}
