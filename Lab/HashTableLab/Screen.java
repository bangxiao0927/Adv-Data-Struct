import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Screen extends JPanel implements ActionListener {
    private JTextArea info;
    private JTextField state, abbreviation, population, capital, image;
    private JButton add, remove;
    
    public Screen() {
        this.setLayout(null);

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

        add = new JButton("Add");
        add.setBounds(50, 300, 150, 30);
        add.addActionListener(this);
        this.add(add);
        remove = new JButton("Remove");
        remove.setBounds(50, 400, 150, 30);
        remove.addActionListener(this);
        this.add(remove);
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
        //
    }
}
