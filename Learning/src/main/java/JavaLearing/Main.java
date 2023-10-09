package JavaLearing;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Main {
    public static class MyRunnable implements Runnable {
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("my runnable");
        }
    }
    public static class MyThread extends Thread {
        public void run() {
            System.out.println("my thread");
        }
    }
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        System.out.println("Hello world!");
        MyRunnable myRunnable = new MyRunnable();
        MyThread myThread = new MyThread();

        Thread thread1 = new Thread(myRunnable);
        thread1.start();
        myThread.start();
    }
}