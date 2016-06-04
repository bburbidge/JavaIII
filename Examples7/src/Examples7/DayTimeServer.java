package Examples7;


/***************************************************************************
 * Example 7-1 DayTimeServer.java
 * A class that is used to provide the Day Time Service (port 13) as server
 * using the UDP protocol.
 * <p>
 * email:  kentyang@alumni.ucsd.edu
 * URL:    www.javathehut.org
 * <p>
 * If you enjoy the Java 3 class and the examples provided, please look
 * for other classes I teach.  In addition, please recommend my classes to
 * your co-workers, friends and family. Thank you.
 *
 * @author Kent Yang
 * @version 1.0 2010-02-24
 ***************************************************************************/

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DayTimeServer
        implements Runnable {
    public static void main(String args[]) {
        final Thread service = new Thread(new DayTimeServer());
        service.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            // anonymous shutdown hook
            public void run() {
                System.out.println(
                        "Caught <ctrl-c>, shutting DayTime Service down!");
                service.interrupt();
            }
        });
    }

    public void run() {
        try {
            // we are going to use the standard DayTime Service port
            DatagramSocket serverSocket = new DatagramSocket(13);
            try {
                while (!Thread.currentThread().isInterrupted()) { // run forever until interrupted!
                    DatagramPacket packet =
                            new DatagramPacket(new byte[0], 0);
                    serverSocket.receive(packet); // this call blocks

                    String currentTimeStr =
                            java.util.Calendar.getInstance().getTime().toString();

                    byte[] buffer = currentTimeStr.getBytes();
                    DatagramPacket timePacket =
                            new DatagramPacket(buffer, // buffer to send
                                    buffer.length, //size of buffer
                                    packet.getAddress(),
                                    packet.getPort());
                    serverSocket.send(timePacket);
                }
            } finally {
                if (serverSocket != null)
                    serverSocket.close();
            }
        } catch (Exception x) {
            System.err.println("Oh Oh, exception:" + x);
        }
    }
}
