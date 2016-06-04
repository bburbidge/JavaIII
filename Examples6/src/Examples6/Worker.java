package Examples6;

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
 * Created on 5/24/2016.
 *
 * @author Kent Yang
 */
public class Worker
        implements Runnable {
    private String name = "";

    private Worker(String name) {
        this.name = name;
    }

    public static void main(String args[]) {
        new Worker("XXXXX").run();
        new Worker("OOOOO").run();
    }

    public void run() {
        for (int i = 0; i < 500; i++)
            System.out.print(name + " ");
    }
}