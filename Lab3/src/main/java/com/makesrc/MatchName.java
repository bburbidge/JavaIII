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
 * Created on 4/27/2016.
 *
 * @author Kent Yang
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchName {
    public static void main(String args[]) {
        String strToProcess = "Kent";
        if (args.length > 0)
            strToProcess = args[0];
        // Step 2 Get a Pattern Object, notice you do no instantiate
        // Notice how the + operator works here to ensure 1 or more
        Pattern p = Pattern.compile("^[A-Z][a-z]+");
        // Step 3 Pass String for matcher to match
        Matcher m = p.matcher(strToProcess);
        // Step 4 Check match
        if (m.matches())
            System.out.println(strToProcess + " is a valid name.");
        else
            System.out.println(strToProcess + " is an invalid name.");
    }
}
