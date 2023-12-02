package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import views.GamePanel;

public class KeyChecker extends KeyAdapter {
    GamePanel panel;

    public KeyChecker(GamePanel panel) {
        this.panel = panel;
    }

    public void keyPressed(KeyEvent e) {
        panel.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        panel.keyReleased(e);
    }
}