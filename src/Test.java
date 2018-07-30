import java.util.Scanner;

public class Test {
    public static void main (String[] args){
        String w0 = "Aha";
        char[] word = {'a','h','a'};
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        for (char el: word) {
            if (answer.equals(el)) {
                int ix = w0.indexOf(el);
                System.out.println(ix);
            }
        }
    }
}
