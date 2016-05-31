package com.makesrc;

/***************************************************************************
 * Example 3-6 MatchPhone.java
 * <p>
 * This example shows how to extract matched data as groups within the
 * overall matched String.  Extracting the parts then recombining to create
 * a newly formatted String.  In our example, a phone number is extracted
 * from the command line argument and then reformatted.  For example:
 * 8888675309 would be             (888) 867-5309      .
 * <p>
 * email:  kent.yang@gmail.com kentyang@alumni.ucsd.edu
 * URL:    www.makesrc.com
 * <p>
 * If you enjoy the Java 3 class and the examples provided, please look
 * for other classes I teach.  In addition, please recommend my classes to
 * your co-workers, friends and family. Thank you.
 *
 * @author Kent Yang
 * @version 1.0 2010-01-20
 ***************************************************************************/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchPhone {
    public static void main(String args[]) {
        String strToProcess = "8588675309";
        if (args.length > 0)
            strToProcess = args[0];

        Pattern p =
                Pattern.compile("\\(?([0-9]{3})\\)?[- ]?(\\d{3})[- ]?([0-9]{4})\\b");
        Matcher m = p.matcher(strToProcess);
        if (m.matches())
            System.out.println(m.replaceAll("$0 reformatted is ($1) $2-$3"));
    }
}
