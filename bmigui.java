/*
@Author: Sebastian Talarowski
@title: BMI calculator + Gui
@source: na podstawie ksiazki pt: "Beginning Java Programming: The Object-Oriented Approach", stackoverflow.com
@date: 13.03.2019r.
 */

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class bmigui extends JFrame {
    private final JTextField txtMass = makePrettyTextField();
    private final JTextField txtHeight = makePrettyTextField();
    private final JButton btnCalc = makePrettyButton("Calculate BMI");
    public double result;


    public bmigui() throws FileNotFoundException{
        super("BMI Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BMI Calculator");

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        txtMass.setPreferredSize((new Dimension(200, 30)));
        txtHeight.setPreferredSize(new Dimension(200, 30));
        txtHeight.setMaximumSize(txtHeight.getPreferredSize());
        txtMass.setMaximumSize(txtMass.getPreferredSize());

        getContentPane().setBackground(new Color(232, 240, 255));

        getContentPane().add(makePrettyLabel("Your mass (kg):"));
        getContentPane().add(Box.createRigidArea(new Dimension(5, 5)));
        getContentPane().add(txtMass);
        getContentPane().add(Box.createRigidArea((new Dimension(5, 5))));

        getContentPane().add(Box.createVerticalGlue());

        getContentPane().add(makePrettyLabel("Your height (cm):"));
        getContentPane().add(Box.createRigidArea(new Dimension(5, 5)));
        getContentPane().add(txtHeight);
        getContentPane().add(Box.createRigidArea((new Dimension(5, 5))));

        getContentPane().add(Box.createVerticalGlue());
        getContentPane().add(btnCalc);
        getContentPane().add(Box.createRigidArea(new Dimension(5, 5)));

        btnCalc.addActionListener(arg0 -> {
            double mass;
            double height;
            try {
                mass = Double.parseDouble(txtMass.getText());
                height = Double.parseDouble(txtHeight.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a valid number for mass and height.",
                        "Input error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            result = calculateBMI(mass, height);
            JOptionPane.showMessageDialog(null,
                    "Your BMI is:" + (Math.round(result * 100.0) / 100.0),
                    "Your BMI result",
                    JOptionPane.PLAIN_MESSAGE);
            PrintWriter zapis = null;

            try {
                zapis = new PrintWriter("Your_BMI.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }

            zapis.println("Value_BMI: " + result);
            zapis.close();
        });
        List<Double> list = new ArrayList<>();
        list.add(result);

        pack();
        setVisible(true);
        setLocation(860,430); //Wysrodkowanie aplikacji pod rozdzielczosc 1920x1080 wzor: (wys/2)-(wys_apki/2) + (szer/2)-(szer_apki/2)

    }


    static double calculateBMI(double mass, double height){
        double v = mass / Math.pow(height / 100.0, 2.0);
        return v;
    }

    private JButton makePrettyButton(String title) {
        JButton button = new JButton(title);
        button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setBackground(Color.white);
        button.setForeground(new Color(52, 124, 255));
        return button;
    }

    private JTextField makePrettyTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
        field.setHorizontalAlignment((JTextField.RIGHT));
        field.setBorder(BorderFactory.createLoweredBevelBorder());
        return field;

    }

    private JLabel makePrettyLabel(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        label.setForeground(new Color(53, 124, 255));
        return label;


    }



    public static void main(String[] args) throws FileNotFoundException {

        SwingUtilities.invokeLater(() -> {
            try {
                new bmigui();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });
    }

}
