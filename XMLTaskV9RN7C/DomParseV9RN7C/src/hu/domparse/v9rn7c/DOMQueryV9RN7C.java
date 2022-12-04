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

class DomQueryV9RN7C {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        //Xml fájl megnyitása és DOM incializálása
        File xmlFile = new File("V9RN7Cxml.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Azoknak az alkalmazottak neve, akik többet kapnak, mint 400.000:");
        getBySalary(doc);
        System.out.println("Azoknak a bároknak a neve, akik árulnak vodkát:");
        getIfContainsSpecificDrink(doc);
        System.out.println("Azok a fellépőknek a neve, akik német állampolgárok:");
        getByCountryName(doc);
        System.out.println("Azoknak az alkalmazottnak neve, akik dolgoztak szombaton:");
        getByShift(doc);
        System.out.println("Azoknak a résztevőknek a neve, akik diákok:");
        getByBoolean(doc);

    }

    private static void getByBoolean(Document doc) {
        NodeList nList = doc.getElementsByTagName("resztvevo");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element elem = (Element) nNode;

            Node node1 = elem.getElementsByTagName("nev").item(0);
            String nev = node1.getTextContent();

            Node node2 = elem.getElementsByTagName("diakigazolvany").item(0);
            Boolean diak = Boolean.parseBoolean(node2.getTextContent());
            if (diak)
                System.out.println('\t' + nev);
        }
    }

    private static void getByShift(Document doc) {
        NodeList nList = doc.getElementsByTagName("alkalmazott");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element elem = (Element) nNode;

            Node node1 = elem.getElementsByTagName("nev").item(0);
            String nev = node1.getTextContent();

            Node node2 = elem.getElementsByTagName("muszak").item(0);
            String muszak1 = node2.getTextContent();

            Node node3 = elem.getElementsByTagName("muszak").item(1);
            String muszak2 = node3.getTextContent();

            if (muszak1.contains("szombat") || muszak2.contains("szombat"))
                System.out.println('\t' + nev);
        }
    }

    private static void getByCountryName(Document doc) {
        NodeList nList = doc.getElementsByTagName("fellepo");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element elem = (Element) nNode;

            Node node1 = elem.getElementsByTagName("nev").item(0);
            String nev = node1.getTextContent();

            Node node2 = elem.getElementsByTagName("orszag").item(0);
            String orszag = node2.getTextContent();

            if (orszag.equalsIgnoreCase("németország"))
                System.out.println('\t' + nev);
        }
    }

    private static void getIfContainsSpecificDrink(Document doc) {
        NodeList nList = doc.getElementsByTagName("bar");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element elem = (Element) nNode;

            Node node1 = elem.getElementsByTagName("nev").item(0);
            String nev = node1.getTextContent();

            for (int j = 0; j < 3; j++) {
                Node node2 = elem.getElementsByTagName("ital").item(j);
                String ital = node2.getTextContent();
                if (ital.equalsIgnoreCase("vodka"))
                    System.out.println('\t' + nev);
            }

        }
    }

    private static void getBySalary(Document doc) {
        //Node listát csinálás a kiválasztott elem gyerekelemeiből
        NodeList nList = doc.getElementsByTagName("alkalmazott");
        //Bejárja a gyerekelemeket
        for (int i = 0; i < nList.getLength(); i++) {
            //Megkönnyíti a különböző node-ok megkülönböztetését ez a sor
            Node nNode = nList.item(i);
            //Aktuális node elemmé konvertálás
            Element elem = (Element) nNode;

            //Gyerekelemek kiválasztása és megfelelő adattípus szerint konvertálja
            Node node1 = elem.getElementsByTagName("nev").item(0);
            String nev = node1.getTextContent();

            Node node2 = elem.getElementsByTagName("fizetes").item(0);
            int fizetes = Integer.parseInt(node2.getTextContent());
            //Feltétel megadása
            if (fizetes >= 400000)
                System.out.println('\t' + nev);
        }
    }
}
