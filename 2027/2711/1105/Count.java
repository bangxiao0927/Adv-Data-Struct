public class Count implements Runnable {
    public Count() {}

    public void run() {
        try {
            Thread.sleep(1000); //millisecond
        } catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
}
    
