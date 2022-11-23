package domv9rn7c;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class MyTest {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        /*
            <supercars company="Lamborghini"> - //Ferrari -t Lamborghini
            <carname type="formula one">Lamborghini 01</carname>
            <carname type="sports">Lamborghini 02</carname>
         */
        File xmlFile = new File("carsV9RN7C.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);

        NodeList listOfSuperCars = doc.getElementsByTagName("supercars");
        System.out.println(listOfSuperCars.getLength());
        for (int i = 0; i < listOfSuperCars.getLength(); i++) {
            Node supercar = listOfSuperCars.item(i);
            if (supercar.getNodeType() == Node.ELEMENT_NODE) {
                String company = supercar.getAttributes().getNamedItem("company").getTextContent();
                if(company.equals("Ferrari"))
                {
                    supercar.getAttributes().getNamedItem("company").setTextContent("Lamborghini");
                    String aaa = supercar.getAttributes().getNamedItem("company").getTextContent();
                }
            }
        }
        /*TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        System.out.println("New File");
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);*/

    }
}
