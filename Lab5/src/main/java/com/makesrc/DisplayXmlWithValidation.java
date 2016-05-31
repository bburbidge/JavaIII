package com.makesrc;

/***************************************************************************
 * Example 5-2 DisplayXmlWithValidation.java
 * Traversing a DOM tree using DOM API to display the contents of an XML
 * file with validation against a XSD file and error handler using
 * anonymous inner class.
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
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DisplayXmlWithValidation {
    /** Constants used for JAXP 1.2 */
    static final String JAXP_SCHEMA_LANGUAGE =
            "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA =
            "http://www.w3.org/2001/XMLSchema";
    static final String JAXP_SCHEMA_SOURCE =
            "http://java.sun.com/xml/jaxp/properties/schemaSource";

    public static void displayXml(File xmlFile, File xsdFile) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();

            // Change: Enable validation
            dbFactory.setValidating(true);
            // Change: Specify XSD type validation (the other choice is DTD)
            dbFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            // Change: Specify the actual XSD file used to validate
            dbFactory.setAttribute(JAXP_SCHEMA_SOURCE, xsdFile);
            // Change: The following line doesn't work.  Sun Bug controversy
            //    Books indicates this should be a bug.  Sun website disagrees
            //    See book provided work around.
            dbFactory.setIgnoringElementContentWhitespace(true);

            // The rest of the code is the same
            DocumentBuilder docBuilder =
                    dbFactory.newDocumentBuilder();

            // For Example 5-2, we need to install a Error Handler for the parser
            // We create one on the fly with an anonymous inner classes
            docBuilder.setErrorHandler(new ErrorHandler() {
                public void warning(SAXParseException e)
                        throws SAXException {
                    errorDisplay("Warning", e);
                }

                public void error(SAXParseException e)
                        throws SAXException {
                    errorDisplay("Error", e);
                }

                public void fatalError(SAXParseException e)
                        throws SAXException {
                    errorDisplay("Fatal Error", e);
                }

                // note you can add extra methods to anonymous inner class
                private void errorDisplay(String errorType, SAXParseException e) {
                    System.err.println("Our Error Hanlder: " + errorType + " Line: "
                            + e.getLineNumber() + "\nError Message: " + e.getMessage());
                }
            });

            Document doc = docBuilder.parse(xmlFile);

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
        } catch (Exception e) {
            // Change: We simplify the exception handling
            System.err.println(
                    "Error! Can not display XML file: " + xmlFile +
                            " using: " + xsdFile + " to validate.");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        // Change: the usage to provide extra parameter
        if (args.length == 2 &&
                args[0].endsWith(".xml") &&
                args[1].endsWith(".xsd"))
            displayXml(new File(args[0]), new File(args[1]));
        else
            System.out.println("Usage: java DisplayXmlWithValidation <file name>.xml <file name>.xsd");
    }
}
