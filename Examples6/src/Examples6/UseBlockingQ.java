package Examples6;

/***************************************************************************
 * Example 6-4 UseBlockingQ.java
 * This examples is a rewrite of wait notify example except it was rewritten
 * to take advantage of the java concurrency APIs; specifically blocking Q.
 * It demonstrates the same Producer, Consumer concept except the concurrency
 * API abstract away all the code for synchronization, wait, and notify.  The
 * code is so much easier to write, read and maintain.
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

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class UseBlockingQ implements Runnable {
    private boolean runstate = true;
    private BlockingQueue<String> mailBox = new LinkedBlockingQueue<>();

    public static void main(String args[]) {
        UseBlockingQ wne;
        final Thread service = new Thread(wne = new UseBlockingQ());
        service.start();

        Sender send;
        final Thread sender = new Thread(send = new Sender(wne));
        sender.start();
        final Thread sender1 = new Thread(new Sender(wne));
        sender1.start();
        final Thread sender2 = new Thread(new Sender(wne));
        sender2.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            // anonymous shutdown hook
            public void run() {
                System.out.println("Caught <ctrl-c>, shutting thread down!");
                service.interrupt();
                sender.interrupt();
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
            while (runstate) { // run forever until interrupted!
                String s = mailBox.take();
                System.out.println("Consume message: " + s);
            }
            System.out.println(
                    "Thread normal exit.  Exiting service without exception...");
        } catch (InterruptedException e) {
            // thread interrupted via thread.interrupt()
            System.out.println("Thread interrupted.  Exiting service...");
        }
    }

    void addMessage(String msg) {
        try {
            System.out.println("Produce message:" + msg);
            this.mailBox.put(msg);
        } catch (InterruptedException e) {
            //thread interrupted via thread.interrupt()
            System.out.println("Thread interrupted.");
        } finally {
        }
    }

    private void kill() {
        this.runstate = false;
        System.out.println("killed thread!");
    }
}

class Sender
        implements Runnable {
    private boolean runstate = true;
    private UseBlockingQ wne = null;

    Sender(UseBlockingQ processor) {
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



