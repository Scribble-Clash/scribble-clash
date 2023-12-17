package views;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private MainMenu mainMenu;
    private GamePanel gamePanel;
    private JoinRoomPanel joinRoomPanel;
    private RoomPanel roomPanel;
    String roomCode;

    public GameWindow() {
        mainMenu = new MainMenu(this);
        gamePanel = new GamePanel();
        joinRoomPanel = new JoinRoomPanel(this);
        roomPanel = new RoomPanel(this.roomCode);

        setLayout(new CardLayout());
        add(mainMenu, "MainMenu");
        add(gamePanel, "GamePanel");
        add(joinRoomPanel, "joinRoomPanel");
        add(roomPanel, "RoomPanel");

        showMainMenu(); // Tampilkan MainMenu pertama kali saat aplikasi dimulai
    }

    public void showMainMenu() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "MainMenu");
    }

    public void showGamePanel() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "GamePanel");
        gamePanel.requestFocusInWindow(); // Fokus pada GamePanel untuk input dari keyboard
    }

    public void showRoomPanel(String roomCode) {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "RoomPanel");
    }

    public void showJoinRoomPanel() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "joinRoomPanel");
    }

    public String getRoomCode() {
        return roomCode;
    }
}
