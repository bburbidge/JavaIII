package com.makesrc;

/*
    * Copyright 2012 KentYang.
    *
    * Released under The Academic Free License 3.0 ("AFL") v.3.0
    *
    *      http://www.opensource.org/licenses/afl-3.0.php
    *
    * See the License for the specific language governing permissions and
    * limitations under the License.
  */

/**
 * @author KentYang
 * @version Feb 14, 2012 6:45:40 AM
 */

import javax.xml.stream.*;
import java.io.*;

public class XmlRoundTripWithStax {
    public static void main(String[] args) {
        try {
            File f = new File("Kent.xml");
            writePersonXml(
                    new Person("Kent", "Yang", "kent.yang@gmail.com", "888-888-8888"),
                    f);
            Person p = readPersonXml(f);
            System.out.println("Information for Person: \n" + p);
        } catch (Throwable e) {
            System.err.println("Failed StAX demonstration with Person class!");
        }
    }

    public static void writePersonXml(Person p, File f)
            throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
        try {
            FileWriter fileWriter = new FileWriter(f);
            XMLStreamWriter writer =
                    outputFactory.createXMLStreamWriter(fileWriter);

            writer.writeStartDocument("1.0");
            writer.writeStartElement("Person");
            writer.writeStartElement("firstName");
            writer.writeCharacters(p.getFirstName());
            writer.writeEndElement();
            writer.writeStartElement("lastName");
            writer.writeCharacters(p.getLastName());
            writer.writeEndElement();
            writer.writeStartElement("email");
            writer.writeAttribute("type", "work");
            writer.writeCharacters(p.getEmail());
            writer.writeEndElement();
            writer.writeStartElement("phone");
            writer.writeAttribute("type", "cell");
            writer.writeCharacters(p.getPhone());
            writer.writeEndElement();
            writer.writeEndElement();
            writer.flush();
            writer.close();
        } catch (IOException | XMLStreamException e) {
            System.err.println(e);
            throw e;
        }
    }

    public static Person readPersonXml(File f) {
        Person p = new Person();

        if (f.exists()) {
            try {
                XMLInputFactory inputFactory = XMLInputFactory.newFactory();
                FileReader fileReader = new FileReader(f);
                XMLStreamReader reader =
                        inputFactory.createXMLStreamReader(fileReader);

                while (reader.hasNext()) {
                    int eventType = reader.getEventType();
                    switch (eventType) {
                        case XMLStreamConstants.START_ELEMENT:
                            String elementName = reader.getLocalName();
                            if (elementName.equals("Person")) {
                                System.out.println("Start new Person processing...");
                            } else if (elementName.equals("firstName")) {
                                p.setFirstName(reader.getElementText());
                            } else if (elementName.equals("lastName")) {
                                p.setLastName(reader.getElementText());
                            } else if (elementName.equals("email")) {
                                // Check for type
                                int numAttributes = reader.getAttributeCount();
                                for (int i = 0; i < numAttributes; i++)
                                    System.out.println("Email attribute: " +
                                            reader.getAttributeLocalName(i) +
                                            "=" + reader.getAttributeValue(i));
                                p.setEmail(reader.getElementText());
                            } else if (elementName.equals("phone")) {
                                int numAttributes = reader.getAttributeCount();
                                for (int i = 0; i < numAttributes; i++)
                                    System.out.println("Phone attribute: " +
                                            reader.getAttributeLocalName(i) +
                                            "=" + reader.getAttributeValue(i));
                                System.out.println("found phone");
                                p.setPhone(reader.getElementText());
                            }
                            break;
                        case XMLStreamConstants.END_ELEMENT:
                            elementName = reader.getLocalName();
                            if (elementName.equals("Person")) {
                                System.out.println("Done processing person...");
                            }
                            break;
                    }
                    reader.next();
                }
            } catch (FileNotFoundException | XMLStreamException e) {
                System.err.println(e);
                return p;
            }
        }
        return p;
    }
}
