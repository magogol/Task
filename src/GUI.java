import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GUI extends JFrame{
    private JButton button1 = new JButton("Txt");
    private JButton button2 = new JButton("Xml");
    private JButton button3 = new JButton("Json");
    private JLabel label = new JLabel("Input name of file: ");
    private JTextField input = new JTextField("", 7);

    private JLabel label2 = new JLabel("Choose the type of file you want to create: ");

    public GUI(){
        super("Data");
        this.setBounds(200,200,700,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));
        container.add(label);
        container.add(input);
        container.add(label2);

        button1.addActionListener(new Button1EventListener ());
        button2.addActionListener(new Button2EventListener ());
        button3.addActionListener(new Button3EventListener ());

        container.add(button1);
        container.add(button2);
        container.add(button3);
    }
    class Button1EventListener implements ActionListener {
        private String[] file = {"Zip", "Encrypt"};
        private ImageIcon icon = null;
        public void actionPerformed (ActionEvent e){
            String mess = "The content of output txt file:\n\n";
            String mess2 = "The content of input txt file:\n\n";
            try {
                File in = FileData.Inp(input.getText() + ".txt");
                File out = FileData.Outp("out.txt");
                CountArithmExpression.CountExp(in, out);
                BufferedReader br = new BufferedReader(new FileReader(out));
                String s;
                while ((s = br.readLine()) != null) {
                    mess += s;
                    mess += "\n";
                }
                BufferedReader br2 = new BufferedReader(new FileReader(in));
                String s2;
                while ((s2 = br2.readLine()) != null) {
                    mess2 += s2;
                    mess2 += "\n";
                }
                JOptionPane.showMessageDialog(null,mess2,"Input",JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null,mess,"Output",JOptionPane.PLAIN_MESSAGE);
                Object res = JOptionPane.showInputDialog(null,"Choose:","File", JOptionPane.QUESTION_MESSAGE, icon, file, file[0]);
                switch(res.toString()){
                    case "Zip":
                        FileData.Zip(input.getText() + ".txt", "compressedTxt.zip");
                        JOptionPane.showMessageDialog(null, "file was zipped");
                        Object o = JOptionPane.showConfirmDialog(null,"Unzip file?", "unzip?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
                        System.out.println(o);
                        if(o.toString().matches("0"))
                            FileData.UnZip("compressedTxt.zip", "unzip.txt");
                        break;
                    case "Encrypt":
                        SecretKey key = FileData.generateKey(128);
                        String algorithm = "AES/CBC/PKCS5Padding";
                        IvParameterSpec ivParameterSpec = FileData.generateIv();
                        File inputFile = Paths.get(input.getText() + ".txt").toFile();
                        File encryptedFile = new File("inTxt.encrypted");
                        File decryptedFile = new File("inTxt.decrypted");
                        FileData.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
                        JOptionPane.showMessageDialog(null, "file was encrypted");
                        Object o2 = JOptionPane.showConfirmDialog(null,"Decrypt file?", "decrypt?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
                        System.out.println(o2);
                        if(o2.toString().matches("0"))
                            FileData.decryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);
                        break;
                }
            } catch(IOException | NoSuchAlgorithmException | NoSuchPaddingException |
                    InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException |
                    IllegalBlockSizeException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
    class Button2EventListener implements ActionListener {
        private String[] file = {"Zip", "Encrypt"};
        private ImageIcon icon = null;
        public void actionPerformed (ActionEvent e){
            String mess = "The content of output xml file:\n\n";
            String mess2 = "The content of input xml file:\n\n";
            try {
                File in = FileData.Inp(input.getText() + ".xml");
                File out = FileData.Outp("out.xml");
                CountArithmExpression.CountExp(in, out);
                BufferedReader br = new BufferedReader(new FileReader(out));
                BufferedReader br2 = new BufferedReader(new FileReader(in));
                String s, s2;
                while ((s = br.readLine()) != null) {
                    mess += s;
                    mess += "\n";
                }
                while ((s2 = br2.readLine()) != null) {
                    mess2 += s2;
                    mess2 += "\n";
                }
                JOptionPane.showMessageDialog(null,mess2,"Input",JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null,mess,"Output",JOptionPane.PLAIN_MESSAGE);
                Object res = JOptionPane.showInputDialog(null,"Choose:","File", JOptionPane.QUESTION_MESSAGE, icon, file, file[0]);
                switch(res.toString()){
                    case "Zip":
                        FileData.Zip(input.getText() + ".xml", "compressedXml.zip");
                        JOptionPane.showMessageDialog(null, "file was zipped");
                        Object o = JOptionPane.showConfirmDialog(null,"Unzip file?", "unzip?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
                        if(o.toString().matches("0"))
                            FileData.UnZip("compressedXml.zip", "unzip.xml");
                        break;
                    case "Encrypt":
                        SecretKey key = FileData.generateKey(128);
                        String algorithm = "AES/CBC/PKCS5Padding";
                        IvParameterSpec ivParameterSpec = FileData.generateIv();
                        File inputFile = Paths.get(input.getText() + ".xml").toFile();
                        File encryptedFile = new File("inXml.encrypted");
                        File decryptedFile = new File("inXml.decrypted");
                        FileData.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
                        JOptionPane.showMessageDialog(null, "file was encrypted");
                        Object o2 = JOptionPane.showConfirmDialog(null,"Decrypt file?", "decrypt?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
                        System.out.println(o2);
                        if(o2.toString().matches("0"))
                            FileData.decryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);
                        break;
                }
            } catch(IOException | NoSuchAlgorithmException | NoSuchPaddingException |
                    InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException |
                    IllegalBlockSizeException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
    class Button3EventListener implements ActionListener {
        private String[] file = {"Zip", "Encrypt"};
        private ImageIcon icon = null;
        public void actionPerformed (ActionEvent e){
            String mess = "The content of output json file:\n\n";
            String mess2 = "The content of input json file:\n\n";
            try {
                File in = FileData.Inp(input.getText() + ".json");
                File out = FileData.Outp("out.json");
                CountArithmExpression.CountExp(in, out);
                BufferedReader br = new BufferedReader(new FileReader(out));
                String s;
                while ((s = br.readLine()) != null) {
                    mess += s;
                    mess += "\n";
                }
                BufferedReader br2 = new BufferedReader(new FileReader(in));
                String s2;
                while ((s2 = br2.readLine()) != null) {
                    mess2 += s2;
                    mess2 += "\n";
                }
                JOptionPane.showMessageDialog(null,mess2,"Input",JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null,mess,"Output",JOptionPane.PLAIN_MESSAGE);
                Object res = JOptionPane.showInputDialog(null,"Choose:","File", JOptionPane.QUESTION_MESSAGE, icon, file, file[0]);
                switch(res.toString()){
                    case "Zip":
                        FileData.Zip(input.getText() + ".json", "compressedJson.zip");
                        JOptionPane.showMessageDialog(null, "file was zipped");
                        Object o = JOptionPane.showConfirmDialog(null,"Unzip file?", "unzip?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
                        if(o.toString().matches("0"))
                            FileData.UnZip("compressedJson.zip", "unzip.json");
                        break;
                    case "Encrypt":
                        SecretKey key = FileData.generateKey(128);
                        String algorithm = "AES/CBC/PKCS5Padding";
                        IvParameterSpec ivParameterSpec = FileData.generateIv();
                        File inputFile = Paths.get(input.getText() + ".json").toFile();
                        File encryptedFile = new File("inJson.encrypted");
                        File decryptedFile = new File("inJson.decrypted");
                        FileData.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
                        JOptionPane.showMessageDialog(null, "file was encrypted");
                        Object o2 = JOptionPane.showConfirmDialog(null,"Decrypt file?", "decrypt?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
                        System.out.println(o2);
                        if(o2.toString().matches("0"))
                            FileData.decryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);
                        break;
                }
            } catch(IOException | NoSuchAlgorithmException | NoSuchPaddingException |
                    InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException |
                    IllegalBlockSizeException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

}
