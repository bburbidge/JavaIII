package com.makesrc;

/***************************************************************************
 * Example 7-3 DayTimeTcpServer.java
 * A class that is used to provide the Day Time Service (port 13) as server
 * using the TCP protocol.
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

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DayTimeTcpServer
        implements Runnable {
    public static void main(String args[]) {
        final Thread service = new Thread(new DayTimeTcpServer());
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
            ServerSocket serverSocket = new ServerSocket(13);
            try {
                while (!Thread.currentThread().isInterrupted()) { // run forever until interrupted!
                    Socket socket = serverSocket.accept(); // blocking call
                    new Thread(new Worker(socket)).start();
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

class Worker
        implements Runnable {
    private Socket socket;

    public Worker(Socket s) {
        this.socket = s;
    }

    public void run() {
        try {
            try {
                PrintWriter out =
                        new PrintWriter(this.socket.getOutputStream(), true);

                out.println(
                        java.util.Calendar.getInstance().getTime().toString());
            } finally {
                if (this.socket != null)
                    this.socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


