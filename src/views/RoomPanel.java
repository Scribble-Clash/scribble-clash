package views;

import api.Room;
import data.Multiplayer;

import javax.swing.*;
import java.awt.*;

public class RoomPanel extends JPanel {
    private JTextField roomCodeField;
    private JList<String> userList;
    private DefaultListModel<String> userListModel;
    private JLabel roomLabel;

    public RoomPanel(String roomLabelString, GameWindow gameWindow) {
        setLayout(new BorderLayout());

        // Create the room label
        roomLabel = new JLabel(roomLabelString);
        add(roomLabel, BorderLayout.NORTH);

        // Create the room code field
        roomCodeField = new JTextField();
        roomCodeField.setEditable(false);
        add(roomCodeField, BorderLayout.CENTER);

        // Create the user list
        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        add(new JScrollPane(userList), BorderLayout.EAST);

        String roomCode = generateRoomCode(6);
        Room room = new Room(roomCode, "player1", 1);
        room.listenStart(gameWindow);
        Multiplayer.roomCode = roomCode;
        Multiplayer.id = "player1";
        Multiplayer.status = 1;
        roomCodeField.setText(roomCode);

        // Create the host button
        if (Multiplayer.status == 1) {
            JButton hostButton = new JButton("Play");
            hostButton.addActionListener(e -> {
                room.getPlayerList();
                room.setStart(true);
            });
            add(hostButton, BorderLayout.SOUTH);
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