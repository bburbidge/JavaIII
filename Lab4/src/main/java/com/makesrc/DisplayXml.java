package com.makesrc;

/***************************************************************************
 * Example 4-3 DisplayXml.java
 * Traversing a DOM tree using DOM API to display the contents of an XML
 * file.
 * <p>
 * email:  kentyang@alumni.ucsd.edu
 * URL:    www.javathehut.org
 * <p>
 * If you enjoy the Java 3 class and the examples provided, please look
 * for other classes I teach.  In addition, please recommend my classes to
 * your co-workers, friends and family. Thank you.
 *
 * @author Kent Yang
 * @version 1.0 2010-01-30
 ***************************************************************************/

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisplayXml {
    public static void displayXml(File f) {
        try {
            DocumentBuilder docBuilder =
                    DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = docBuilder.parse(f);

            List<Node> nodeList = new ArrayList<Node>();
            nodeList.add(doc);

            while (nodeList.size() > 0) {
                Node node = nodeList.get(0);

                if (node instanceof Document)
                    System.out.println("Document Node");
                else if (node instanceof Element) {
                    System.out.println("Element Node: " +
                            ((Element) node).getTagName());
                    NamedNodeMap attrMap = node.getAttributes();
                    for (int i = 0; i < attrMap.getLength(); i++) {
                        Attr attribute = (Attr) attrMap.item(i);
                        System.out.print("\tAttribute Key: " + attribute.getName()
                                + " Value: " + attribute.getValue());
                    }
                    if (node.hasAttributes())
                        System.out.println();
                } else if (node instanceof Text)
                    System.out.println("Text Node: " + node.getNodeValue());
                else
                    System.out.println("Other Type: " + node.getNodeValue());

                if (node.hasChildNodes()) {
                    NodeList nl = node.getChildNodes();
                    for (int i = 0; i < nl.getLength(); i++) {
                        nodeList.add(nl.item(i));
                    }
                }
                nodeList.remove(0);
            }
        } catch (ParserConfigurationException e) {
            System.err.println(
                    "Parser Configuration error. Can not display XML file: " + f);
        } catch (SAXException e) {
            System.err.println(
                    "SAX Parser error. Can not display XML file: " + f);
        } catch (IOException e) {
            System.err.println(
                    "IO error. Can not display XML file: " + f);
        }
    }

    public static void main(String args[]) {
        if (args.length == 1 && args[0].endsWith(".xml"))
            displayXml(new File(args[0]));
        else
            System.out.println("Usage: java DisplayXml <file name>.xml");
    }
}

