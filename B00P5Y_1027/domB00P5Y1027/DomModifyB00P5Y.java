package B00P5Y_1027.domB00P5Y1027;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyB00P5Y {
    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException,
            TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("B00P5Y_1027/carsB00P5Y.xml"));
        document.getDocumentElement().normalize();

        Node cars = document.getFirstChild();
        Node supercar = document.getElementsByTagName("supercars").item(0);

        NamedNodeMap attr = supercar.getAttributes();
        Node nodeAttr = attr.getNamedItem("company");
        nodeAttr.setTextContent("Lamborigini");

        NodeList list = supercar.getChildNodes();

        for (int temp = 0; temp < list.getLength(); temp++) {
            Node node = list.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                if ("carname".equals(eElement.getNodeName())) {
                    if ("Ferrari 101".equals(eElement.getTextContent())) {
                        eElement.setTextContent("Lamborigini 001");
                    }
                    if ("Ferrari 202".equals(eElement.getTextContent()))
                        eElement.setTextContent("Lamborigini 002");
                }
            }
        }

        NodeList childNodes = cars.getChildNodes();

        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);

            if ("luxurycars".equals(node.getNodeName()))
                cars.removeChild(node);
        }

        write(document);
    }

    /**
     * Write a Document to console
     * 
     * @param doc
     * @throws TransformerException
     */
    private static void write(Document doc)
            throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();
        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amunt", "2");

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);

        System.out.println("-----------Modified File-----------");
        transf.transform(source, console);
    }

}