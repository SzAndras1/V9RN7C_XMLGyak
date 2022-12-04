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

class DomReadV9RN7C {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        //Xml fájl megnyitása és DOM incializálása
        File xmlFile = new File("V9RN7Cxml.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        //Node listát csinálás a kiválasztott elem gyerekelemeiből
        NodeList nList = doc.getElementsByTagName("bar");
        //Bejárja a gyerekelemeket
        for (int i = 0; i < nList.getLength(); i++) {
            //Megkönnyíti a különböző node-ok megkülönböztetését ez a sor
            Node nNode = nList.item(i);
            //Gyökérelem neve
            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            //Aktuális node elemmé konvertálás
            Element elem = (Element) nNode;
            //Attribútumok kigyűjtése
            String b_id = elem.getAttribute("b_id");
            String fesz_b = elem.getAttribute("fesz_b");
            System.out.println("Bár kódja: " + b_id);
            System.out.println("Fesztivál kódja: " + fesz_b);

            //Gyerekelemek kiválasztása és megfelelő adattípus szerint konvertálja
            Node node1 = elem.getElementsByTagName("nev").item(0);
            String nev = node1.getTextContent();

            Node node5 = elem.getElementsByTagName("cegnev").item(0);
            String cegnev = node5.getTextContent();

            System.out.println("Bár neve: " + nev);
            for (int j = 0; j < 3; j++) {
                Node node2 = elem.getElementsByTagName("ital").item(j);
                String ital = node2.getTextContent();
                System.out.println("Ital neve: " + ital);
            }
            System.out.println("Cég neve: " + cegnev);
        }
    }
}
