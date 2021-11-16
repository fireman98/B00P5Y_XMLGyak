package B00P5Y_1110.domb00p5y1110;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DOMWriteB00P5Y {
    public static void main(String arg[]) throws IOException, ParserConfigurationException, TransformerException {
        File outputXMLFile = new File("B00P5Y_1110/user1B00P5Y.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document outputDoc = dBuilder.newDocument();
        Element root = outputDoc.createElementNS("domB00P5Y", "users");

        root.appendChild(createUser(outputDoc, "1", "Pal", "Kiss", "programmer"));
        root.appendChild(createUser(outputDoc, "2", "Pirsoka", "Zold", "writer"));
        root.appendChild(createUser(outputDoc, "3", "Alma", "Gordon", "teacher"));

        outputDoc.appendChild(root);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(outputDoc);

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(outputXMLFile);

        transformer.transform(source, console);
        transformer.transform(source, file);

    }

    private static Node createUser(Document doc, String id, String firstname, String lastname, String profession) {
        Node _firstname = doc.createElement("firstname");
        _firstname.setTextContent(firstname);
        Node _lastname = doc.createElement("lastname");
        _lastname.setTextContent(lastname);
        Node _profession = doc.createElement("profession");
        _profession.setTextContent(profession);
        Node user = doc.createElement("user");
        ((Element) user).setAttribute("id", id);
        user.appendChild(_firstname);
        user.appendChild(_lastname);
        user.appendChild(_profession);

        return user;
    }

}
