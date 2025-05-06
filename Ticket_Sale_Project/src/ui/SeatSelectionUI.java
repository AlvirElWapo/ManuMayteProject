package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SeatSelectionUI extends JFrame {
    private final int ROWS = 10;
    private final int COLS = 15;
    private JButton[][] seats = new JButton[ROWS][COLS];

    public SeatSelectionUI() {
        setTitle("Stadium Seat Selection");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();

        for (int row = 0; row < ROWS; row++) {
            int padding = (int) (Math.abs(Math.sin((double) row / ROWS * Math.PI)) * 5); // makes it curve
            for (int col = 0; col < COLS; col++) {
                JButton seat = new JButton();
                seat.setPreferredSize(new Dimension(30, 30));
                seat.setBackground(Color.LIGHT_GRAY);
                seat.setMargin(new Insets(0, 0, 0, 0));
                seat.setFocusPainted(false);

                int rowIndex = row;
                int colIndex = col;

                seat.addActionListener(e -> toggleSeat(seat));

                seats[row][col] = seat;

                gbc.gridx = col + padding;
                gbc.gridy = row;
                gbc.insets = new Insets(2, 2, 2, 2);
                panel.add(seat, gbc);
            }
        }

        add(panel);
        setVisible(true);
    }

    private void toggleSeat(JButton seat) {
        if (seat.getBackground().equals(Color.LIGHT_GRAY)) {
            seat.setBackground(Color.GREEN);
        } else if (seat.getBackground().equals(Color.GREEN)) {
            seat.setBackground(Color.LIGHT_GRAY);
        }
    }
}
