package Examples1;

/**
 * Copyright 2014 KentYang.
 * <p>
 * Released under The Academic Free License 3.0 ("AFL") v.3.0
 * <p>
 * http://www.opensource.org/licenses/afl-3.0.php
 * <p>
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Kent Yang
 */

import java.io.*;

public class BinaryVsText {
    public static void main(String[] args) {
        try {
            DataOutputStream dos =
                    new DataOutputStream(
                            new FileOutputStream("number.bin"));
            dos.writeInt(51966);
            dos.close();

            PrintWriter pw =
                    new PrintWriter(
                            new BufferedWriter(
                                    new FileWriter("number.txt")));

            pw.print(51966);
            pw.close();

            File file = new File("number.bin");
            System.out.println("Binary file size: " + file.length());
            file = new File("number.txt");
            System.out.println("Text file size: " + file.length());

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}




