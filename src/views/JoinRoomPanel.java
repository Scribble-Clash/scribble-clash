package views;

import api.Room;
import data.Multiplayer;

import java.awt.*;
import javax.swing.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class JoinRoomPanel extends JPanel {
    public JoinRoomPanel(GameWindow gameWindow) {
        setLayout(null);




        JButton playButton = createStyledButton("Join Room");
        playButton.setBounds(830, 500, 240, 80);
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(Color.BLACK);
        playButton.setFont(new Font("Reach Story", Font.BOLD, 50));
        JButton hostbutton = createStyledButton("Create Room");
        hostbutton.setBounds(830, 600, 240, 80);
        hostbutton.setForeground(Color.WHITE);
        hostbutton.setBackground(Color.BLACK);
        hostbutton.setFont(new Font("Reach Story", Font.BOLD, 50));

        // playButton.setEnabled(false);

        JLabel roomCodeLabel = new JLabel("Room Code:");
        roomCodeLabel.setHorizontalTextPosition(JLabel.CENTER);
        roomCodeLabel.setFont(new Font("Reach Story", Font.BOLD, 100));
        roomCodeLabel.setBounds(780,250, 600, 80);
        JTextField roomCodeField = new JTextField(10);
        roomCodeField.setFont(new Font("Reach Story", Font.BOLD, 80));
        roomCodeField.setHorizontalAlignment(JTextField.CENTER);
        roomCodeField.setOpaque(false);

        roomCodeField.setBorder(new MatteBorder(0,0,2,0,Color.BLACK));
        roomCodeField.setBounds(650, 350, 600, 80);

        DocumentFilter filter = new UppercaseDocumentFilter();

        ((AbstractDocument) roomCodeField.getDocument()).setDocumentFilter(filter);

        roomCodeField.addActionListener(e -> {
            String roomCode = roomCodeField.getText();
            playButton.setEnabled(!roomCode.isEmpty());
        });

        playButton.addActionListener(e -> {
            String roomCode = roomCodeField.getText();
            if (!roomCode.isEmpty()) {
                new Thread(() -> {
                    Room room = new Room(roomCode);
                }).start();
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                Room room = new Room(roomCode);
                System.out.println(room.getPlayerList());
                if (!room.isEmpty()) {
                    Multiplayer.numberOfPlayers = room.getPlayerList().size();
                    for (int i = 2; i <= 4; i++) {
                        if (!room.isPlayerExist("player" + i)) {
                            Multiplayer.roomCode = roomCode;
                            room.addPlayer("player" + i, 0);
                            Multiplayer.id = "player" + i;
                            Multiplayer.status = 0;
                            gameWindow.showRoomPanel(0);
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Room Not Found");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a Room Code!");
            }
        });
        hostbutton.addActionListener(e -> {
            // Generate a random alphanumeric string of length 6
            String roomCode = generateRoomCode(6);
            roomCodeField.setText(roomCode);
            gameWindow.showRoomPanel(1);
        });

        add(roomCodeLabel);
        add(roomCodeField);
        add(playButton);
        add(hostbutton);
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
                button.setBackground(Color.BLACK);
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
    private class UppercaseDocumentFilter extends DocumentFilter {
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
                                 AttributeSet attr) throws BadLocationException {

            fb.insertString(offset, text.toUpperCase(), attr);
        }

        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
                            AttributeSet attrs) throws BadLocationException {

            fb.replace(offset, length, text.toUpperCase(), attrs);
        }
    }
}

