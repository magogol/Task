import java.io.*;
import java.util.Scanner;

public class FileData {
    public static File Inp(String input) throws IOException {
        BufferedReader br = null;
        File inputFile = null;
        Source so = new Encryption(new DataFile(input));
        Data dat = new DataFile(input);
        br = new BufferedReader(new FileReader(input));
        String str;
        PrintWriter pwin = new PrintWriter(inputFile);
        while ((str = br.readLine()) != null) {
            so.writeData(str);
            so.readData();
            pwin.println(so.readData());
        }

        System.out.println("Encoded: ");
        System.out.println(dat.readData());
        System.out.println("Decoded: ");
        System.out.println(so.readData());

        if(!inputFile.exists()) {
            inputFile.createNewFile();
            PrintWriter pw = new PrintWriter(inputFile);
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter content of file: ");
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                pw.println(s);
            }
        }
        return inputFile;
    }
    public static File Outp(String input) throws IOException {
        File out = null;
        if(input.matches("\\w+\\.txt")){
            out = new File("out.txt");
        }
        if(input.matches("\\w+\\.xml")){
            out = new File("out.xml");
        }
        if(input.matches("\\w+\\.json")){
            out = new File("out.json");
        }
        if (!out.exists())
            out.createNewFile();
        return out;
    }
}
