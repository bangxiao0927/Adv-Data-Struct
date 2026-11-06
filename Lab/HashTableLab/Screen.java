import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;

// The window for the state hash map lab.
public class Screen extends JPanel implements ActionListener {
    private JTextArea info;
    private JTextField state, abbreviation, population, capital, image;
    private JButton get, add, remove, addImage, viewAll;
    
    private MyHashMap<State, StateInfo> states;
    private StateInfo current;
    
    public Screen() {
        this.setLayout(null);

        states = new MyHashMap<State, StateInfo>();

        info = new JTextArea();
        info.setBounds(500, 50, 330, 150);
        info.setEditable(false);
        info.setText("");
        this.add(info);

        state = new JTextField("State");
        state.setBounds(50, 50, 150, 30);
        this.add(state);
        abbreviation = new JTextField("Abbreviation");
        abbreviation.setBounds(50, 100, 150, 30);
        this.add(abbreviation);
        population = new JTextField("Population");
        population.setBounds(50, 150, 150, 30);
        this.add(population);
        capital = new JTextField("Capital");
        capital.setBounds(50, 200, 150, 30);
        this.add(capital);
        image = new JTextField("Image URL");
        image.setBounds(50, 250, 150, 30);
        this.add(image);

        get = new JButton("Get Info");
        get.setBounds(50, 300, 150, 30);
        get.addActionListener(this);
        this.add(get);
        add = new JButton("Add");
        add.setBounds(50, 350, 150, 30);
        add.addActionListener(this);
        this.add(add);
        remove = new JButton("Remove");
        remove.setBounds(50, 400, 150, 30);
        remove.addActionListener(this);
        this.add(remove);
        addImage = new JButton("Add Image");
        addImage.setBounds(50, 450, 150, 30);
        addImage.addActionListener(this);
        this.add(addImage);
        viewAll = new JButton("View All States");
        viewAll.setBounds(250, 50, 150, 30);
        viewAll.addActionListener(this);
        this.add(viewAll);

        State ca = new State("California", "ca");
        State ny = new State("New York", "ny");
        State tx = new State("Texas", "tx");
        states.put(ca, new StateInfo(ca, "39,400,000", "Sacramento"));
        states.put(ny, new StateInfo(ny, "20,000,000", "Albany"));
        states.put(tx, new StateInfo(tx, "31,700,000", "Austin"));

        addImageToState("ca", "https://www.planetware.com/img/gallery/most-iconic-landmarks-to-visit-in-california/intro-1780001427.webp");
        // Cary Bass, Golden Gate Bridge, CC BY-SA 3.0 (resized).
        // https://commons.wikimedia.org/wiki/File:Golden_Gate_Bridge_Yang_Ming_Line.jpg
        // https://creativecommons.org/licenses/by-sa/3.0/
        addImageToState("ca", "https://thumb.wikimedia.org/wikipedia/commons/thumb/a/ac/Golden_Gate_Bridge_Yang_Ming_Line.jpg/960px-Golden_Gate_Bridge_Yang_Ming_Line.jpg");
        addImageToState("ny", "https://arlohotels.com/wp-content/uploads/2025/08/statue-liberty-new-york-city-skyline-usa_268835-777.jpg");
        // Dllu, Empire State Building, CC BY-SA 4.0 (resized).
        // https://commons.wikimedia.org/wiki/File:View_of_Empire_State_Building_from_Rockefeller_Center_New_York_City_dllu.jpg
        // https://creativecommons.org/licenses/by-sa/4.0/
        addImageToState("ny", "https://thumb.wikimedia.org/wikipedia/commons/thumb/0/05/View_of_Empire_State_Building_from_Rockefeller_Center_New_York_City_dllu.jpg/960px-View_of_Empire_State_Building_from_Rockefeller_Center_New_York_City_dllu.jpg");
        // Comrade Goose, Austin Texas Skyline, CC BY 4.0 (resized).
        // https://commons.wikimedia.org/wiki/File:Austin_Texas_Skyline.jpg
        // https://creativecommons.org/licenses/by/4.0/
        addImageToState("tx", "https://thumb.wikimedia.org/wikipedia/commons/thumb/1/1a/Austin_Texas_Skyline.jpg/960px-Austin_Texas_Skyline.jpg");
        // Philipperez1, Alamo Mission, CC BY-SA 4.0 (resized).
        // https://commons.wikimedia.org/wiki/File:Alamo_Mission_in_San_Antonio_DSC03806.jpg
        // https://creativecommons.org/licenses/by-sa/4.0/
        addImageToState("tx", "https://thumb.wikimedia.org/wikipedia/commons/thumb/3/31/Alamo_Mission_in_San_Antonio_DSC03806.jpg/960px-Alamo_Mission_in_San_Antonio_DSC03806.jpg");

        showAll();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(880, 500);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (current == null) {
            return;
        }
        // Two pictures per row, under the text box.
        DLList<String> urls = current.images();
        for (int i = 0; i < urls.size(); i++) {
            Image pic = loadImage(urls.get(i));
            if (pic != null) {
                g.drawImage(pic, 500 + 160 * (i % 2), 220 + 130 * (i / 2), 150, 120, null);
            }
        }
    }

    private void showAll() {
        current = null;
        if (states.isEmpty()) {
            info.setText("No states yet.");
        } else {
            info.setText(states.keySet().toString());
        }
        repaint();
    }

    private void showState(String abbr) {
        StateInfo found = states.get(new State("PLACEHOLDER", abbr));
        if (found == null) {
            current = null;
            info.setText("State not found.");
        } else {
            current = found;
            info.setText(current.toString());
        }
        repaint();
    }

    private State keyFromField() {
        String abbr = abbreviation.getText().trim();
        if (abbr.length() != 2) {
            JOptionPane.showMessageDialog(this, "Abbreviation must be 2 letters.");
            return null;
        }
        return new State("PLACEHOLDER", abbr);
    }

    // ImageIO cannot read webp, so try the same picture as a jpg.
    private Image loadImage(String url) {
        try {
            URLConnection conn = URI        .create(url).toURL().openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            Image pic = ImageIO.read(conn.getInputStream());
            if (pic == null && url.endsWith(".webp")) {
                conn = URI.create(url.substring(0, url.length() - 4) + "jpg").toURL().openConnection();
                conn.setConnectTimeout(3000);
                conn.setReadTimeout(3000);
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                pic = ImageIO.read(conn.getInputStream());
            }
            return pic;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addImageToState(String abbr, String url) {
        if (abbr.trim().length() != 2) {
            return false;
        }
        StateInfo found = states.get(new State("PLACEHOLDER", abbr));
        if (found == null) {
            return false;
        }
        // The lab stores the picture URL here, and then loads it for display.
        if (loadImage(url) == null) {
            return false;
        }
        found.addImage(url);
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewAll) {
            showAll();
        } else if (e.getSource() == add) {
            String abbr = abbreviation.getText().trim();
            if (abbr.length() != 2) {
                JOptionPane.showMessageDialog(this, "Abbreviation must be 2 letters.");
                return;
            }
            State s = new State(state.getText(), abbr);
            states.put(s, new StateInfo(s, population.getText(), capital.getText()));
            showAll();
        } else if (e.getSource() == remove) {
            State key = keyFromField();
            if (key != null) {
                states.remove(key);
                showAll();
            }
        } else if (e.getSource() == get) {
            State key = keyFromField();
            if (key != null) {
                showState(key.getAbbreviation());
            }
        } else if (e.getSource() == addImage) {
            State key = keyFromField();
            if (key == null) {
                return;
            }
            if (addImageToState(key.getAbbreviation(), image.getText().trim())) {
                showState(key.getAbbreviation());
            } else {
                JOptionPane.showMessageDialog(this, "Could not add that picture.");
            }
        }
    }
}
