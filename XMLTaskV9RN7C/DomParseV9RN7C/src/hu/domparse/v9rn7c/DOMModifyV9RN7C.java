package hu.domparse.v9rn7c;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.plaf.basic.BasicBorders;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

class DomModifyV9RN7C {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        File xmlFile = new File("V9RN7Cxml.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        //Elem érték átnevezés: A bárokban a vodkák átnevezése vodkaszódának:
        elementValueModify(doc);
        //Attribútom átnevezés: Egyik alkalmazott elsődleges kulcsát, az a_id-jának új értékadás
        attributeValueModify(doc);
        //Gyerekelem törlés: Résztvevőknél a diákigazolvány elemet kitörli
        childElementDelete(doc);
        //Új gyerekelem hozzáadás: Alkalmazott egy új kor nevű gyerekelemet
        childElementAdd(doc);
        //Új attributumot hozzáadás: Közvélemény kapcsoló táblának új id: k_id
        attributeAddNew(doc);

        writeToConsole(doc);
    }

    private static void elementValueModify(Document doc) {
        NodeList nList = doc.getElementsByTagName("bar");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element element = (Element) nNode;
            //Átmegy az összes ital elemen
            for (int j = 0; j < 3; j++) {
                Node node = element.getElementsByTagName("ital").item(j);
                String ital = node.getTextContent();
                //Ha az ital megegyezik a kivánt értékkel, akkor új értékere módosítja
                if (ital.equalsIgnoreCase("vodka")) {
                    ital = "vodkaszóda";
                    Element modifyElement = (Element) node;
                    modifyElement.setTextContent(ital);
                }
            }
        }
    }

    private static void attributeValueModify(Document doc) {
        NodeList nList = doc.getElementsByTagName("alkalmazott");
        //Első a sorban alkalmazottat kiválasztjuk
        Node nNode = nList.item(0);
        //Kigyűjtük az attribútomakat
        NamedNodeMap attributes = nNode.getAttributes();
        //a_id kigyűjtése az attribútom listából
        Node a_idNode = attributes.getNamedItem("a_id");
        //új érték adás
        Attr modifyAttr = (Attr) a_idNode;
        modifyAttr.setNodeValue("kpw-77a-ns");
    }

    private static void childElementDelete(Document doc) {
        NodeList nList = doc.getElementsByTagName("resztvevo");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element element = (Element) nNode;
            Node node = element.getElementsByTagName("diakigazolvany").item(0);
            element.removeChild(node);
        }
    }

    private static void childElementAdd(Document doc) {
        NodeList nList = doc.getElementsByTagName("alkalmazott");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            //Létrehozza az új gyerekelemet
            Element element = doc.createElement("kor");
            //Belerakja az alkalmazott elembe
            nNode.appendChild(element);
            //Add text részt a kor gyerekelemnek
            element.appendChild(doc.createTextNode(String.valueOf(20 + i)));
        }
    }

    private static void attributeAddNew(Document doc) {
        NodeList nList = doc.getElementsByTagName("kozvelemeny");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element element = (Element) nNode;
            element.setAttribute("k_id", "mau-ugs-2" + i);
        }
    }

    private static void writeToConsole(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);

        System.out.println("Módosított fájl:");
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}
