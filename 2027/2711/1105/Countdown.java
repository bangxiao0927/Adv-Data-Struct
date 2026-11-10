/**
 * Countdown timer thread for the bubble popping game.
 */
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
                Thread.sleep(1000); // tick once per second
                seconds--;
                screen.setSeconds(seconds);
                screen.repaint();
            }
            // time is up and the player has not won yet -> lose
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
