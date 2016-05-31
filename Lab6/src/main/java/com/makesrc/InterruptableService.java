package com.makesrc;

/***************************************************************************
 * Example 6-3 InterruptService.java
 * Demonstrates creation of a service thread that will run indefinitely
 * until interrupted.  Demonstrates adding a Shutdown hook which will allow
 * a <ctrl-c> hook to terminate the processes.  Provide a mechanism for
 * proper cleanup of resources using finally.
 * <p>
 * email:  kentyang@alumni.ucsd.edu
 * URL:    www.javathehut.org
 * <p>
 * If you enjoy the Java 3 class and the examples provided, please look
 * for other classes I teach.  In addition, please recommend my classes to
 * your co-workers, friends and family. Thank you.
 *
 * @author Kent Yang
 * @version 1.0 2010-02-10
 ***************************************************************************/
public class InterruptableService
        implements Runnable {
    public static void main(String args[]) {
        final Thread service = new Thread(new InterruptableService());
        service.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            // anonymous shutdown hook
            public void run() {
                System.out.println("Caught <ctrl-c>, shutting thread down!");
                service.interrupt();
            }
        });
        System.out.println("Shutdown hook added");
    }

    public void run() {
        int counter = 0;

        try {
            while (!Thread.currentThread().isInterrupted()) {  // run forever until interrupted!
                System.out.println("Slept " + counter + " seconds.");
                Thread.currentThread().sleep(1000);
                counter++;
            }
            System.out.println("Thread interrupted.  Exiting service without exception...");
        } catch (InterruptedException e) {
            // thread interrupted via thread.interrupt()
            System.out.println("Thread interrupted.  Exiting service...");
        }
    }

}
