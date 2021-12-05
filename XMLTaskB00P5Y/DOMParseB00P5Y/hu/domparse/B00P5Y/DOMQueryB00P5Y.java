package XMLTaskB00P5Y.DOMParseB00P5Y.hu.domparse.B00P5Y;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryB00P5Y {

    public static void main(String arg[])
            throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

        File inputFile = new File("XMLTaskB00P5Y/XMLB00P5Y.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        // Build XPath tree
        XPath xPath = XPathFactory.newInstance().newXPath();

        int inputCode = 0;
        System.out.println("Menu: ");
        System.out.println("1) - A hímnemű állatok neveinek kiíratása");
        System.out.println("2) - A 2018-ban és attól később született állatok lekérdezése");
        System.out.println("3) - December havi nyitvatartás lekérdezése");
        System.out.println("4) - Az utolsó 3 takarítás lekérdezése");
        System.out.println("Más billentyű) - Kilépés");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        try {
            inputCode = Integer.parseInt(input);
        } catch (Exception e) {
            inputCode = 0;
        }

        String expression = "";

        switch (inputCode) {
            case 1:
                expression = "//allat/nev[../nem='Hím']";
                break;
            case 2:
                expression = "//allat[number(translate(./szuletes-ideje,'-','')) > 20180101]";
                break;
            case 3:
                expression = "//nyitvatartas[@honap=12]";
                break;
            case 4:
                expression = "//takaritas[position() > last() - 3]";
                break;
            default:
                System.out.println("Bye");
                return;
        }

        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            DOMReadB00P5Y.printNodeRecurse(node);
        }
    }
}
