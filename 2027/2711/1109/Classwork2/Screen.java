import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JPanel;

// player1 (red) moves with the arrow keys. player2 (blue) moves with W, A, S, D.
// Each player is saved to its own file after it moves,
// and the saved positions are loaded when the screen starts.
public class Screen extends JPanel implements KeyListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int SIZE = 40;   // each player is a square
    public static final int STEP = 10;   // pixels moved per key press
    private static final String FILE1 = "player1.dat";
    private static final String FILE2 = "player2.dat";

    private Player player1;
    private Player player2;

    public Screen() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        // load the last saved positions. If there is no saved position, start in the center.
        player1 = loadPlayer(FILE1);
        if (player1 == null) {
            player1 = new Player((WIDTH - SIZE) / 2, (HEIGHT - SIZE) / 2);
        }
        player2 = loadPlayer(FILE2);
        if (player2 == null) {
            player2 = new Player((WIDTH - SIZE) / 2, (HEIGHT - SIZE) / 2);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(player1.getX(), player1.getY(), SIZE, SIZE);
        g.setColor(Color.BLUE);
        g.fillRect(player2.getX(), player2.getY(), SIZE, SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Red: arrow keys    Blue: W A S D", 20, 30);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            tryMove(player1, 0, -STEP, FILE1);
        } else if (code == KeyEvent.VK_DOWN) {
            tryMove(player1, 0, STEP, FILE1);
        } else if (code == KeyEvent.VK_LEFT) {
            tryMove(player1, -STEP, 0, FILE1);
        } else if (code == KeyEvent.VK_RIGHT) {
            tryMove(player1, STEP, 0, FILE1);
        } else if (code == KeyEvent.VK_W) {
            tryMove(player2, 0, -STEP, FILE2);
        } else if (code == KeyEvent.VK_S) {
            tryMove(player2, 0, STEP, FILE2);
        } else if (code == KeyEvent.VK_A) {
            tryMove(player2, -STEP, 0, FILE2);
        } else if (code == KeyEvent.VK_D) {
            tryMove(player2, STEP, 0, FILE2);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Moves the player only if it stays on the screen. Then the player is saved.
    private void tryMove(Player p, int dx, int dy, String fileName) {
        int newX = p.getX() + dx;
        int newY = p.getY() + dy;
        if (newX < 0 || newX + SIZE > WIDTH || newY < 0 || newY + SIZE > HEIGHT) {
            return;
        }
        p.move(dx, dy);
        savePlayer(p, fileName);
    }

    private void savePlayer(Player p, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(p);
            out.close();
        } catch (IOException ex) {
            System.out.println("Could not save " + fileName);
        }
    }

    // Returns the saved player, or null if there is no saved file yet.
    private Player loadPlayer(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            Player p = (Player) in.readObject();
            in.close();
            return p;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Could not load " + fileName);
            return null;
        }
    }
}
