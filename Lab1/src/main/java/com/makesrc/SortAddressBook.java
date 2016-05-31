package com.makesrc;

/***************************************************************************
 * Example 1-3b SortAddressBook.java
 * <p>
 * A text processing class that will take the read for follows the read,
 * process, write a new file after completing the
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

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortAddressBook {

    private static final FirstNameComparator FIRSTNAMECOMPARATOR = new FirstNameComparator();

    public static void main(String[] args) {
        try {
            // Step 1: Read Text From File
            List<String> textData = TextFileUtils.readTextFile(args[0]);

            // Step 2: Process Text Data
            Collections.sort(textData, FIRSTNAMECOMPARATOR);

            // Step 3: Write Text Data toFile
            TextFileUtils.writeTextFile(textData, args[1]);

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

class FirstNameComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        String[] st1 = s1.split(" ");
        String[] st2 = s2.split(" ");

        String firstName1 = st1[0].toUpperCase();
        String lastName1 = st1[1].toUpperCase();
        String firstName2 = st2[0].toUpperCase();
        String lastName2 = st2[1].toUpperCase();
        if (!(firstName1.equals(firstName2)))
            return firstName1.compareTo(firstName2);
        else
            return lastName1.compareTo(lastName2);
    }
}

