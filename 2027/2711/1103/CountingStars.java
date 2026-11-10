public class CountingStars implements Runnable {
    public void run() {
        int count = 1;
        while (true) {
            System.out.println(count);
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.println("I am not done");
                return;
            }
        }
    }
}