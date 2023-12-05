package controller;

import java.awt.event.*;

import entity.Player;

public class MouseInput implements MouseListener, MouseMotionListener {
    private Player player;
    private boolean isMouseInside;
    private int lastMouseX;
    private int lastMouseY;

    public MouseInput(Player player) {
        this.player = player;

        int playerX = player.getX() + player.getWidth() / 2;
        int playerY = player.getY() + player.getHeight() / 2;

        int initialHandY = playerY + player.getHeight() / 4;
        player.getHand().updatePosition(playerX - player.getHand().getWidth() / 2, initialHandY);

        isMouseInside = true;
        lastMouseX = 0;
        lastMouseY = 0;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isMouseInside) {
            updateHandPosition(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isMouseInside) {
            updateHandPosition(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isMouseInside) {
            updateHandPosition(e.getX(), e.getY());
            player.getHeldWeapon().attack();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isMouseInside) {
            updateHandPosition(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isMouseInside) {
            updateHandPosition(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isMouseInside = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isMouseInside = false;
        int playerX = player.getX() + player.getWidth() / 2;
        int playerY = player.getY() + player.getHeight() / 2;
        int initialHandY = playerY + player.getHeight() / 4;
        player.getHand().updatePosition(playerX - player.getHand().getWidth() / 2, initialHandY);
    }

    private void updateHandPosition(int mouseX, int mouseY) {
        int playerX = player.getX() + player.getWidth() / 2;
        int playerY = player.getY() + player.getHeight() / 2;

        int dx = mouseX - playerX;
        int dy = mouseY - playerY;

        double angleRadians = Math.atan2(dy, dx);

        int maxHandDistanceFromMouse = 30;
        double distanceToPlayer = Math.sqrt(dx * dx + dy * dy);
        int newHandDistanceFromCenter = (int) Math.min(distanceToPlayer, maxHandDistanceFromMouse);

        int handX = (int) (playerX + newHandDistanceFromCenter * Math.cos(angleRadians))
                - player.getHand().getWidth() / 2;
        int handY = (int) (playerY + newHandDistanceFromCenter * Math.sin(angleRadians))
                - player.getHand().getHeight() / 2;

        int maxX = playerX + player.getWidth() / 2 - player.getHand().getWidth() / 2;
        int maxY = playerY + player.getHeight() / 2 - player.getHand().getHeight() / 2;

        handX = Math.min(Math.max(handX, playerX - maxX), playerX + maxX);
        handY = Math.min(Math.max(handY, playerY - maxY), playerY + maxY);

        lastMouseX = mouseX;
        lastMouseY = mouseY;

        player.getHand().updatePosition(handX, handY);
    }

    public int getLastMouseX() {
        return lastMouseX;
    }

    public int getLastMouseY() {
        return lastMouseY;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isMouseInside() {
        return isMouseInside;
    }
}
