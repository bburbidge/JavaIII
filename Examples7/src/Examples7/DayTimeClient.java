package Examples7;

/***************************************************************************
 * Example 7-2 DayTimeClient.java
 * This class represents a client network application capable of asking a
 * DayTime service (port 13) running on the local machine.   The returned
 * data from the Time Server is a text string representing current
 * date/time.
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
 **************************************************************************/

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class DayTimeClient {
    public static void main(String args[]) {
        try {
            DatagramSocket socket = new DatagramSocket();
            try {
                DatagramPacket packet = new DatagramPacket(new byte[0], 0,
                        InetAddress.getByName("127.0.0.1"), 13);
                socket.send(packet);
                byte[] recBuf = new byte[256];
                DatagramPacket recPacket =
                        new DatagramPacket(recBuf, recBuf.length);
                socket.receive(recPacket);
                System.out.println("Current Date and Time: " +
                        new String(recPacket.getData(), 0, recPacket.getLength()));
            } finally {
                if (socket != null)
                    socket.close();
            }
        } catch (SocketException e) {
        } catch (IOException e) {
        }
    }
}


