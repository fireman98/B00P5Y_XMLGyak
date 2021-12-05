package XMLTaskB00P5Y.DOMParseB00P5Y.hu.domparse.B00P5Y;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

enum ElohelyTipus {
    SARKVIDEKI,
    ERDOS,
    MADARKETREC
}

public class DOMModifyB00P5Y {
    public static void main(String arg[]) throws IOException, ParserConfigurationException, SAXException,
            TransformerConfigurationException, TransformerException, XPathExpressionException {
        // Get input and output file
        File inputXMLFile = new File("XMLTaskB00P5Y/XMLB00P5Y.xml");
        File outputXMLFile = new File("XMLTaskB00P5Y/XMLB00P5Y.out.xml");
        // Get documentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        // Get document and normalize root element
        Document inputDoc = dBuilder.parse(inputXMLFile);
        inputDoc.getDocumentElement().normalize();

        // Apply modifications
        changeAllatNevById(inputDoc, "a-1", "PiheCica");
        addMegjegyzesToGondozasById(inputDoc, "g-11", "Nagyon rossz volt az állat!!");
        addElohely(inputDoc, (float) 25.2, ElohelyTipus.ERDOS);

        // Write to file and console
        write(inputDoc, outputXMLFile);
    }

    /**
     * Write a Document to file and console
     * 
     * @param doc
     * @param outputFile
     * @throws TransformerException
     * @throws UnsupportedEncodingException
     */
    private static void write(Document doc, File outputFile)
            throws TransformerException, UnsupportedEncodingException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();
        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amunt", "2");

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(outputFile);

        transf.transform(source, console);
        transf.transform(source, file);
    }

    /**
     * Állat nevének megváltoztatása id alapján
     * 
     * @param doc
     * @param id
     * @param newVal
     */
    private static void changeAllatNevById(Document doc, String id, String newVal) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//allat[@aid='" + id + "']/nev";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        if (nodeList.getLength() != 1)
            return;

        Node node = nodeList.item(0);
        node.setTextContent(newVal);
    }

    /**
     * Megjegyzés hozzáadása egy gondozáshoz
     * 
     * @param doc
     * @param id
     * @param newVal
     */
    private static void addMegjegyzesToGondozasById(Document doc, String id, String newVal)
            throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//gondozas[@gid='" + id + "']";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        if (nodeList.getLength() != 1)
            return;

        Node node = nodeList.item(0);
        Node megjegyzes = doc.createElement("megjegyzes");
        megjegyzes.setTextContent(newVal);

        node.appendChild(megjegyzes);
    }

    /**
     * Élőhely hozzáadása
     * 
     * @param newVal
     */
    private static void addElohely(Document doc, Float terulet, ElohelyTipus tipus) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//elohelyek";
        String elohelyExpression = "//elohelyek/elohely";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        if (nodeList.getLength() != 1)
            return;

        NodeList elohelyList = (NodeList) xPath.compile(elohelyExpression).evaluate(doc, XPathConstants.NODESET);
        int elohelyCount = elohelyList.getLength();

        Node elohelyek = nodeList.item(0);
        Node elohelyNode = doc.createElement("elohely");
        Node teruletNode = doc.createElement("terulet");
        Node tipusNode = doc.createElement("tipus");
        // Generate id: elohely.getLength() + 1
        ((Element) elohelyNode).setAttribute("eid", "e-" + elohelyCount + 1);
        String tipusString = "";
        teruletNode.setTextContent(terulet.toString());
        switch (tipus) {
            case ERDOS:
                tipusString = "Erdős";
                break;
            case SARKVIDEKI:
                tipusString = "Sarkvidéki";
                break;
            case MADARKETREC:
                tipusString = "Madárketrec";
                break;

        }
        tipusNode.setTextContent(tipusString);

        elohelyNode.appendChild(teruletNode);
        elohelyNode.appendChild(tipusNode);
        elohelyek.appendChild(elohelyNode);
    }

}
