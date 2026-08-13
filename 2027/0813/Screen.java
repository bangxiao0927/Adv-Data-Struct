import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Screen extends JPanel implements ActionListener {
    private JTextField textField;

    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton equalsButton;
    private JButton clearButton;

    private double firstNumber;
    private String operation;

    public Screen() {
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(175, 70, 450, 45);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField);

        clearButton = makeButton("AC", 175, 140, 65);
        addButton = makeButton("+", 250, 140, 55);
        subtractButton = makeButton("-", 315, 140, 55);
        multiplyButton = makeButton("*", 380, 140, 55);
        divideButton = makeButton("/", 445, 140, 55);
        equalsButton = makeButton("=", 510, 140, 115);

        operation = "";
    }

    // making button method
    private JButton makeButton(String label, int x, int y, int width) {
        JButton button = new JButton(label);
        button.setBounds(x, y, width, 40);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(this);
        add(button);
        return button;
    }

    // preferred size of the window
    public Dimension getPreferredSize() {
        return new Dimension(800, 250);
    }

    // painting the components 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    // getting the action events of pressing buttons and react
    public void actionPerformed(ActionEvent event) {
        JButton buttonPressed = (JButton) event.getSource();

        if (buttonPressed == clearButton) {
            clearCalculator();
        } else if (buttonPressed == equalsButton) {
            findAnswer();
        } else if (buttonPressed == addButton) {
            saveFirstNumber("+");
        } else if (buttonPressed == subtractButton) {
            saveFirstNumber("-");
        } else if (buttonPressed == multiplyButton) {
            saveFirstNumber("*");
        } else if (buttonPressed == divideButton) {
            saveFirstNumber("/");
        }
    }

    // saves the first number when an operation button is pressed.
    private void saveFirstNumber(String newOperation) {
        try {
            firstNumber = Double.parseDouble(textField.getText());
            operation = newOperation;
            textField.setText("");
            textField.requestFocusInWindow();
        } catch (NumberFormatException exception) {
            showError();
        }
    }

    // uses the saved number and the number currently in the text field.
    private void findAnswer() {
        if (operation.equals("")) {
            return;
        }

        try {
            // changing the input into the double variable
            double secondNumber = Double.parseDouble(textField.getText());
            double answer;

            // the main operating logics of + , - , * and /
            if (operation.equals("+")) {
                answer = firstNumber + secondNumber;
            } else if (operation.equals("-")) {
                answer = firstNumber - secondNumber;
            } else if (operation.equals("*")) {
                answer = firstNumber * secondNumber;
            } else {
                if (secondNumber == 0) {
                    showError();
                    return;
                }
                answer = firstNumber / secondNumber;
            }

            textField.setText(formatAnswer(answer));
            firstNumber = answer;
            operation = "";
        } catch (NumberFormatException exception) {
            showError();
        }
    }

    // removes ".0" when the answer is a whole number.
    private String formatAnswer(double answer) {
        if (answer == (long) answer) {
            return Long.toString((long) answer);
        }
        return Double.toString(answer);
    }

    // AC logics
    private void clearCalculator() {
        textField.setText("");
        firstNumber = 0;
        operation = "";
        textField.requestFocusInWindow();
    }

    // ERROR print
    private void showError() {
        textField.setText("Error");
        firstNumber = 0;
        operation = "";
    }
}
