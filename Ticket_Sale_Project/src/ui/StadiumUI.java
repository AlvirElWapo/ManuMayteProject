package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class StadiumUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private List<JButton> selectedSeats = new ArrayList<>();

    public StadiumUI() {
        setTitle("Stadium Seat Selector");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        mainPanel.add(createSectionSelectionScreen(), "sectionSelection");
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private JPanel createSectionSelectionScreen() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Select a Stand", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panel.add(title, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gridPanel.add(createStandDisplay("North Stand", Color.BLUE, "North"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gridPanel.add(createStandDisplay("South Stand", Color.RED, "South"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gridPanel.add(createStandDisplay("East Stand", Color.ORANGE, "East"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gridPanel.add(createStandDisplay("West Stand", Color.BLACK, "West"), gbc);

        panel.add(gridPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createStandDisplay(String standName, Color color, String key) {
        JPanel standPanel = new JPanel(new BorderLayout());
        standPanel.setPreferredSize(new Dimension(400, 200));
        JLabel label = new JLabel(standName, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(color.darker());
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel seatSelection = createSeatSelectionScreen(standName, color, key);
                mainPanel.add(seatSelection, "seatSelection");
                cardLayout.show(mainPanel, "seatSelection");
            }
        });

        standPanel.add(label, BorderLayout.CENTER);
        standPanel.add(selectButton, BorderLayout.SOUTH);
        return standPanel;
    }

    private JPanel createSeatSelectionScreen(String standName, Color color, String direction) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(standName + " - Select Your Seats (Max 5)", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panel.add(title, BorderLayout.NORTH);

        int rows = (direction.equals("North") || direction.equals("South")) ? 6 : 10;
        int cols = (direction.equals("North") || direction.equals("South")) ? 12 : 6;

        JPanel seatGrid = new JPanel(new GridLayout(rows + 1, cols + 1, 5, 5));


        for (int row = 0; row <= rows; row++) {
            for (int col = 0; col <= cols; col++) {
                if (row == 0 && col == 0) {
                    seatGrid.add(new JLabel("")); // top-left corner empty
                } else if (row == 0) {
                    JLabel colLabel = new JLabel(String.valueOf(col), SwingConstants.CENTER);
                    colLabel.setFont(new Font("Arial", Font.BOLD, 12));
                    seatGrid.add(colLabel);
                } else if (col == 0) {
                    JLabel rowLabel = new JLabel(String.valueOf((char) ('A' + row - 1)), SwingConstants.CENTER);
                    rowLabel.setFont(new Font("Arial", Font.BOLD, 12));
                    seatGrid.add(rowLabel);
                } else {
                    String seatNumber = standName + "-" + (char) ('A' + row - 1) + col;
                    String tier = getTier(row - 1, rows);
                    JButton seat = new JButton(getTierShort(tier));
                    seat.setPreferredSize(new Dimension(80, 80));
                    seat.setBackground(color);
                    seat.setToolTipText(seatNumber + " (" + tier + ")");
                    seat.putClientProperty("tier", tier);
                    seat.putClientProperty("seatNumber", seatNumber);
                    seat.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            toggleSeat(seat, color);
                        }
                    });
                    seatGrid.add(seat);
                }
            }
        }





        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(seatGrid);
        panel.add(centerWrapper, BorderLayout.CENTER);

        JLabel fieldLabel = new JLabel(getFieldDirection(direction), SwingConstants.CENTER);
        fieldLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fieldLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(fieldLabel, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton backButton = new JButton("\u2190 Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setMargin(new Insets(5, 10, 5, 10));
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSeats.clear();
                cardLayout.show(mainPanel, "sectionSelection");
            }
        });
        bottomPanel.add(backButton);

        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.PLAIN, 12));
        continueButton.setMargin(new Insets(5, 10, 5, 10));
        continueButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showOrderReview();
            }
        });
        bottomPanel.add(continueButton);

        JButton resetButton = new JButton("Reset Selection");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 12));
        resetButton.setMargin(new Insets(5, 10, 5, 10));
        resetButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton seat : selectedSeats) {
                    seat.setBackground(color);
                }
                selectedSeats.clear();
            }
        });
        bottomPanel.add(resetButton);

        panel.add(bottomPanel, BorderLayout.PAGE_END);

        return panel;
    }

    private void toggleSeat(JButton seat, Color color) {
        if (selectedSeats.contains(seat)) {
            seat.setBackground(color);
            selectedSeats.remove(seat);
        } else {
            if (selectedSeats.size() < 5) {
                seat.setBackground(Color.YELLOW);
                selectedSeats.add(seat);
            } else {
                JOptionPane.showMessageDialog(this, "You can only select up to 5 seats.", "Limit Reached", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private String getTier(int row, int totalRows) {
        if (row < totalRows / 3) return "VIP";
        else if (row < 2 * totalRows / 3) return "Preferential";
        else return "General";
    }

    private String getTierShort(String tier) {
        if ("VIP".equals(tier)) return "V";
        else if ("Preferential".equals(tier)) return "P";
        else return "G";
    }

    private String getFieldDirection(String direction) {
        if ("North".equals(direction)) return "\u2193 Field this way";
        else if ("South".equals(direction)) return "\u2191 Field this way";
        else if ("East".equals(direction)) return "\u2190 Field this way";
        else return "\u2192 Field this way";
    }

    private void showOrderReview() {
        if (selectedSeats.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one seat to continue.", "No Seats Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder message = new StringBuilder("You selected the following seats:\n");
        for (JButton seat : selectedSeats) {
            message.append(seat.getToolTipText()).append("\n");
        }

        JOptionPane.showMessageDialog(this, message.toString(), "Review Order", JOptionPane.INFORMATION_MESSAGE);
    }

}
