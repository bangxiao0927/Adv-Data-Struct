public class Countdown implements Runnable {
    private final Screen screen;
    private int seconds;

    public Countdown(Screen screen, int seconds) {
        this.screen = screen;
        this.seconds = seconds;
    }

    public void run() {
        try {
            while (seconds > 0 && !screen.isGameOver()) {
                Thread.sleep(1000);
                seconds--;
                screen.setSeconds(seconds);
                screen.repaint();
            }
            if (!screen.isGameOver()) {
                screen.finish(false);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public int getSeconds() {
        return seconds;
    }
}
