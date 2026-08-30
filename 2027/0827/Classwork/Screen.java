import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Screen extends JPanel implements ActionListener {
    private ArrayList<Pair<Student, Schedule>> students;

    private JTextField textField;
    private int selectedStudent;

    public Screen() {
        students = new ArrayList<Pair<Student, Schedule>>();

        addStudent("Alex", "Math", "English", "Computer Science");
        addStudent("Jordan", "Biology", "History", "Art");
        addStudent("Taylor", "Chemistry", "Spanish", "Physical Education");

        textField = new JTextField(15);
        textField.addActionListener(this);
        add(textField);

        selectedStudent = -1;
    }

    private void addStudent(String name, String firstClass, String secondClass,
                            String thirdClass) {
        Schedule schedule = new Schedule();
        schedule.addCourse(1, firstClass);
        schedule.addCourse(2, secondClass);
        schedule.addCourse(3, thirdClass);
        students.add(new Pair<Student, Schedule>(new Student(name), schedule));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String name = textField.getText();
        selectedStudent = -1;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getKey().toString().equalsIgnoreCase(name)) {
                selectedStudent = i;
            }
        }
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 500);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Type a student's name and press Enter", 50, 60);
        g.drawString("Students:", 50, 100);

        for (int i = 0; i < students.size(); i++) {
            g.drawString(students.get(i).getKey().toString(), 50, 130 + i * 30);
        }

        if (selectedStudent != -1) {
            String name = students.get(selectedStudent).getKey().toString();
            Schedule schedule = students.get(selectedStudent).getValue();
            g.drawString(name + "'s Schedule:", 300, 100);

            for (int i = 0; i < schedule.size(); i++) {
                g.drawString(schedule.getCourse(i).toString(),
                             300, 130 + i * 30);
            }
        }
    }
}
