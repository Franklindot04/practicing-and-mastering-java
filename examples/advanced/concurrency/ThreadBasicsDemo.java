public class ThreadBasicsDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            System.out.println("Worker thread: " + Thread.currentThread().getName());
        });

        worker.start();
        worker.join();

        System.out.println("Main thread: " + Thread.currentThread().getName());
    }
}
