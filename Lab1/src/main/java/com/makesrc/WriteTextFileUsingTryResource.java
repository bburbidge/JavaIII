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

import java.io.FileWriter;
import java.io.IOException;

public class WriteTextFileUsingTryResource {
    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter("test.txt")) {
            fw.write("Write text file using Try with Resources!");
        } catch (IOException e) {
            System.err.println("Fail to write data to file!");
        }
 
      /* Old way using separating responsibility of 
       *  try catch and try finally. 
       */

        FileWriter fw = null;

        try {
            try {
                fw = new FileWriter("testOldWay.txt");
                fw.write("Write text file using double try!");
            } finally {
                if (fw != null)
                    fw.close();
            }
        } catch (IOException e) {
            System.err.println("Fail to write data to file!");
        }
    }
} 