package Examples5;


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

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenSomeLogs {
    // Use the static method to get a logger object capable of logging
    private static Logger logger;

    static {
        try {
            logger = Logger.getLogger(GenSomeLogs.class.getName());
            logger.addHandler(
                    new FileHandler(GenSomeLogs.class.getName() + ".xml"));
            logger.setLevel(Level.FINE);
        } catch (Exception e) {
            System.err.println("Can't create log file!");
            System.exit(-1);
        }
    }

    public static void main(String args[]) {
        logger.info("This is an info message.");
        logger.config("This is a config message.");
        logger.fine("This is a fine message.");
        logger.log(Level.WARNING, "This is a warning message.");
        logger.log(Level.SEVERE, "This is a severe message.");
    }
}
