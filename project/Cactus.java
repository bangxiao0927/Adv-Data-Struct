import java.awt.Color;
import java.awt.Graphics;

// Item 1: a saguaro cactus, the tall object in the desert.
public class Cactus extends Scenery {
    public static final int WIDTH = 60;
    public static final int HEIGHT = 110;

    public Cactus(int x, int y) {
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

        // Trunk.
        g.setColor(new Color(46, 125, 50));
        g.fillRect(getX() + 20, getY(), 20, HEIGHT);

        // Left arm: a short stem that turns upward.
        g.fillRect(getX(), getY() + 40, 20, 14);
        g.fillRect(getX(), getY() + 18, 14, 36);

        // Right arm: a little higher and shorter.
        g.fillRect(getX() + 40, getY() + 55, 20, 14);
        g.fillRect(getX() + 46, getY() + 32, 14, 37);

        // A lighter ridge running down the trunk.
        g.setColor(new Color(102, 187, 106));
        g.drawLine(getX() + 30, getY() + 4, getX() + 30, getY() + HEIGHT - 4);
    }
}
