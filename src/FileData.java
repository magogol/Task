import java.io.*;
import java.util.Scanner;

public class FileData {
    public static File Inp(String input) throws IOException {
        File inputFile = new File(input);
        if(!inputFile.exists()) {
            inputFile.createNewFile();
            PrintWriter pwin = new PrintWriter(inputFile);
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter content of file: ");
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                pwin.println(s);
            }
        }
        return inputFile;
    }
    public static File Outp(String output) throws IOException {
        File outputFile = new File(output);
        if (!outputFile.exists())
            outputFile.createNewFile();
        return outputFile;
    }
}
