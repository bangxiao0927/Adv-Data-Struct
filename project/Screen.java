import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Draws the desert background and scatters the items across the sand.
public class Screen extends JPanel implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int HORIZON = 380;

    // The band of sand where an item is allowed to land.
    private static final int GROUND_TOP = 390;
    private static final int GROUND_BOTTOM = 580;
    private static final int MARGIN = 20;

    private DLList<Scenery> items;
    private Random random;
    private JLabel status;
    private JPanel canvas;
    private JButton addCactusButton;
    private JButton removeCactusButton;
    private JButton addRockButton;
    private JButton removeRockButton;
    private JButton addTumbleweedButton;
    private JButton removeTumbleweedButton;
    private JButton addPyramidButton;
    private JButton removePyramidButton;
    private JButton addScorpionButton;
    private JButton removeScorpionButton;
    private JButton randomizeButton;
    private JButton clearButton;

    public Screen() {
        items = new DLList<Scenery>();
        random = new Random();
        status = new JLabel();

        setLayout(new BorderLayout());
        canvas = new ScenePanel();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.addMouseListener(new ItemClickListener());
        add(canvas, BorderLayout.CENTER);

        JPanel controls = new JPanel(new GridLayout(0, 2, 8, 8));
        controls.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        addCactusButton = makeButton(controls, "Add Cactus");
        removeCactusButton = makeButton(controls, "Remove Cactus");
        addRockButton = makeButton(controls, "Add Rock");
        removeRockButton = makeButton(controls, "Remove Rock");
        addTumbleweedButton = makeButton(controls, "Add Tumbleweed");
        removeTumbleweedButton = makeButton(controls, "Remove Tumbleweed");
        addPyramidButton = makeButton(controls, "Add Pyramid");
        removePyramidButton = makeButton(controls, "Remove Pyramid");
        addScorpionButton = makeButton(controls, "Add Scorpion");
        removeScorpionButton = makeButton(controls, "Remove Scorpion");
        randomizeButton = makeButton(controls, "Randomize");
        clearButton = makeButton(controls, "Clear");
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.add(new JLabel("Desert Scenery", JLabel.CENTER), BorderLayout.NORTH);
        sidebar.add(controls, BorderLayout.CENTER);
        add(sidebar, BorderLayout.EAST);

        JPanel instructions = new JPanel(new GridLayout(2, 1));
        instructions.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        instructions.add(new JLabel("Challenge: Click any item to delete it. Overlapping items: click removes the top one."));
        instructions.add(status);
        add(instructions, BorderLayout.SOUTH);

        // Start with two of each type; the user can add any number afterward.
        addItem("Cactus");
        addItem("Cactus");
        addItem("Rock");
        addItem("Rock");
        addItem("Tumbleweed");
        addItem("Tumbleweed");
        addItem("Pyramid");
        addItem("Pyramid");
        addItem("Scorpion");
        addItem("Scorpion");
        refresh("Ready. Add or remove items using the buttons.");
    }

    private JButton makeButton(JPanel controls, String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        controls.add(button);
        return button;
    }

    public void actionPerformed(ActionEvent event) {
        JButton buttonPressed = (JButton) event.getSource();

        if (buttonPressed == randomizeButton) {
            randomizeItems();
        } else if (buttonPressed == clearButton) {
            items.clear();
            refresh("All items cleared.");
        } else if (buttonPressed == addCactusButton) {
            addItem("Cactus");
        } else if (buttonPressed == removeCactusButton) {
            removeItem("Cactus");
        } else if (buttonPressed == addRockButton) {
            addItem("Rock");
        } else if (buttonPressed == removeRockButton) {
            removeItem("Rock");
        } else if (buttonPressed == addTumbleweedButton) {
            addItem("Tumbleweed");
        } else if (buttonPressed == removeTumbleweedButton) {
            removeItem("Tumbleweed");
        } else if (buttonPressed == addPyramidButton) {
            addItem("Pyramid");
        } else if (buttonPressed == removePyramidButton) {
            removeItem("Pyramid");
        } else if (buttonPressed == addScorpionButton) {
            addItem("Scorpion");
        } else if (buttonPressed == removeScorpionButton) {
            removeItem("Scorpion");
        }
    }

    private void addItem(String type) {
        Scenery item;
        if (type.equals("Cactus")) {
            item = new Cactus(0, 0);
        } else if (type.equals("Rock")) {
            item = new Rock(0, 0);
        } else if (type.equals("Tumbleweed")) {
            item = new Tumbleweed(0, 0);
        } else if (type.equals("Pyramid")) {
            item = new Pyramid(0, 0);
        } else if (type.equals("Scorpion")) {
            item = new Scorpion(0, 0);
        } else {
            return;
        }
        placeRandomly(item);
        items.add(item);
        refresh(item.getName() + " added.");
    }

    private boolean matchesType(Scenery item, String type) {
        if (item.getName().equals(type)) {
            return true;
        }
        return false;
    }

    private void removeItem(String type) {
        int count = 0;
        for (Node<Scenery> node = items.getHead(); node != null; node = node.next()) {
            if (matchesType(node.get(), type)) {
                count++;
            }
        }
        if (count == 0) {
            refresh("No items of that type to remove.");
            return;
        }
        // Choose among only the matching items, with equal probability.
        int chosen = random.nextInt(count);
        int index = 0;
        for (Node<Scenery> node = items.getHead(); node != null; node = node.next()) {
            if (matchesType(node.get(), type)) {
                if (chosen == 0) {
                    Scenery removed = items.remove(index);
                    refresh(removed.getName() + " removed.");
                    return;
                }
                chosen--;
            }
            index++;
        }
    }

    private void placeRandomly(Scenery item) {
        item.setPosition(randomX(item.getWidth()), randomY(item.getHeight()));
    }

    private void randomizeItems() {
        for (Node<Scenery> node = items.getHead(); node != null; node = node.next()) {
            placeRandomly(node.get());
        }
        refresh("All item positions randomized.");
    }

    private void refresh(String message) {
        status.setText("Items: " + items.size() + " | " + message);
        canvas.repaint();
    }

    // Picks an x that keeps the whole item inside the panel.
    private int randomX(int itemWidth) {
        return MARGIN + random.nextInt(WIDTH - itemWidth - 2 * MARGIN + 1);
    }

    // Picks a y that keeps the whole item standing on the sand.
    private int randomY(int itemHeight) {
        return GROUND_TOP + random.nextInt(GROUND_BOTTOM - GROUND_TOP - itemHeight + 1);
    }

    private void drawScene(Graphics g) {
        drawSky(g);
        drawSun(g);
        drawMountains(g);
        drawGround(g);

        for (Node<Scenery> node = items.getHead(); node != null; node = node.next()) {
            node.get().drawMe(g);
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

    // Named helper classes keep the Swing drawing and mouse code together.
    private class ScenePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawScene(g);
        }
    }

    private class ItemClickListener extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            // Last drawn = topmost, so search backward through the list.
            Node<Scenery> node = items.getTail();
            int index = items.size() - 1;
            while (node != null) {
                if (node.get().contains(event.getX(), event.getY())) {
                    items.remove(index);
                    refresh("Clicked item removed.");
                    return;
                }
                node = node.prev();
                index--;
            }
        }
    }
}
