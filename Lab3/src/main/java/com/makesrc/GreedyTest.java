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

public class GreedyTest {
    public static void main(String args[]) {
        // Greedy - longest possible match
        Pattern r = Pattern.compile("1\\d*3");
        Matcher m = r.matcher("123123123123123");
        while (m.find())
            System.out.println(m.group());

        // Reluctant - shortest possible match
        r = Pattern.compile("1\\d*?3");
        m = r.matcher("123123123123123");
        while (m.find())
            System.out.println(m.group());
    }

}

