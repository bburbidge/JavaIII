package com.makesrc;


import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2016 Kent Yang
 * <p>
 * Released under The Academic Free License 3.0 ("AFL") v3.0
 * <p>
 * http://www.opensource.org/licenses/afl-3.0.php
 * <p>
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Created on 5/24/2016.
 *
 * @author Kent Yang
 */

/**
 * JAXB XML Data Binding root element of the generated XML document
 */
@XmlRootElement(name = "addressBook")
public class DayTimer {
    /*
     * All of the value elements of the contacts Map will be wrapped
     * by a list element called 'contacts' instead of the default.
     */
    @XmlElementWrapper(name = "person")
    private Map<String, Person> contacts;

    public DayTimer() {
        contacts = new HashMap<String, Person>();
    }

    public void add(Person p) {

        if (!contacts.containsKey(p.getEmail())) {
            contacts.put(p.getEmail(), p);
        }
    }

    public void empty() {
        contacts.clear();
    }

    public Person[] getContacts() {
        return contacts.values().toArray(new Person[0]);
    }

    public Person findByEmail(String email) {
        return contacts.get(email);
    }

    public int getSize() {
        return contacts.size();
    }
}

