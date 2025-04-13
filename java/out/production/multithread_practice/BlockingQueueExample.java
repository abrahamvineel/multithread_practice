package out.production.multithread_practice;


import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> bq = new LinkedBlockingQueue<>(3);
        tempSensorProd(bq);
        tempSensorCons(bq);

        Thread.sleep(5000);
    }

    private static void tempSensorProd(BlockingQueue<Integer> bq) {
        try {
            while(true) {
                Random r = new Random();
                int val = r.nextInt();
                System.out.println("Temp sensor value generated %d" + val);
                bq.put(val);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void tempSensorCons(BlockingQueue<Integer> bq) {
        while(true) {
            System.out.println("Consumer received the value" + bq.poll());
        }
    }
}
