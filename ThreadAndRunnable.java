public class ThreadAndRunnable {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable("Thread 1");
        Thread t1 = new Thread(runnable);

        t1.start();

        MyRunnable runnable1 = new MyRunnable("Thread 2");

        Thread t2 = new Thread(runnable1);
        t2.start();
    }
}
