package com.makesrc;


/***************************************************************************
 * Example 4-4 GenMailingList.java
 * Demonstrates targeted access of specific XML element within the XML file
 * using the SAX API.
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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class GenMailingList
        extends org.xml.sax.helpers.DefaultHandler {
    private StringBuffer buffer = new StringBuffer();

    public static void main(String[] args)
            throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        GenMailingList handler = new GenMailingList();
        parser.parse(new File("addressbook.xml"), handler);
    }

    public void startDocument() {
        System.out.println("Start processing...");
    }

    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }

    public void startElement(String uri, String localName,
                             String qname, Attributes attributes) {
        buffer.setLength(0);
    }

    public void endElement(String uri, String localName, String qname) {
        if (qname.equals("email")) {
            System.out.println(buffer.toString().trim());
        }
    }

    public void endDocument() {
        System.out.println("End processing...");
    }
}


