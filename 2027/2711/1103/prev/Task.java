public class Task implements Runnable {
    private Manager manager;

    public Task(Manager manager) {
        this.manager = manager;
    }

    public void run() {
        manager.doubleIt();
    }
}