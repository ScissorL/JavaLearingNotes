package JavaLearing.test.thread;

import lombok.Data;

import javax.swing.undo.CannotUndoException;
import java.util.Stack;

public class PrintWithTwoThread {
    private static class PrintThread implements Runnable {
        private int num;
        private static final int total = 100;
        private static int current = 1;
        private static final Object lock = new Object();

        public PrintThread(int num) {
            this.num = num;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (current <= total) {
                        if ((current & 1) == num ) {
                            System.out.println(Thread.currentThread() + " i = " + current);
                            ++current;
                            lock.notify();
                        } else {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        return;
                    }
                }
            }

        }
    }
    public static void main(String[] args) throws InterruptedException {
        PrintThread printThread1 = new PrintThread(1);
        PrintThread printThread2 = new PrintThread(0);

        Thread thread1 = new Thread(printThread1);
        Thread thread2 = new Thread(printThread2);

        thread1.start();
        thread2.start();

    }
}
