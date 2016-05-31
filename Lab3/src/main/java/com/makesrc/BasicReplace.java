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
public class BasicReplace {
    public static void main(String args[]) {
        // String provide an easy way to search and replace portions of a String of Text
        // Match: all literal occurances of the word fox with kangaroo
        String strToProcess = "Kent,      Yang,   858-111-1111,kent.yang@gmail.com";
        if (args.length > 0)
            strToProcess = args[0];
        System.out.println(strToProcess.replaceAll(",\\s*", ", "));
    }
}
