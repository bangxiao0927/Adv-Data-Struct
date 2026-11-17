public class Animate implements Runnable {
    private final Screen screen;

    public Animate(Screen screen) {
        this.screen = screen;
    }

    public void run() {
        try {
            while (true) {
                screen.repaint();
                Thread.sleep(20);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
