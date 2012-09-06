package sk.mka.books.threads.samaphore;

import java.util.concurrent.Semaphore;

/**
 *
 * @author bracek
 * @date Dec 22, 2009
 */
public class Main {

    public static void main(String args[]) throws Exception {
        Semaphore sem = new Semaphore(1, true);
        Thread thrdA = new Thread(new SyncOutput(sem, "Message 1"));
        Thread thrdB = new Thread(new SyncOutput(sem, "Message 2!"));

        thrdA.start();
        thrdB.start();

        thrdA.join();
        thrdB.join();

    }
}

class SyncOutput implements Runnable {

    Semaphore sem;
    String msg;

    SyncOutput(Semaphore s, String m) {
        sem = s;
        msg = m;
    }

    public void run() {
        try {
            sem.acquire();
            System.out.println(msg);
            Thread.sleep(10);
        } catch (Exception exc) {
            System.out.println("Error Writing File");
        }
        sem.release();
    }
}
