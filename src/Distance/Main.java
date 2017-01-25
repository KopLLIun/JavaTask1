package Distance;
import JavaForm.Start;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.swing.*;

/**
 * Created by Никита on 21.01.2017.
 */
public class Main {
    public static void main(String[] args) {
        Start start = new Start("Distance");
        start.setVisible(true);
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.setSize(500,100);
        start.setResizable(false);
        start.setLocationRelativeTo(null);
    }
}