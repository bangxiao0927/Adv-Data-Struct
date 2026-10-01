import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

// Draws the desert background and scatters the items across the sand.
public class Screen extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int HORIZON = 380;

    // The band of sand where an item is allowed to land.
    private static final int GROUND_TOP = 390;
    private static final int GROUND_BOTTOM = 580;
    private static final int MARGIN = 20;

    private ArrayList<Scenery> items;

    public Screen() {
        items = new ArrayList<Scenery>();
        Random random = new Random();

        // Each unique item is added several times at a random spot.
        int cacti = 3 + random.nextInt(3);
        for (int i = 0; i < cacti; i++) {
            items.add(new Cactus(randomX(Cactus.WIDTH, random),
                                 randomY(Cactus.HEIGHT, random)));
        }

        int rocks = 3 + random.nextInt(3);
        for (int i = 0; i < rocks; i++) {
            items.add(new Rock(randomX(Rock.WIDTH, random),
                               randomY(Rock.HEIGHT, random)));
        }

        int tumbleweeds = 4 + random.nextInt(3);
        for (int i = 0; i < tumbleweeds; i++) {
            items.add(new Tumbleweed(randomX(Tumbleweed.WIDTH, random),
                                     randomY(Tumbleweed.HEIGHT, random)));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    // Picks an x that keeps the whole item inside the panel.
    private int randomX(int itemWidth, Random random) {
        return MARGIN + random.nextInt(WIDTH - itemWidth - 2 * MARGIN);
    }

    // Picks a y that keeps the whole item standing on the sand.
    private int randomY(int itemHeight, Random random) {
        return GROUND_TOP + random.nextInt(GROUND_BOTTOM - GROUND_TOP - itemHeight);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSky(g);
        drawSun(g);
        drawMountains(g);
        drawGround(g);

        for (int i = 0; i < items.size(); i++) {
            items.get(i).drawMe(g);
        }
    }

    // A vertical gradient from blue sky down to a warm horizon.
    private void drawSky(Graphics g) {
        for (int y = 0; y < HORIZON; y++) {
            int red = 120 + y * 135 / HORIZON;
            int green = 180 + y * 34 / HORIZON;
            int blue = 235 - y * 82 / HORIZON;
            g.setColor(new Color(red, green, blue));
            g.drawLine(0, y, WIDTH, y);
        }
    }

    private void drawSun(Graphics g) {
        g.setColor(new Color(255, 236, 179));
        g.fillOval(600, 55, 150, 150);
        g.setColor(new Color(255, 205, 60));
        g.fillOval(625, 80, 100, 100);
    }

    // Distant mountains whose bases are hidden by the sand.
    private void drawMountains(Graphics g) {
        g.setColor(new Color(180, 130, 110));
        g.fillPolygon(new int[] {0, 140, 300},
                      new int[] {HORIZON, 250, HORIZON}, 3);
        g.fillPolygon(new int[] {220, 400, 600},
                      new int[] {HORIZON, 285, HORIZON}, 3);
        g.fillPolygon(new int[] {520, 680, 800},
                      new int[] {HORIZON, 240, HORIZON}, 3);
    }

    private void drawGround(Graphics g) {
        g.setColor(new Color(232, 193, 122));
        g.fillRect(0, HORIZON, WIDTH, HEIGHT - HORIZON);

        // Ripples of sand for a little depth.
        g.setColor(new Color(214, 172, 104));
        g.fillOval(-60, HORIZON + 20, 360, 40);
        g.fillOval(280, HORIZON + 70, 460, 46);
        g.fillOval(80, HORIZON + 140, 520, 50);

        // Darker sand along the very bottom of the panel.
        g.setColor(new Color(205, 160, 95));
        g.fillRect(0, 540, WIDTH, HEIGHT - 540);
    }
}
