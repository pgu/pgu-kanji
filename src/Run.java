import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Run {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        System.out.println("toto");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringComments(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document dom = db.parse("pgu-kanji/src/kanjidic2.xml");

        Element doc = dom.getDocumentElement();
        NodeList nodeList = doc.getElementsByTagName("literal");

        System.out.println("Nodes: " + nodeList.getLength());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < nodeList.getLength(); i++) {
            sb.append(nodeList.item(i).getTextContent());
            sb.append(".");

            if ((i + 1) % 9 == 0) {
                sb.append("\n");
            }
        }

        PrintWriter writer = new PrintWriter("pgu-kanji/src/result.txt", "UTF-8");
        writer.println(sb.toString());
        writer.close();

        System.out.println("Done!");
    }

}
