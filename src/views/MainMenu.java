package views;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JPanel {
    public MainMenu(GameWindow gameWindow) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Scribble-Clash", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton roomButton = createStyledButton("Room");
        JButton playDemoButton = createStyledButton("Play Demo");
        JButton exitButton = createStyledButton("Exit");

        roomButton.addActionListener(e -> gameWindow.showJoinRoomPanel());
        playDemoButton.addActionListener(e -> gameWindow.showGamePanel(true));
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(roomButton, gbc);
        gbc.gridy++;
        buttonPanel.add(playDemoButton, gbc);
        gbc.gridy++;
        buttonPanel.add(exitButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(100, 30));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });

        return button;
    }
}
