package Views;

import java.awt.Color;

import javax.swing.JFrame;

import Controllers.KeyChecker;

public class MainFrame extends JFrame {
    public MainFrame() {
        GamePanel gPanel = new GamePanel();
        gPanel.setLocation(0, 0);
        gPanel.setSize(this.getSize());
        gPanel.setBackground(Color.lightGray);
        gPanel.setVisible(true);
        this.add(gPanel);
        addKeyListener(new KeyChecker(gPanel));
    }

}
