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

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class PersonRead {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream("data.zip")))) {
            List<Person> lp = (List<Person>) ois.readObject();
            System.out.println(lp);

        } catch (Exception e) {
            System.err.println("Failed to read Object from file!" + e);
        }
    }
}