import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TXTFile {
    public static File InpTXT(String input) throws IOException {
        BufferedReader br = null;
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
    public static File OutTXT() throws IOException {
        File outputFile = new File("out.txt");
        if (!outputFile.exists())
            outputFile.createNewFile();
        return outputFile;
    }
}
