public class RunnableDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> System.out.println("Running reusable task");

        Thread first = new Thread(task);
        Thread second = new Thread(task);

        first.start();
        second.start();

        first.join();
        second.join();
    }
}
