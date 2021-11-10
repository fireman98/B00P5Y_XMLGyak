package B00P5Y_1110.xpathb00p5y1110;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xPathB00P5Y {

    public static void main(String arg[])
            throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

        File inputFile = new File("B00P5Y_1110/studentB00P5Y.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        // Build XPath tree
        XPath xPath = XPathFactory.newInstance().newXPath();

        // String expression = "class";

        // Válassza ki az összes student element, amely a class gyermekei.
        // String expression = "class/student";

        // Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és
        // értéke "01".
        // String expression = "//student[@id='01']";

        // Kiválasztja az összes student elemet, függetlenül attól, hogy hol vannak a
        // dokumentumban
        // String expression = "//student";

        // Válassza ki a második student element, amely a class elem gyermeke.
        // String expression = "/class/student[position()=2]";

        // Válassza ki az utolsó student elemet, amely a class elem gyermeke.
        // String expression = "/class/student[last()]";

        // Válassza ki az utolsó előtti student elemet, amely a class elem gyermeke
        // String expression = "/class/student[position() = last() - 1]";

        // Válassza ki az első két student elemet, amelyek a class elem gyermekei.
        // String expression = "/class/student[position() < 2]";

        // Válassza ki class elem összes gyermek elemét.
        // String expression = "/class/*";

        // Válassza ki az összes student elemet, amely rendelkezik legalább egy
        // bármilyen attribútummal.
        // String expression = "//student[@*]";

        // Válassza ki a dokumentum összes elemét.
        // String expression = "//*";

        // Válassza ki a class elem összes student elemét, amelynél a kor>20.
        String expression = "//class/*[age > 20]";

        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            System.out.println("Aktuális elem: " + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
                Element element = (Element) node;

                System.out.println("Hallgató ID: " + element.getAttribute("id"));
                System.out.println("Keresznév: " + element.getElementsByTagName("first-name").item(0).getTextContent());
                System.out.println("Vezetéknév: " + element.getElementsByTagName("last-name").item(0).getTextContent());
                System.out.println("Becenév: " + element.getElementsByTagName("nick-name").item(0).getTextContent());
                System.out.println("Kor: " + element.getElementsByTagName("age").item(0).getTextContent());

            }
            System.out.println("");

        }
        System.out.println("");
    }
}
