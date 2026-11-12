import java.awt.Color;
import java.awt.Graphics;

public class Circle implements Runnable {
    private int x;
    private final int y;
    private final int radius;
    private boolean running = true;

    public Circle(int x) {
        this.x = x;
        this.y = 300;
        this.radius = 40;
    }

    // draw the circle based upon x
    public void drawMe(Graphics g) {
        g.setColor(new Color(100, 149, 237)); // cornflower blue
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.setColor(Color.BLACK);
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    public void run() {
        try {
            while (isRunning()) {
                x += 5;
                // wrap around when it leaves the screen
                if (x - radius > 800) {
                    x = radius;
                }
                Thread.sleep(100); // wait 100 milliseconds between increments
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public synchronized void stop() {
        running = false;
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public boolean contains(int mx, int my) {
        int dx = mx - x;
        int dy = my - y;
        return dx * dx + dy * dy <= radius * radius;
    }
}
