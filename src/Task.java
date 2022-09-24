import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class Task {
    public static void main(String[] args) {
        try(FileInputStream in=new FileInputStream("C://Users//User//IdeaProjects//Task//in.txt");
            FileOutputStream out=new FileOutputStream("C://Users//User//IdeaProjects//Task//out.txt"))
        {
            byte[] buff = new byte[in.available()];
            in.read(buff, 0, buff.length);
            out.write(buff, 0, buff.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
