package com.makesrc;


/***************************************************************************
 * Example 5-4 UnmarshallAddressBook.java
 * An example demonstrating how to marshall to a XML file (convert to
 * XML) using the JAXB API.
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

//import generated.*;

import com.sun.org.apache.xalan.internal.utils.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.util.List;

public class MarshallAddressBook {
    public static void main(String args[]) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("generated");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    new Boolean(true));

            ObjectFactory objFactory = new ObjectFactory();
            AddressBook ab = objFactory.createAddressBook();

            List<Person> list = ab.getPerson();

            Person person = objFactory.createPerson();

            person.setFirstName("Kent");
            person.setLastName("Yang");
            person.setEmail("kent.yang@gmail.com");
            person.setPhone("888-888-8888");

            list.add(person);
            marshaller.marshal(ab, new FileOutputStream("newAddressbook.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}