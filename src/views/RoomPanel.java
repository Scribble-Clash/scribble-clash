package views;

import javax.swing.*;
import java.awt.*;

public class RoomPanel extends JPanel {
    private JTextField roomCodeField;
    private JList<String> userList;
    private DefaultListModel<String> userListModel;
    private JLabel roomLabel;

    public RoomPanel(String roomLabelString) {
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

        // Create the host button
        JButton hostButton = new JButton("Host");
        hostButton.addActionListener(e -> {
            // Generate a random alphanumeric string of length 6
            String roomCode = generateRoomCode(6);
            roomCodeField.setText(roomCode);
        });

        add(hostButton, BorderLayout.SOUTH);
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