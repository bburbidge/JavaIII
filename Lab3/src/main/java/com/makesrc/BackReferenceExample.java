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
import java.util.regex.PatternSyntaxException;

public class BackReferenceExample {
    public static void main(String[] args) {
        String testStr = "There are too many dog dog dog dog dog running around.";
        Pattern regex = null;
        System.out.println("Test String:" + testStr);
        try {
            regex = Pattern.compile("(dog\\s)(\\1)+");
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }

        Matcher matcher = regex.matcher(testStr);
        while (matcher.find()) {
            System.out.println(matcher.group(0));

            // finds only two groups instead of three
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("Match" + i + ": " + matcher.group(i));
            }
        }

        String results = matcher.replaceAll("$1");
        System.out.println("Result: " + results);
    }
}
