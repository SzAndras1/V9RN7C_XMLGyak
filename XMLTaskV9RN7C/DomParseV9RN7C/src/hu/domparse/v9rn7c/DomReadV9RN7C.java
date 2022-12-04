package hu.domparse.v9rn7c;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DomReadV9RN7C {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        File xmlFile = new File("V9RN7Cxml.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("bar");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            Element elem = (Element) nNode;
            String b_id = elem.getAttribute("b_id");
            String fesz_b = elem.getAttribute("fesz_b");
            System.out.println("Bár kódja: " + b_id);
            System.out.println("Fesztivál kódja: " + fesz_b);

            Node node1 = elem.getElementsByTagName("nev").item(0);
            String nev = node1.getTextContent();

            Node node2 = elem.getElementsByTagName("ital").item(0);
            String ital1 = node2.getTextContent();

            Node node3 = elem.getElementsByTagName("ital").item(1);
            String ital2 = node3.getTextContent();

            Node node4 = elem.getElementsByTagName("ital").item(2);
            String ital3 = node4.getTextContent();

            Node node5 = elem.getElementsByTagName("cegnev").item(0);
            String cegnev = node5.getTextContent();

            System.out.println("Bár neve: " + nev);
            System.out.println("Ital: " + ital1);
            System.out.println("Ital: " + ital2);
            System.out.println("Ital: " + ital3);
            System.out.println("Cég neve: " + cegnev);
        }
    }
}
