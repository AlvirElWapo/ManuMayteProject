package ui;

import javax.swing.*;
import java.awt.event.*;
// import logic.Calculator;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Simple App");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton calcButton = new JButton("Calculate 2 + 3");
        calcButton.setBounds(70, 70, 160, 30);

        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = 5;
                JOptionPane.showMessageDialog(null, "Result: " + result);
            }
        });

        add(calcButton);
        setVisible(true);
    }
}
