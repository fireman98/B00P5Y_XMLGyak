package B00P5Y_1027.domB00P5Y1027;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomWriteB00P5Y {

    private static void processNode(Node n) {
        if (n.getNodeType() != Node.ELEMENT_NODE)
            return;

        NodeList nList = n.getChildNodes();
        NamedNodeMap attributes = n.getAttributes();
        String name = n.getNodeName();

        System.out.println("Current element: " + name);

        for (int i = 0; i < attributes.getLength(); i++) {
            String attributeName = attributes.item(i).getNodeName();
            String attributeContent = attributes.item(i).getTextContent();

            System.out.println(attributeName + ": " + attributeContent);
        }

        for (int i = 0; i < nList.getLength(); i++) {
            processNode(nList.item(i));
        }
    }

    public static void main(String arg[]) throws IOException, ParserConfigurationException, SAXException,
            TransformerConfigurationException, TransformerException {
        File inputXMLFile = new File("B00P5Y_1027/usersB00P5Y.xml");
        File outputXMLFile = new File("B00P5Y_1027/user1B00P5Y.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document inputDoc = dBuilder.parse(inputXMLFile);
        inputDoc.getDocumentElement().normalize();

        Document outputDoc = dBuilder.newDocument();
        Element root = outputDoc.createElementNS("domB00P5Y", "users");
        outputDoc.appendChild(root);

        Node documentElement = inputDoc.getDocumentElement();
        // processNode(documentElement);

        // Copy input doc
        DOMSource source = new DOMSource(inputDoc);

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(outputXMLFile);

        TransformerFactory transfFactory = TransformerFactory.newInstance();
        Transformer transf = transfFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        transf.transform(source, console);
        transf.transform(source, file);
    }
}
