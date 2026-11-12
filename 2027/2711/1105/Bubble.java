import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Bubble implements Runnable {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private final int radius;
    private final Color color;
    private final int width;
    private final int height;
    private volatile boolean running = true;
    private volatile boolean popped = false;

    public Bubble(int width, int height) {
        this.width = width;
        this.height = height;
        Random r = new Random();
        radius = 18 + r.nextInt(22);
        x = r.nextInt(width - 2 * radius) + radius;
        y = r.nextInt(height - 2 * radius) + radius;
        dx = 2 + r.nextInt(3);
        if (r.nextBoolean()) {
            dx = -dx;
        }
        dy = 2 + r.nextInt(3);
        if (r.nextBoolean()) {
            dy = -dy;
        }
        color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    // draw the bubble (nothing is drawn once it is popped)
    public void drawMe(Graphics g) {
        if (popped) {
            return;
        }
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.setColor(Color.BLACK);
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    // move the bubble and bounce off the walls
    public void run() {
        try {
            while (running && !popped) {
                x += dx;
                y += dy;
                if (x - radius < 0 || x + radius > width) {
                    dx = -dx;
                }
                if (y - radius < 0 || y + radius > height) {
                    dy = -dy;
                }
                Thread.sleep(40);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        running = false;
    }

    public void pop() {
        popped = true;
    }

    public boolean isPopped() {
        return popped;
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

    public boolean contains(int mx, int my) {
        int dx2 = mx - x;
        int dy2 = my - y;
        return dx2 * dx2 + dy2 * dy2 <= radius * radius;
    }
}
