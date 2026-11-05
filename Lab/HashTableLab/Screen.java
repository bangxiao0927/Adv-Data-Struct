import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Screen extends JPanel implements ActionListener {

    public Screen() {
        this.setLayout(null);
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
