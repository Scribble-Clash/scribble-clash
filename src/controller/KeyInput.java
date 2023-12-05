package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entity.Player;

public class KeyInput extends KeyAdapter {
    private Player player;

    public KeyInput(Player player) {
        this.player = player;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.keyUp = true;
                break;
            case KeyEvent.VK_A:
                player.keyLeft = true;
                break;
            case KeyEvent.VK_S:
                player.keyDown = true;
                break;
            case KeyEvent.VK_D:
                player.keyRight = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.keyUp = false;
                break;
            case KeyEvent.VK_A:
                player.keyLeft = false;
                break;
            case KeyEvent.VK_S:
                player.keyDown = false;
                break;
            case KeyEvent.VK_D:
                player.keyRight = false;
                break;
        }
    }

}
