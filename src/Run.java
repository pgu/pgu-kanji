import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Run {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringComments(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document dom = db.parse("pgu-kanji/src/kanjidic2.xml");

        Element doc = dom.getDocumentElement();
        NodeList charactersNL = doc.getElementsByTagName("character");

        int nbCharacters = charactersNL.getLength();

        System.out.println("nbCharacters: " + nbCharacters);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < nbCharacters; i++) {

            Element character = (Element) charactersNL.item(i);

            NodeList literalNL = character.getElementsByTagName("literal");
            String kanji = literalNL.item(0).getTextContent();

            if ("亜".equals(kanji)) {

                NodeList readingNL = character.getElementsByTagName("reading");
                NodeList meaningNL = character.getElementsByTagName("meaning");

                System.out.println(kanji);
                System.out.println("### reading");
                for (int j = 0; j < readingNL.getLength(); j++) {
                    Node reading = readingNL.item(j);

                    NamedNodeMap atts = reading.getAttributes();
                    String r_type = atts.getNamedItem("r_type").getTextContent();

                    if ("ja_on".equals(r_type)) {
                        System.out.println("on: " + reading.getTextContent());

                    } else if ("ja_kun".equals(r_type)) {
                        System.out.println("kun: " + reading.getTextContent());
                    }

                }

                System.out.println("### meaning");
                for (int j = 0; j < meaningNL.getLength(); j++) {
                    Node item = meaningNL.item(j);
                    if (!item.hasAttributes()) {
                        System.out.println(item.getTextContent());
                    }
                }

                break;
            }

            System.out.print(".");
        }

        PrintWriter writer = new PrintWriter("pgu-kanji/src/result.txt", "UTF-8");
        writer.println(sb.toString());
        writer.close();

        System.out.println("Done!");
    }

    //            sb.append(character.getTextContent());
//            sb.append(".");
//
//            if ((i + 1) % 9 == 0) {
//                sb.append("\n");
//            }


//    NodeList listOfPersons = doc.getElementsByTagName("person");
//    int totalPersons = listOfPersons.getLength();
//    System.out.println("Total no of people : " + totalPersons);
//
//    for(int s=0; s<listOfPersons.getLength() ; s++){
//
//
//        Node firstPersonNode = listOfPersons.item(s);
//        if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){
//
//
//            Element firstPersonElement = (Element)firstPersonNode;
//
//            //-------
//            NodeList firstNameList = firstPersonElement.getElementsByTagName("first");
//            Element firstNameElement = (Element)firstNameList.item(0);
//
//            NodeList textFNList = firstNameElement.getChildNodes();
//            System.out.println("First Name : " +
//                    ((Node)textFNList.item(0)).getNodeValue().trim());
//
//            //-------
//            NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
//            Element lastNameElement = (Element)lastNameList.item(0);
//
//            NodeList textLNList = lastNameElement.getChildNodes();
//            System.out.println("Last Name : " +
//                    ((Node)textLNList.item(0)).getNodeValue().trim());
//
//            //----
//            NodeList ageList = firstPersonElement.getElementsByTagName("age");
//            Element ageElement = (Element)ageList.item(0);
//
//            NodeList textAgeList = ageElement.getChildNodes();
//            System.out.println("Age : " +
//                    ((Node)textAgeList.item(0)).getNodeValue().trim());
//
//            //------
//
//
//        }//end of if clause
//
//
//    }//end of for loop with s var

}
