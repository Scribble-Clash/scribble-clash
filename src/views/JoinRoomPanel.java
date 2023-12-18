package views;

import api.Room;
import data.Multiplayer;

import java.awt.*;
import javax.swing.*;

import javax.swing.*;

public class JoinRoomPanel extends JPanel {
    public JoinRoomPanel(GameWindow gameWindow) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Scribble-Clash", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton playButton = createStyledButton("Join Room");
        JButton hostbutton = createStyledButton("Create Room");

        // playButton.setEnabled(false);

        JLabel roomCodeLabel = new JLabel("Room Code:");
        JTextField roomCodeField = new JTextField(10);
        roomCodeField.setPreferredSize(new Dimension(100, 30));
        roomCodeField.addActionListener(e -> {
            String roomCode = roomCodeField.getText();
            playButton.setEnabled(!roomCode.isEmpty());
        });

        playButton.addActionListener(e -> {
            String roomCode = roomCodeField.getText();
            if (!roomCode.isEmpty()) {
                Room room = new Room(roomCode);
                Multiplayer.numberOfPlayers = room.getPlayerList().size();
                for (int i = 2; i <= 4; i++) {
                    if (!room.isPlayerExist("player" + i)) {
                        Multiplayer.roomCode = roomCode;
                        room.addPlayer("player" + i, 0);
                        Multiplayer.id = "player" + i;
                        Multiplayer.status = 0;
                        break;
                    }
                }
                gameWindow.showRoomPanel(roomCode);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a Room Code!");
            }
        });
        hostbutton.addActionListener(e -> {
            // Generate a random alphanumeric string of length 6
            String roomCode = generateRoomCode(6);
            roomCodeField.setText(roomCode);
            gameWindow.showRoomPanel(roomCode);
        });
        buttonPanel.add(roomCodeLabel, gbc);
        gbc.gridy++;
        buttonPanel.add(roomCodeField, gbc);
        gbc.gridy++;
        buttonPanel.add(playButton, gbc);
        gbc.gridy++;
        buttonPanel.add(hostbutton, gbc);
        gbc.gridy++;

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(200, 30));

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

    private String generateRoomCode(int length) {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = (int) (alphaNumericString.length() * Math.random());
            sb.append(alphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}