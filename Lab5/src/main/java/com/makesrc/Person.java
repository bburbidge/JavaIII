package com.makesrc;


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

import javax.xml.bind.annotation.XmlType;

/**
 * A simple class representing a person
 */

/**
 * JAXB XML Data Binding - specifies the ordering of the properties in the
 * target <sequence> element.
 */
@XmlType
        (
                name = "PersonType", propOrder = {"firstName", "lastName", "email", "phone"}
        )
class Person {

    private String lastName = "";
    private String firstName = "";
    private String email = "";
    private String phone = "";

    // no-argument constructor
    public Person() {
    }

    public Person(String firstName, String lastName, String email, String phone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
    }

    public String toString() {
        return String.format(
                " First name: %s \n Last name:  %s \n Email:      %s \n Phone: %s\n",
                this.firstName, this.lastName, this.email, this.phone);
    }

    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (this.getClass() != otherObject.getClass()) return false;
        Person other = (Person) otherObject;
        return this.lastName.equals(other.lastName) &&
                this.firstName.equals(other.firstName) &&
                this.email.equals(other.email) &&
                this.phone.equals(other.phone);
    }

    public int hashCode() {
        return 5 * this.lastName.hashCode()
                + 7 * this.firstName.hashCode()
                + 11 * this.email.hashCode()
                + 13 * this.phone.hashCode();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


