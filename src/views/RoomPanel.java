package views;

import api.Room;
import controller.Loader;
import data.Multiplayer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RoomPanel extends JPanel {
    private JTextField roomCodeField;

    private JPanel playerListPanel;
    private JLabel roomLabel;
    private ArrayList<String> playerList = new ArrayList<>();

    public RoomPanel(int mode, GameWindow gameWindow) {
        setLayout(null);
        // Create the room code field
        roomCodeField = new JTextField();
        roomCodeField.setEditable(false);


        // Create the user list

        Room room;

        if (mode == 1) {
            String roomCode = generateRoomCode(6);
            room = new Room(roomCode, "player1", 1);
            roomCodeField.setText("Room Code: " + roomCode);
            room.listenStart(gameWindow);
            Multiplayer.roomCode = roomCode;
            Multiplayer.id = "player1";
            Multiplayer.status = 1;
            JButton hostButton = new JButton("Start Game");
            hostButton.setFont(new Font("Reach Story", Font.BOLD, 50));
            hostButton.setBorder(null);
            hostButton.setBackground(Color.BLACK);
            hostButton.setForeground(Color.WHITE);
            hostButton.setBounds(1630, 960, 240, 80);


            hostButton.addActionListener(e -> {
                room.getPlayerList();
                room.setStart(true);
            });
            hostButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    hostButton.setBackground(Color.LIGHT_GRAY);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    hostButton.setBackground(Color.BLACK);
                }
            });
            add(hostButton);
        } else {
            roomCodeField.setText("Room Code: " + Multiplayer.roomCode);
            room = new Room(Multiplayer.roomCode);
            room.listenStart(gameWindow);
        }
        playerList.addAll(room.getPlayerList().keySet());
        // Create the host button

        roomCodeField.setBorder(null);
        roomCodeField.setForeground(Color.BLACK);
        roomCodeField.setHorizontalAlignment(JTextField.CENTER);
        roomCodeField.setBounds(600, 80, 700, 150);
        roomCodeField.setFont(new Font("Reach Story", Font.BOLD, 90));
        add(roomCodeField);

        playerListPanel = new JPanel();
        playerListPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        playerListPanel.setBounds(550, 250, 750, 600);
        add(playerListPanel);

        BufferedImage sprites = (BufferedImage) new Loader().mainimage();
        Image img = sprites.getSubimage(576, 448, 64, 64);
        for (int i = 0; i < playerList.size(); i++) {
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            playerPanel.setBackground(null);
            playerPanel.setPreferredSize(new Dimension(600, 100));

            JLabel playerLabel = new JLabel(playerList.get(i));
            playerLabel.setFont(new Font("Reach Story", Font.BOLD, 70));

            JLabel playerSprite = new JLabel(new ImageIcon(img.getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
            playerPanel.add(playerSprite);
            playerPanel.add(playerLabel);
            playerListPanel.add(playerPanel);
        }


    }

    // Method to generate a random alphanumeric string
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