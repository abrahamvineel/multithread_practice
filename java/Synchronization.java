import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedCounter {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public synchronized void increment() {
        count++;
        System.out.println("Incremented (synchronized method) by " + Thread.currentThread().getName() + ", Count: " + count);

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void decrement() {
        synchronized (this) {
            count--;
            System.out.println("Decremented (synchronized block) by " + Thread.currentThread().getName() + ", Count: " + count);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void incrementReentrantLock() {
        lock.lock();
        try {
            count++;
            System.out.println("Increment (Reentrant lock) by " + Thread.currentThread().getName() + ", Count: " + count);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}

class WorkerThread implements Runnable {
    private final SharedCounter counter;
    private final String operationType;

    public WorkerThread(SharedCounter counter, String operationType) {
        this.counter = counter;
        this.operationType = operationType;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            switch (operationType) {
                case "increment":
                    counter.increment();
                case "decrement":
                    counter.decrement();
                case "reentrant":
                    counter.incrementReentrantLock();
            }
        }
    }
}

public class Synchronization {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter counter = new SharedCounter();
        WorkerThread w1 = new WorkerThread(counter, "increment");
        WorkerThread w2 = new WorkerThread(counter, "decrement");
        WorkerThread w3 = new WorkerThread(counter, "reentrant");

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w2);
        Thread t3 = new Thread(w3);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(counter.getCount());
    }
}