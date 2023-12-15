package views;

import java.awt.Color;

import javax.swing.JFrame;

import controller.KeyInput;

public class GameWindow extends JFrame {
    public GameWindow() {
        GamePanel gPanel = new GamePanel();

        gPanel.setLocation(0, 0);
        gPanel.setSize(this.getSize());
        gPanel.setBackground(Color.lightGray);
        gPanel.setVisible(true);
        this.add(gPanel);
        KeyInput keyChecker = gPanel.getKeychecker();
        addKeyListener(keyChecker);

    }

}