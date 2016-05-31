package com.makesrc;


/***************************************************************************
 * Example 6-3 WaitNotifyExample.java
 * Demonstrates how to use the wait notify scheme to synchronize access to
 * share data between threads.  This is an example of Producer, Consumer
 * use case where producers produce messages (task generation) and consumers
 * consume messages (processing).
 * <p>
 * email:  kent.yang@gmail.com
 * URL:    www.makesrc.com
 * <p>
 * If you enjoy the Java 3 class and the examples provided, please look
 * for other classes I teach.  In addition, please recommend my classes to
 * your co-workers, friends and family. Thank you.
 *
 * @author Kent Yang
 * @version 1.0 2010-08-18
 *************************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WaitNotifyExample
        implements Runnable {
    private boolean runstate = true;
    private List<String> mailBox = new ArrayList<String>();

    public static void main(String args[]) {
        WaitNotifyExample wne = null;
        final Thread service = new Thread(wne = new WaitNotifyExample());
        service.start();

        Sender2 send = null;
        final Thread sender2 = new Thread(send = new Sender2(wne));
        sender2.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            // anonymous shutdown hook
            public void run() {
                System.out.println("Caught <ctrl-c>, shutting thread down!");
                service.interrupt();
                sender2.interrupt();
            }
        });
        System.out.println("Shutdown hook added");

        try {
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();

            if (input.equals("exit")) {
                wne.kill();
                send.kill();
            }
        } catch (Exception e) {
            System.out.println("Blocking read interrupted: ");
        }

        System.out.println("Existing...");
    }

    public void run() {
        try {
            synchronized (this) {
                while (runstate == true) { // run forever until interrupted!
                    for (String s : mailBox)
                        System.out.println("Consume message: " + s);

                    mailBox.clear();
                    this.wait();
                }
                System.out.println(
                        "Thread normal exit.  Exiting service without exception...");
            }
        } catch (InterruptedException e) {
            // thread interrupted via thread.interrupt()
            System.out.println("Thread interrupted.  Exiting service...");
        }
    }

    void addMessage(String msg) {
        //synchronized(this)
        {
            System.out.println("Produce message:" + msg);
            this.mailBox.add(msg);
            this.notify();
        }
    }

    private void kill() {
        this.runstate = false;
        System.out.println("killed thread!");
    }
}

class Sender2
        implements Runnable {
    private boolean runstate = true;
    private WaitNotifyExample wne = null;

    Sender2(WaitNotifyExample processor) {
        wne = processor;
    }

    void kill() {
        this.runstate = false;
        System.out.println("killed thread!");
    }

    public void run() {
        int counter = 0;

        try {
            while (runstate == true) {  // run forever until interrupted!
                counter++;
                wne.addMessage("Add new message " + counter + " seconds.");
                Thread.currentThread().sleep(1000);
            }
            System.out.println(
                    "Sender Thread interrupted.  Exiting service without exception...");
            wne.addMessage("Sender Existing...");
        } catch (InterruptedException e) {
            // thread interrupted via thread.interrupt()
            System.out.println("Sender Thread interrupted.  Exiting service...");
        }

    }
}


