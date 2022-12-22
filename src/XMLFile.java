import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class XMLFile {
    public static File InpXML(String input) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        DocumentBuilder builder = f.newDocumentBuilder();
        Document doc = builder.parse(new File("in.xml"));

        File txtfile = new File("filetxtxml");
        txtfile.createNewFile();
        PrintWriter pw = new PrintWriter(txtfile);

        Node root = doc.getDocumentElement();
        NodeList data = root.getChildNodes();
        for (int i = 0; i < data.getLength(); i++) {
            Node dat = data.item(i);
            if (dat.getNodeType() != Node.TEXT_NODE) {
                NodeList datalist = dat.getChildNodes();
                for(int j = 0; j < datalist.getLength(); j++) {
                    Node d = datalist.item(j);
                    if (d.getNodeType() != Node.TEXT_NODE) {
                        String str = d.getChildNodes().item(0).getTextContent();
                        pw.println(str);
                    }
                }
            }
        }
        File outputFile = new File("outxml.txt");
        CountArithmExpression.CountExp(txtfile, outputFile);

        return outputFile;
    }
    public static File OutXML() throws IOException {
        File outputFile = new File("outxml.txt");
        if (!outputFile.exists())
            outputFile.createNewFile();
        return outputFile;
    }
}
