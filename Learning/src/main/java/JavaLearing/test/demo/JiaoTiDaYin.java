package JavaLearing.test.demo;


import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class JiaoTiDaYin {
    static ReentrantLock reentrantLock = new ReentrantLock();
    static Condition conditionA = reentrantLock.newCondition();
    static Condition conditionB = reentrantLock.newCondition();
    static Condition conditionC = reentrantLock.newCondition();
    static int num = 100;
    static int cur = 1;

    static class PrintThread extends Thread {
        Condition conditionCur;
        Condition conditionNext;
        @Override
        public void run() {
            try {
                reentrantLock.lock();
                while (cur <= num) {
                    System.out.println(Thread.currentThread().getName() + " " + cur);
                    ++cur;
                    conditionNext.signal();
                    conditionCur.await();
                }
                conditionNext.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }

        }
        public PrintThread(Condition conditionCur, Condition conditionNext) {
            this.conditionCur = conditionCur;
            this.conditionNext = conditionNext;
        }
    }
    static class PrintRunnable implements Runnable {
        public void run() {

        }
    }
    public static void main(String[] args) {
        Thread threadA = new PrintThread(conditionA, conditionB);
        Thread threadB = new PrintThread(conditionB, conditionC);
        Thread threadC = new PrintThread(conditionC, conditionA);
        threadA.start();
        threadB.start();
        threadC.start();
        Object tm= new Object();
        LockSupport.park();
    }

}
