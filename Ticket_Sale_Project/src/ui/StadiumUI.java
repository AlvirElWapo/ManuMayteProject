package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StadiumUI extends JFrame {

    public StadiumUI() {
        setTitle("Stadium Seat Selector");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createStand("North Stand", 6, 12, Color.BLUE), BorderLayout.NORTH);
        add(createStand("South Stand", 6, 12, Color.RED), BorderLayout.SOUTH);
        add(createStand("East Stand", 10, 6, Color.RED), BorderLayout.EAST);
        add(createStand("West Stand", 10, 6, Color.RED), BorderLayout.WEST);

        add(new JLabel("Pick your seats!", SwingConstants.CENTER), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createStand(String name, int rows, int cols, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel(name, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setBackground(color.darker());
        label.setOpaque(true);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(rows, cols, 5, 5));
        grid.setBackground(color.darker());

        for (int i = 0; i < rows * cols; i++) {
            JButton seat = new JButton();
            seat.setBackground(color);
            seat.setPreferredSize(new Dimension(40, 40));
            seat.setToolTipText(name + " Seat " + (i + 1));
            seat.addActionListener(e -> toggleSeat(seat));
            grid.add(seat);
        }

        panel.add(grid, BorderLayout.CENTER);
        return panel;
    }

    private void toggleSeat(JButton seat) {
        if (seat.getBackground().equals(Color.GREEN)) {
            seat.setBackground(seat.getActionCommand() != null && seat.getActionCommand().equals("away") ? new Color(0, 120, 120) : Color.LIGHT_GRAY);
        } else {
            seat.setBackground(Color.GREEN);
        }
    }
}
