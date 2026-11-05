import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen extends JPanel implements ActionListener {
    private JTextArea info;
    private JTextField state, abbreviation, population, capital, image;
    private JButton get, add, remove, addImage;
    
    private MyHashMap<State, StateInfo> states;
    
    public Screen() {
        this.setLayout(null);

        states = new MyHashMap<>();

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

        State ca = new State("California", "ca");
        State ny = new State("New York", "ny");
        State tx = new State("Texas", "tx");
        states.put(ca, new StateInfo(ca, "39,400,000", "Sacramento"));
        states.put(ny, new StateInfo(ny, "20,000,000", "Albany"));
        states.put(tx, new StateInfo(tx, "31,700,000", "Austin"));

        /* VIEW ALL STATES */
    }
    
    @Override
  	public Dimension getPreferredSize() {
  		return new Dimension(880, 500);
  	}
    
    @Override
  	public void paintComponent(Graphics g) {
  		//
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            State s = new State(state.getText(), abbreviation.getText());
            states.put(s, new StateInfo(s, population.getText(), capital.getText()));
        } else if (e.getSource() == remove) {
            states.remove(new State("PLACEHOLDER", abbreviation.getText()));
        } else if (e.getSource() == get) {
            StateInfo si = states.get(new State("PLACEHOLDER", abbreviation.getText())).get(0);
            info.setText(si.toString());
            repaint();
        } else if (e.getSource() == addImage) {
            /* ADD IMAGE CODE */
        }
    }
}
