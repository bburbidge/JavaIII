package com.makesrc;

/***************************************************************************
 * Example 4-1 Person.java
 * Showing brute force way of creating an XML file.
 * <p>
 * Example 4-2
 * Showing how to use the DOM API to create a XML file.
 * <p>
 * email:  kentyang@alumni.ucsd.edu
 * URL:    www.javathehut.org
 * <p>
 * If you enjoy the Java 3 class and the examples provided, please look
 * for other classes I teach.  In addition, please recommend my classes to
 * your co-workers, friends and family. Thank you.
 *
 * @author Kent Yang
 * @version 1.0 2010-01-05
 ***************************************************************************/

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class Person
        implements Serializable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddr;

    public Person(String first, String last, String email, String phone) {
        this.firstName = first;
        this.lastName = last;
        this.phoneNumber = phone;
        this.emailAddr = email;

    }

    public static void main(String args[])
            throws IOException {
        Person me = new Person("Kent", "Yang",
                "KentYang@alumni.ucsd.edu", "(858)829-6201");
        me.toXml(); // example 4-1
        //me.toXmlWithDom(); // example 4-2
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String first) {
        this.firstName = first;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String last) {
        this.lastName = last;
    }

    public String getPhone() {
        return this.phoneNumber;
    }

    public void setPhone(String phone) {
        this.phoneNumber = phone;
    }

    public String getEmail() {
        return this.emailAddr;
    }

    public void setEmail(String email) {
        this.emailAddr = email;
    }

    public String toString() {
        return String.format("%s: %s\n%s: %s\n%s: %s\n%s: %s\n",
                "First Name", this.firstName,
                "Last Name", this.lastName,
                "Phone Number", this.phoneNumber,
                "Email Address", this.emailAddr);
    }

    public void toXml()
            throws IOException {
        String fileName = this.firstName + this.lastName + ".xml";
        File f = new File(fileName);
        if (f.exists())
            f.delete();

        PrintWriter pw =
                new PrintWriter(
                        new BufferedWriter(
                                new FileWriter(fileName)));

        StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("\n<person>");
        sb.append("\n\t<firstName>\n\t\t" + this.firstName + "\n\t</firstName>");
        sb.append("\n\t<lastName>\n\t\t" + this.lastName + "\n\t</lastName>");
        sb.append("\n\t<email>\n\t\t" + this.emailAddr + "\n\t</email>");
        sb.append("\n\t<phone>\n\t\t" + this.phoneNumber + "\n\t</phone>");
        sb.append("\n</person>\n");

        pw.println(sb);
        pw.close();
    }

    public void toXmlWithDom()
            throws IOException {
        try {
            Document document =
                    DocumentBuilderFactory.newInstance().
                            newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("person");
            document.appendChild(rootElement);

            Element element = document.createElement("firstName");
            element.appendChild(document.createTextNode(this.firstName));
            rootElement.appendChild(element);

            element = document.createElement("lastName");
            element.appendChild(document.createTextNode(this.lastName));
            rootElement.appendChild(element);

            element = document.createElement("email");
            // assign an attribute to the element
            element.setAttribute("type", "work");
            element.appendChild(document.createTextNode(this.emailAddr));
            rootElement.appendChild(element);
            element = document.createElement("phone");
            // assign an attribute to the element another way
            Attr attribute = document.createAttribute("type");
            attribute.setValue("cell");
            element.setAttributeNode(attribute);
            element.appendChild(document.createTextNode(this.phoneNumber));
            rootElement.appendChild(element);

            String fileName = this.firstName + this.lastName + ".xml";

            File f = new File(fileName);
            if (f.exists())
                f.delete();

            // holds the current DOM Tree to load/transform
            Source source = new DOMSource(document);
            // holds the results of the transformation (XML)
            Result result = new StreamResult(f);
            // An instance of abstract class that can transform
            // a source tree into result tree (XML)
            Transformer transformer =
                    TransformerFactory.newInstance().newTransformer();
            // Add capability to indent the output (make it pretty)
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // Set the indention to 3 spaces (default is 0)
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "3");
            // Just do it!
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            System.err.println("Parser Configuration error. Can not serialize Object to XML.");
        } catch (TransformerConfigurationException e) {
            System.err.println("Transformer Configuration error. Can not serialize Object to XML.");
        } catch (TransformerException e) {
            System.err.println("Transformation error. Can not serialize Object to XML.");
        }
    }
}

