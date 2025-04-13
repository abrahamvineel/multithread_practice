import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class WorkerThread1 implements Runnable{
    private int id;

    public WorkerThread1(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Worker " + id + " starting in thread " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Worker " + id + " finished in thread " + Thread.currentThread().getName());
    }
}
public class ExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            es.execute(new WorkerThread1(i));
        }

        es.shutdown();
        es.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Main thread is finished");
    }
}
