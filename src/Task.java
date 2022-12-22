import java.io.*;
import java.util.Scanner;

public class Task {
    public static void main(String[] args) throws IOException{
        try {
            System.out.println("Enter name of file(txt, xml or json): ");
            Scanner scan = new Scanner(System.in);
            String name = scan.next();
            File in = FileData.Inp(name);
            File out = FileData.Outp(name);
            CountArithmExpression.CountExp(in, out);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}


