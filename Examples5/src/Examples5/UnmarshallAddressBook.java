package Examples5;


/***************************************************************************
 * Example 5-3 UnmarshallAddressBook.java
 * An example demonstrating how to unmarshall an XML file (convert to
 * Objects) using the JAXB API.
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


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class UnmarshallAddressBook {

    public static void main(String args[]) {
        try {
            JAXBContext jc = JAXBContext.newInstance("generated");
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            AddressBook ab = (AddressBook)
                    unmarshaller.unmarshal(new File("addressbook.xml"));

            List<Person> list = ab.getPerson();

            for (Person p : list) {
                System.out.println("First Name: " + p.getFirstName());
                System.out.println("Last Name:  " + p.getLastName());
                System.out.println("Phone:      " + p.getPhone());
                System.out.println("Email:      " + p.getEmail());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




