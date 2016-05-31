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
 * Created on 4/11/2016.
 *
 * @author Kent Yang
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class PersonWrite {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("data.zip")))) {
            Person p = new Person();
            p.setFirstName("Kent");
            p.setLastName("Yang");
            p.setEmail("kent.yang@gmail.com");
            p.setPhone("888-888-8888");

            List<Person> lp = new ArrayList<>();
            lp.add(p);
            lp.add(p);
            lp.add(p);

            oos.writeObject(lp);
        } catch (IOException e) {
            System.err.println("Failed to write object out to file!");
        }
    }
}