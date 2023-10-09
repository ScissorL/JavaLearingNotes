package JavaLearing.test.demo;


import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProviderConsumer {
    static List<Integer> list = new ArrayList<>();
    static int capacity;
    static int size;
    static ReentrantLock lock = new ReentrantLock(true);
    static Condition empty = lock.newCondition();
    static Condition full = lock.newCondition();

    static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                while (size == 0) {
                    try {
                        empty.await();
                    } catch (InterruptedException e) {
                        lock.unlock();
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(list.get(size - 1));
                list.remove(size - 1);
                --size;
                full.signalAll();
                lock.unlock();
                try {
                    sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    static class Provider extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                while (size == capacity) {
                    try {
                        full.await();
                    } catch (InterruptedException e) {
                        lock.unlock();
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("list add: " + (size + 1));
                ++size;
                list.add(size);
                empty.signalAll();
                lock.unlock();
                try {
                    sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void main(String[] args) {
        capacity = 20;
        size = 0;
        Provider provider1 = new Provider();
        Provider provider2 = new Provider();
        Provider provider3 = new Provider();
        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();
        provider1.start();
        provider2.start();
        provider3.start();
        consumer1.start();
        consumer2.start();
    }
}
