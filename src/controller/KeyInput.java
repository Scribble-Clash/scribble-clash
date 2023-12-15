package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entity.Player;

public class KeyInput extends KeyAdapter {
    private Player player;
    private boolean enabled = true;

    public KeyInput(Player player) {
        this.player = player;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setKeyUp(true);
                break;
            case KeyEvent.VK_A:
                player.setKeyLeft(true);
                break;
            case KeyEvent.VK_S:
                player.setKeyDown(true);
                break;
            case KeyEvent.VK_D:
                player.setKeyRight(true);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setKeyUp(false);
                break;
            case KeyEvent.VK_A:
                player.setKeyLeft(false);
                break;
            case KeyEvent.VK_S:
                player.setKeyDown(false);
                break;
            case KeyEvent.VK_D:
                player.setKeyRight(false);
                break;
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
