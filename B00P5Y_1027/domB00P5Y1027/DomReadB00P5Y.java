package B00P5Y_1027.domB00P5Y1027;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadB00P5Y {

    public static void main(String arg[]) throws SAXException, IOException, ParserConfigurationException {
        File xmlFile = new File("B00P5Y_1027/usersB00P5Y.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("user");
        for (int i = 0; i < nList.getLength(); i++) {
            Node n = nList.item(i);

            if (n.getNodeType() != Node.ELEMENT_NODE)
                continue;

            NamedNodeMap attr = n.getAttributes();
            System.out.println("Current element: " + n.getNodeName());
            System.out.println("User id: " + attr.getNamedItem("id"));
            System.out.println("First name: " + attr.getNamedItem("first-name"));
            System.out.println("Last name: " + attr.getNamedItem("last-name"));
            System.out.println("Profession: " + attr.getNamedItem("profession"));
            System.out.println();

        }
    }

}
