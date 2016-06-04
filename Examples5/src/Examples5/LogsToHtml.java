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
//        import generated.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import java.util.List;

public class LogsToHtml {
    public static void main(String args[]) {
        try {
            if (args.length == 0) {
                System.out.println("java LogsToHtml <logs.xml>");
                System.exit(0);
            }


            JAXBContext jc = JAXBContext.newInstance("generated");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            JAXBIntrospector jaxbIntrospector = jc.createJAXBIntrospector();

            //            Log log = (Log) unmarshaller.unmarshal(new File(args[0]));
            //
            //            List<Record> records = log.getRecord();

            StringBuilder sb = new StringBuilder();
            sb.append("<tr>");
            sb.append("<th>Date</th>");
            sb.append("<th>Seqeuence</th>");
            sb.append("<th>Level</th>");
            sb.append("<th>Message</th>");
            sb.append("</tr>");

            //            for (Record r : records) {
            //                sb.append("<tr>\n");
            //                sb.append("<td>" + r.getDate() + "</td>\n");
            //                sb.append("<td>" + r.getSequence() + "</td>\n");
            //                sb.append("<td>" + r.getLevel() + "</td>\n");
            //                sb.append("<td>" + r.getMessage() + "</td>\n");
            //                sb.append("</tr>\n");
            //            }

            System.out.println("logs: " + sb.toString());
            List<String> inputList = TextFileUtils.readTextFile("basic.template");

            for (int i = 0; i < inputList.size(); i++) {
                String currentLine = inputList.get(i);
                if (currentLine.contains("{Title}")) {
                    currentLine = currentLine.replace("{Title}", "Log to HTML Generator");
                    inputList.set(i, currentLine);
                } else if (currentLine.contains("{LogData}")) {
                    currentLine = currentLine.replace("{LogData}", sb.toString());
                    inputList.set(i, currentLine);
                }
            }

            TextFileUtils.writeTextFile(inputList, "basic.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
