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
public class BasicMatch {
    public static void main(String args[]) {
        // String provide an easy way to match a String of Text
        // Match: character class to match anything except a question mark
        // one or more times.  Then make sure there is a question mark at the end
        String strToProcess = "Shall we play a game?";
        if (args.length > 0)
            strToProcess = args[0];
        System.out.print(strToProcess + " ");
        System.out.println(strToProcess.matches("[\\w ]+\\?"));
    }
}
