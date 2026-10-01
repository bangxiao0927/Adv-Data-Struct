import java.awt.Color;
import java.awt.Graphics;

// Item 2: a rounded boulder built from a polygon.
public class Rock extends Scenery {
    public static final int WIDTH = 90;
    public static final int HEIGHT = 55;

    public Rock(int x, int y) {
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

        int[] xPoints = {getX(), getX() + 16, getX() + 42, getX() + 72,
                         getX() + 90, getX() + 74, getX() + 22};
        int[] yPoints = {getY() + 55, getY() + 16, getY(), getY() + 5,
                         getY() + 38, getY() + 55, getY() + 55};
        g.setColor(new Color(120, 110, 100));
        g.fillPolygon(xPoints, yPoints, 7);

        // The sunlit face on the upper left.
        int[] litX = {getX() + 16, getX() + 42, getX() + 58, getX() + 30};
        int[] litY = {getY() + 16, getY(), getY() + 16, getY() + 30};
        g.setColor(new Color(160, 150, 138));
        g.fillPolygon(litX, litY, 4);
    }
}
