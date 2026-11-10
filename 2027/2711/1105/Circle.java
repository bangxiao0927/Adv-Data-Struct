import java.awt.Color;
import java.awt.Graphics;

/**
 * Circle Class (Part 1 of the assignment)
 * - instance variable x, set up by the constructor
 * - drawMe(Graphics g) draws the circle based on x
 * - implements Runnable; run() increases x in a while loop
 *   with a 100 millisecond wait between each increment
 */
public class Circle implements Runnable {
    private int x;
    private final int y;
    private final int radius;
    private volatile boolean running = true;

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
            while (running) {
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

    public void stop() {
        running = false;
    }

    public boolean contains(int mx, int my) {
        int dx = mx - x;
        int dy = my - y;
        return dx * dx + dy * dy <= radius * radius;
    }
}
