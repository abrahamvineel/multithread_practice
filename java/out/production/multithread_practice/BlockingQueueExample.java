package out.production.multithread_practice;


import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> bq = new LinkedBlockingQueue<>(3);
        Thread t1 = new Thread(() -> tempSensorProd(bq));
        Thread t2 = new Thread(() -> tempSensorCons(bq));

        t1.start();
        t2.start();

        Thread.sleep(5000);

        t1.interrupt();
        t2.interrupt();

        t1.join();
        t2.join();
        System.out.println("Main thread finished");
    }

    private static void tempSensorProd(BlockingQueue<Integer> bq) {
        try {
            Random r = new Random();
            while(!Thread.currentThread().isInterrupted()) {
                int val = r.nextInt(100);
                System.out.println("Temp sensor value generated " + val);
                bq.put(val);
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500) + 500);
            }
            System.out.println("Producer finished");
        } catch (InterruptedException e) {
            System.out.println("Producer interrupted");
            Thread.currentThread().interrupt();
        }
    }

    private static void tempSensorCons(BlockingQueue<Integer> bq) {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                Integer val = bq.take();
                if(val != null) {
                    System.out.println("Consumer received the value " + val);
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(800) + 200);
                }
            }
            System.out.println("Consumer finished");
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
