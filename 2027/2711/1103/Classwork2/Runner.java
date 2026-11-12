public class Runner {
    public static void main(String[] args) throws InterruptedException {
        // Instantiate Manager and print it
        Manager manager = new Manager();
        System.out.println("Initial: " + manager.toString());

        // Instantiate Task passing in Manager
        Task task = new Task(manager);

        // Start the clock, so we can see how long the threads take
        long startTime = System.currentTimeMillis();

        // Create an array of Threads with size 15, set each to a new Thread(task), and start them all
        Thread[] threadList = new Thread[15];
        for (int i = 0; i < threadList.length; i++) {
            threadList[i] = new Thread(task);
            threadList[i].start();
        }

        // Wait for all threads to complete via join
        try {
            for (int i = 0; i < threadList.length; i++) {
                threadList[i].join();
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        long endTime = System.currentTimeMillis();

        // Print the manager after all threads complete
        System.out.println("Final:   " + manager.toString());
        System.out.println("Time:    " + (endTime - startTime) + " milliseconds");
    }
}