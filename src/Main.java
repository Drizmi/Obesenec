import java.io.File;
import static Slovnik.SeznamSlov.makeList;

public class Main {
    public static void main(String[] args) {
        File seznam = new File("C:\\Tutorial\\Obesenec\\src\\Slovnik\\seznam.txt");
        if(seznam.exists()) {
            System.out.println(seznam.getName() +  " exists!");
        }
        else {
            makeList();
        }
        
    }
}
