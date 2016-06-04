package Examples1;


/***************************************************************************
 * Example 1-4 FindDirectoriesNoRecursion.java
 * <p>
 * This example demonstrates how you can convert recursion to using loops
 * for better performance and lower memory foot print. In addition it
 * demonstrates how to use the File class.  The original example using
 * recursion is one of the examples from Core Java volume II.
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindDirectoriesNoRecursion {

    public static void main(String[] args) {
        if (args.length == 0)
            args = new String[]{".."};

        List<String> nextDir = new ArrayList<>();
        nextDir.add(args[0]);

        try {

            while (nextDir.size() > 0) {
                File pathName = new File(nextDir.get(0));
                String[] fileNames = pathName.list();

                for (int i = 0; i < fileNames.length; i++) {
                    File f = new File(pathName.getPath(), fileNames[i]);
                    if (f.isDirectory()) {
                        System.out.println(f.getCanonicalPath());
                        nextDir.add(f.getPath());
                    }
                }
                nextDir.remove(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}