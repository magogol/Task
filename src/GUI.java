import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GUI extends JFrame {
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
            } catch(IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
    class Button2EventListener implements ActionListener {
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
            } catch(IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
    class Button3EventListener implements ActionListener {
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
            } catch(IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
}