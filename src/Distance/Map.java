package Distance;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Никита on 21.01.2017.
 */
public class Map {
    public Point getPoint(String fileName) {
        Point point = new Point();
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(fileName);
            Element geometry = (Element) document.getElementsByTagName("location").item(0);
            NodeList nodeList = geometry.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node el = nodeList.item(i);
                if (el.getNodeType() != Node.TEXT_NODE) {
                    if (el.getNodeName().equals("lat"))
                        point.setLat(Double.valueOf(el.getChildNodes().item(0).getTextContent()));
                    else
                        point.setLng(Double.valueOf(el.getChildNodes().item(0).getTextContent()));
                    //System.out.println(el.getNodeName() + " " + el.getChildNodes().item(0).getTextContent());
                    //System.out.println(point.toString());
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return point;
    }
}

