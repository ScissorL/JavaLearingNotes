package JavaLearing.test.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ContainerUseSemaphore {
    Semaphore empty;
    static Semaphore full = new Semaphore(0);
    static Semaphore lock = new Semaphore(1);
    static List<Integer> list = new ArrayList<>();
    int num = 0;
    ContainerUseSemaphore(int empty) {
        this.empty = new Semaphore(empty);
    }
    public void put() {
        try {
            empty.acquire();
            lock.acquire();
            list.add(num);
            System.out.println(Thread.currentThread() +  " add: " + num);
            ++num;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.release();
            full.release();
        }
    }
    public void take() {
        try {
            full.acquire();
            lock.acquire();
            --num;
            System.out.println(Thread.currentThread() +  " take: " + num);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.release();
            empty.release();
        }
    }

    public static void main(String[] args) {
        ContainerUseSemaphore containerUseSemaphore = new ContainerUseSemaphore(20);
        Provider provider = new Provider(containerUseSemaphore);
        Provider provider1 = new Provider(containerUseSemaphore);
        Provider provider2 = new Provider(containerUseSemaphore);
        Consumer consumer = new Consumer(containerUseSemaphore);
        Consumer consumer1 = new Consumer(containerUseSemaphore);
        provider.start();
        provider1.start();
        provider2.start();
        consumer.start();
        consumer1.start();
    }
    public static class Provider extends Thread {
        ContainerUseSemaphore containerUseSemaphore;
        Provider(ContainerUseSemaphore containerUseSemaphore) {
            this.containerUseSemaphore = containerUseSemaphore;
        }
        @Override
        public void run() {
            while (true) {
                containerUseSemaphore.put();
            }
        }

    }
    public static class Consumer extends Thread{
        ContainerUseSemaphore containerUseSemaphore;
        Consumer(ContainerUseSemaphore containerUseSemaphore) {
            this.containerUseSemaphore = containerUseSemaphore;
        }
        @Override
        public void run() {
            while (true) {
                this.containerUseSemaphore.take();
            }
        }
    }
}
