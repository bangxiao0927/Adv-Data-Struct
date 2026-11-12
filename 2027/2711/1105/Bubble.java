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
    private boolean running = true;
    private boolean popped = false;

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
    public synchronized void drawMe(Graphics g) {
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
            while (isRunning()) {
                int newX = getX() + dx;
                int newY = getY() + dy;
                // bounce off the walls. Put the bubble back inside, so it never goes past the edge.
                if (newX - radius < 0) {
                    newX = radius;
                    dx = Math.abs(dx);
                }
                if (newX + radius > width) {
                    newX = width - radius;
                    dx = -Math.abs(dx);
                }
                if (newY - radius < 0) {
                    newY = radius;
                    dy = Math.abs(dy);
                }
                if (newY + radius > height) {
                    newY = height - radius;
                    dy = -Math.abs(dy);
                }
                moveTo(newX, newY);
                Thread.sleep(40);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // move the bubble to a new position
    public synchronized void moveTo(int newX, int newY) {
        x = newX;
        y = newY;
    }

    // the bubble keeps moving until it is stopped or popped
    public synchronized boolean isRunning() {
        return running && !popped;
    }

    public synchronized void stop() {
        running = false;
    }

    public synchronized void pop() {
        popped = true;
    }

    public synchronized boolean isPopped() {
        return popped;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    // a click counts if it is inside the bubble, or up to 5 pixels outside its edge.
    // the bubble on screen can be a few pixels away from its real position,
    // so this allows clicks on the edge to work.
    public synchronized boolean contains(int mx, int my) {
        int reach = radius + 5;
        int dx2 = mx - x;
        int dy2 = my - y;
        return dx2 * dx2 + dy2 * dy2 <= reach * reach;
    }
}
