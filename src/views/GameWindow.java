package views;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private MainMenu mainMenu;
    private GamePanel gamePanel;
    private JoinRoomPanel joinRoomPanel;
    private RoomPanel roomPanel;
    private boolean isDemo = false;
    String roomCode;

    public GameWindow() {

        setLayout(new CardLayout());

        showMainMenu(); // Tampilkan MainMenu pertama kali saat aplikasi dimulai
    }

    public void showMainMenu() {
        mainMenu = new MainMenu(this);
        add(mainMenu, "MainMenu");
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "MainMenu");
    }

    public void showGamePanel(boolean isDemo) {
        gamePanel = new GamePanel(isDemo);
        add(gamePanel, "GamePanel");
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "GamePanel");
        gamePanel.requestFocusInWindow(); // Fokus pada GamePanel untuk input dari keyboard
    }

    public void showRoomPanel(String roomCode) {
        roomPanel = new RoomPanel(roomCode, this);
        add(roomPanel, "RoomPanel");
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "RoomPanel");
    }

    public void showJoinRoomPanel() {
        joinRoomPanel = new JoinRoomPanel(this);
        add(joinRoomPanel, "joinRoomPanel");
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "joinRoomPanel");
    }

    public String getRoomCode() {
        return roomCode;
    }
}
