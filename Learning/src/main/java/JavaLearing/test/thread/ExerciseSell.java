package JavaLearing.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;


public class ExerciseSell {
    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow(2000);
        List<Thread> list = new ArrayList<>();
        List<Integer> sellCount = new Vector<>();
//        List<Integer> sellCount = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            Thread t = new Thread(() -> {

//                try {
//                    sleep(0);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                int count = ticketWindow.sell(randomAmount());
//                int count = ticketWindow.sell(1);
                sellCount.add(count);
            });
            list.add(t);
            t.start();
        }
        list.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(sellCount.stream().mapToInt(c -> c).sum());
        System.out.println(ticketWindow.getCount());
    }

    static Random random = new Random();

    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}

//class TicketWindow {
//    private AtomicInteger count = new AtomicInteger();
//    public TicketWindow(int count) {
//        this.count.set(count);
//    }
//    public int getCount() {
//        return count.get();
//    }
//    public int sell(int amount) {
//        if (count.get() >= amount) {
//            count.getAndSet(count.get() - amount);
//            return amount;
//        } else {
//            return 0;
//        }
//    }
//}

class TicketWindow {
    private int count;
    public TicketWindow(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }
    public int sell(int amount) {
        synchronized (this)
        {
            if (count >= amount) {
                count -= amount;
                return amount;
            } else {
                return 0;
            }
        }
    }
}