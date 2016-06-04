package Examples3;

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
 * Created on 4/27/2016.
 *
 * @author Kent Yang
 */
public class BasicSplit {
    public static void main(String args[]) {
        // String Provide an easy way to split a strinig into tokens
        // Match: Character class include space and or periods
        // So tokens will be divided between space and periods (2 characters)
        //String strToProcess = "Kent,      Yang,   858-111-1111,kent.yang@gmail.com";
        String strToProcess = "Kent, Yang, 858-111-1111, kent.yang@gmail.com";
        if (args.length > 0)
            strToProcess = args[0];
        String array[] = strToProcess.split(",\\s*");
        for (String s : array)
            System.out.println(s); // print tokens with carriage return in between
    }
}