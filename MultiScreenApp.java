import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MultiScreenApp {
    public static void main(String[] args) {
        UIManager.put("Panel.background", new Color(30, 30, 30));
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("Button.background", new Color(50, 50, 50));
        UIManager.put("Button.foreground", Color.WHITE);
        JFrame frame = new JFrame("Multi-Screen App");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Card layout and container panel
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // ---------- Screen 1 ----------
        JPanel screen1 = new JPanel();
        screen1.setLayout(null);
        JButton goToScreen2 = new JButton("Go to Screen 2");
        goToScreen2.setBounds(120, 80, 160, 30);
        screen1.add(goToScreen2);

        // ---------- Screen 2 ----------
        JPanel screen2 = new JPanel();
        screen2.setLayout(null);
        JLabel label = new JLabel("Welcome to Screen 2");
        label.setBounds(120, 50, 200, 30);
        JButton backButton = new JButton("Back");
        backButton.setBounds(120, 100, 160, 30);
        screen2.add(label);
        screen2.add(backButton);

        // Add screens to card panel
        cardPanel.add(screen1, "screen1");
        cardPanel.add(screen2, "screen2");

        // Button actions
        goToScreen2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "screen2");
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "screen1");
            }
        });

        // Add to frame and show
        frame.add(cardPanel);
        frame.setVisible(true);
    }
}
