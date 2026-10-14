import java.awt.Color;
import java.awt.Graphics;

// Item 4: a desert pyramid with two faces and stone courses.
public class Pyramid extends Scenery {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 85;

    public Pyramid(int x, int y) {
        super(x, y);
    }

    public String getName() {
        return "Pyramid";
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public void drawMe(Graphics g) {
        drawShadow(g);
        int x = getX();
        int y = getY();
        g.setColor(new Color(193, 133, 64));
        g.fillPolygon(new int[] {x, x + 55, x + 100},
                      new int[] {y + 85, y, y + 85}, 3);
        g.setColor(new Color(241, 193, 113));
        g.fillPolygon(new int[] {x, x + 55, x + 65},
                      new int[] {y + 85, y, y + 85}, 3);
        g.setColor(new Color(166, 112, 53));
        g.drawLine(x + 16, y + 60, x + 61, y + 60);
        g.drawLine(x + 32, y + 35, x + 58, y + 35);
        g.drawLine(x + 55, y, x + 65, y + 85);
    }
}
