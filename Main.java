package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

//        File fXmlF = new File("books.xml");
//
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//
//        DocumentBuilder db = dbFactory.newDocumentBuilder();
//
//        FileInputStream fis = new FileInputStream("catalog.xml");
//        Document doc = db.parse(fXmlF);
//
//       dbFactory.setIgnoringElementContentWhitespace(true);
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();

        XPathExpression xPathExpression = xPath.compile("//catalog/book[price>10][number(translate(publish_date,'-','')) > 20050000]");
        // stari upit
        //"//book[price>10]/price] | //book[publish_date>2005]/publish_date");

        File xmlDocument = new File("src/main/catalog.xml");
        InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));

        Object result = xPathExpression.evaluate(inputSource, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) result;
        
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (null != node) {
                NodeList childNodeList = node.getChildNodes();
                for (int j = 0; null != childNodeList && j < childNodeList.getLength(); j++) {
                    Node nod = childNodeList.item(j);
                    if (nod.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(childNodeList.item(j).getNodeName() + " : " + nod.getFirstChild().getNodeValue());
                    }
                }
            }
        }
        
    }
}
