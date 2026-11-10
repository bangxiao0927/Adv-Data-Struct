public class Count implements Runnable {
    public void run() {
        // Print the thread name (or id)
        System.out.println("Thread name: " + Thread.currentThread().getName()
                         + " | Thread id: " + Thread.currentThread().getId());

        // Count from 1 to 10 with a 1-second wait between each number
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + i);
            try {
                Thread.sleep(1000); // millisecond
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}