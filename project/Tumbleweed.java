import java.awt.Color;
import java.awt.Graphics;

// Item 3: a dry tumbleweed drawn as a tangled ball.
public class Tumbleweed extends Scenery {
    public static final int WIDTH = 55;
    public static final int HEIGHT = 55;

    public Tumbleweed(int x, int y) {
        super(x, y);
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public void drawMe(Graphics g) {
        drawShadow(g);
        g.setColor(new Color(139, 105, 20));

        // Two rings plus a few crossing strands.
        g.drawOval(getX(), getY(), WIDTH, HEIGHT);
        g.drawOval(getX() + 8, getY() + 8, WIDTH - 16, HEIGHT - 16);

        g.drawLine(getX(), getY() + 27, getX() + WIDTH, getY() + 27);
        g.drawLine(getX() + 27, getY(), getX() + 27, getY() + HEIGHT);
        g.drawLine(getX() + 8, getY() + 8, getX() + 47, getY() + 47);
        g.drawLine(getX() + 47, getY() + 8, getX() + 8, getY() + 47);
    }
}
