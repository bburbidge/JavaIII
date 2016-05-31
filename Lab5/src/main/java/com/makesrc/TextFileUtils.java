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
 * Created on 5/17/2016.
 *
 * @author Kent Yang
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TextFileUtils {
    private TextFileUtils() {
    }

    public static List<String> readTextFile(String inputFile)
            throws IOException {
        BufferedReader in = new BufferedReader(
                new FileReader(inputFile));

        List<String> list = new ArrayList<String>();
        for (String line = null; (line = in.readLine()) != null; ) {
            list.add(line);
        }
        in.close();

        return list;
    }

    public static void writeTextFile(Collection<String> c, String outputFile)
            throws IOException {
        PrintWriter out =
                new PrintWriter(
                        new BufferedWriter(
                                new FileWriter(outputFile)));

        for (String str : c) {
            out.println(str);
        }
        out.close();
    }
}
