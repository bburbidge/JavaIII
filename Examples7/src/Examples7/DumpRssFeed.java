package Examples7;


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
 * Created on 5/25/2016.
 *
 * @author Kent Yang
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class DumpRssFeed {
    public static void main(String[] args)
            throws IOException {
        URL url;
        if (args.length == 0)
            //default is engadaget RSS feed
            url = new URL("http://www.engadget.com/rss.xml");
        else
            url = new URL("http://" + args[0]);

        InputStream is = url.openStream();
        Scanner in = new Scanner(is);
        while (in.hasNextLine()) {
            System.out.println(in.nextLine());
        }
    }
}

