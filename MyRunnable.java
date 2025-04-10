public class MyRunnable implements Runnable{

    private String threadName;

    public MyRunnable(String name) {
        threadName = name;
        System.out.println("creating thread" + name);
    }

    @Override
    public void run() {
        System.out.println("running" + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread " + threadName + " Count " + i);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted");
        }
        System.out.println("Thread " + threadName + " exiting");
    }
}
