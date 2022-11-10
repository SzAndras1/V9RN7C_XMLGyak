package xpathv9rn7c;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class xPathV9RN7C {

    public static void main(String[] args){
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse("studentV9RN7C.xml");

            //az XPath készitése
            XPath xPath = XPathFactory.newInstance().newXPath();

            //Meg kell adni az elérési út kifejezést és a csomópont listát
            String expression = "/class/student/keresztnev | /class/student/vezeteknev";
            /*1, String expression = "/class/student"
            2, String expression = "/class//student[@id=02]"
            3, String expression = "//student";
            4, String expression = "/class/student[2]";
            5, String expression = "/class/student[last()]";
            6, String expression = "/class/student[last()-1]";
            7, String expression = "/class/student[position()<3]"
            8, String expression = "/class/*";
            9, String expression = "/class//student[@*]";
            10, String expression = "//*";
            11, String expression = "/class/student[kor>20]"*/
            //Készitsünk egy listát, majd a xPath kifejezést le kell forditani és ki kell értékelni
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            //A for ciklus segitségével a NodeList csomópontja végig kell iterálni
            for (int i=0;i< nodeList.getLength();i++){
                Node node = nodeList.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                //Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")){
                    Element element = (Element) node;

                    //az id attribútumot ad vissza
                    System.out.println("Hallgató ID: "
                            + element.getAttribute("id"));
                    //keresztnevet ad vissza
                    System.out.println("Keresztnév: "
                            + element.getElementsByTagName("keresztnev").item(0).getTextContent());
                    //vezetéknevet ad vissza
                    System.out.println("Vezetéknév: "
                            + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
                    //becenevet ad vissza
                    System.out.println("Becenév: "
                            + element.getElementsByTagName("becenev").item(0).getTextContent());
                    //kort ad vissza
                    System.out.println("Kor: "
                            + element.getElementsByTagName("kor").item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
