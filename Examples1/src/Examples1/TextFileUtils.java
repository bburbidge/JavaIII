package Examples1;

/***************************************************************************
 * Example 1-3a TextFileUtils.java
 * <p>
 * A utility class that demonstrate creating a static library and convenient
 * methods to read a text file and store lines of text as a list.  It also
 * provides the converse capability of writing output to a text file.
 * <p>
 * email:  kentyang@alumni.ucsd.edu
 * URL:    www.javathehut.org
 * <p>
 * If you enjoy the Java 3 class and the examples provided, please look
 * for other classes I teach.  In addition, please recommend my classes to
 * your co-workers, friends and family. Thank you.
 *
 * @author Kent Yang
 * @version 1.0 2010-01-13
 ***************************************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class TextFileUtils {
    // private constructor to disallowed instantiation outside the class
    // Our goal is to provide a static library of tools.
    private TextFileUtils() {
    }

    static List<String> readTextFile(String inputFile)
            throws IOException {
        BufferedReader in = new BufferedReader(
                new FileReader(inputFile));

        List<String> list = new ArrayList<>();
        for (String line; (line = in.readLine()) != null; ) {
            list.add(line);
        }
        in.close();

        return list;
    }

    static void writeTextFile(List<String> list, String outputFile)
            throws IOException {
        PrintWriter out =
                new PrintWriter(
                        new BufferedWriter(
                                new FileWriter(outputFile)));

        for (String str : list) {
            out.println(str);
        }
        out.close();
    }
}
