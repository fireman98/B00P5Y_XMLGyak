package XMLTaskB00P5Y.DOMParseB00P5Y.hu.domparse.B00P5Y;

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

public class DOMReadB00P5Y {
    public static void main(String arg[]) throws SAXException, IOException, ParserConfigurationException {
        // Get file
        File xmlFile = new File("XMLTaskB00P5Y/XMLB00P5Y.xml");

        // Get DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        // Parse docuement and normalize
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // Call printNodeRecurse on root element
        printNodeRecurse(doc.getDocumentElement());
    }

    /**
     * Overload for printNodeResource,
     * Sets depth = 0
     * 
     * @param n
     */
    public static void printNodeRecurse(Node n) {
        printNodeRecurse(n, 0);
    }

    /**
     * Prints node name, attributes, textContent, then calls printNodeRecurs on
     * ELEMENT_NODE children
     * 
     * @param n     - node
     * @param depth - recursive depth
     */
    public static void printNodeRecurse(Node n, int depth) {
        // Determine node name, attributes and child nodes
        String nodeName = n.getNodeName();
        NamedNodeMap nodeAttributes = n.getAttributes();
        NodeList childNodes = n.getChildNodes();

        indent(depth);
        System.out.print("<" + nodeName);
        // Print attributes
        for (int i = 0; i < nodeAttributes.getLength(); i++) {
            Node attribute = nodeAttributes.item(i);
            System.out.print(" " + nodeAttributeToString(attribute));
        }
        System.out.println(">");
        // Iterate on childNodes
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node child = childNodes.item(i);
            short childNodeType = child.getNodeType();

            if (childNodeType != Node.ELEMENT_NODE) {
                if (childNodeType == Node.TEXT_NODE) {
                    // If textnode => trim (to avoid empty lines), then print
                    String textContent = child.getTextContent().trim();
                    if (textContent.length() != 0) {
                        indent(depth + 1);
                        System.out.println(textContent);
                    }
                }
                continue;
            }
            // Recursive call
            printNodeRecurse(child, depth + 1);
        }

        indent(depth);
        System.out.println("<\\" + nodeName + ">");
    }

    /**
     * Reads all nodename and textcontent and returns it as a formatted String
     * 
     * @param attribute - Node - type: Node.ATTRIBUTE_NODE
     * @return
     */
    private static String nodeAttributeToString(Node attribute) {
        return attribute.getNodeName() + "=\"" + attribute.getTextContent() + "\"";
    }

    /**
     * Prints times * tab on the stdout
     * 
     * @param times
     */
    private static void indent(int times) {
        for (int i = 0; i <= times; i++) {
            System.out.print("  ");
        }
    }
}
