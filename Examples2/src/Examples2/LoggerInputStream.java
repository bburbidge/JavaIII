package Examples2;

/**
 * LoggerInputStream.java
 * <p/>
 * This class demonstrates how to create a decorator for a Java Input Stream.
 * Specifically, this class will decorate a concrete class with the ability
 * to log text base data to a log file using Java's logging API.
 * <p/>
 * Copyright 2011 Kent Yang.  Released under
 * <a href="http://www.opensource.org/licenses/afl-3.0.php">
 * The Academic Free License 3.0 ("AFL") v.3.0</a> in USA.
 *
 * @author KentYang
 * @version 8/2/11 5:00 AM
 */


import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class LoggerInputStream
        extends FilterInputStream {
    // Use the static method to get a logger object capable of logging
    private static Logger logger;

    static {
        try {
            logger = Logger.getLogger(LoggerInputStream.class.getName());
            logger.addHandler(
                    new FileHandler(LoggerInputStream.class.getName() + ".log"));
        } catch (Exception e) {
            System.err.println("Can't create log file!");
            System.exit(-1);
        }
    }

    private InputStream lis;

    private LoggerInputStream(InputStream is) {
        super(is);
    }

    public static void main(String[] args) {
        try {
            InputStream is =
                    new LoggerInputStream(
                            new BufferedInputStream(
                                    new FileInputStream("sample.txt")));

            byte[] buffer = new byte[80];
            for (int bytesRead = 0; bytesRead >= 0; ) {
                bytesRead = is.read(buffer, 0, buffer.length);
                // We can do whatever we need to do to process the buffer
                // but we simply iterate through the file to demonstrate
                // a custom logging input stream (a decorator)
            /*
            for(int i = 0; i < bytesRead; i++)
            {
               System.out.print((char)buffer[i]);
            }
            */
            }

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int read(byte[] b, int offset, int len)
            throws IOException {
        int bytesRead = super.read(b, offset, len);
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < bytesRead; i++) {
            sb.append((char) b[i]);
        }

        // Default only messages log at the info level or
        // higher will appear in the console output
        // Call the logger's set level to change it
        logger.info(sb.toString()); // here is where we log
        //System.out.print(sb.toString()); // here is where we log

        return bytesRead;
    }
}