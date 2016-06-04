package Examples7;

/***************************************************************************
 * Example 7-4 DumpStockPage.java
 * A class that uses the URL class to access web resources.  Can be easily
 * modified to support more advance web scraping.
 * <p>
 * email:  kentyang@alumni.ucsd.edu
 * URL:    www.javathehut.org
 * <p>
 * If you enjoy the Java 3 class and the examples provided, please look
 * for other classes I teach.  In addition, please recommend my classes to
 * your co-workers, friends and family. Thank you.
 *
 * @author Kent Yang
 * @version 1.0 2010-02-28
 ***************************************************************************/

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class DumpStockPage {
    public static void main(String[] args)
            throws IOException {
        URL url;
        if (args.length == 0)
            url = new URL("http://finance.yahoo.com/q?s=GOOG");
        else
            url = new URL("http://finance.yahoo.com/q?s=" + args[0]);

        InputStream is = url.openStream();
        Scanner in = new Scanner(is);
        while (in.hasNextLine()) {
            System.out.println(in.nextLine());
        }
    }
}


