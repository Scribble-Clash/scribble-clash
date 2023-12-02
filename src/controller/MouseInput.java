package controller;

import java.awt.event.*;

import entity.Player;

public class MouseInput implements MouseListener, MouseMotionListener {
    private Player player;

    public MouseInput(Player player) {
        this.player = player;

        int playerX = player.getX() + player.getWidth() / 2;
        int playerY = player.getY() + player.getHeight() / 2;

        // Atur posisi awal tangan di bawah pusat badan pemain
        int initialHandY = playerY + player.getHeight() / 4;
        player.getHand().updatePosition(playerX - player.getHand().getWidth() / 2, initialHandY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        int playerX = player.getX() + player.getWidth() / 2;
        int playerY = player.getY() + player.getHeight() / 2;

        int dx = mouseX - playerX;
        int dy = mouseY - playerY;

        double angleRadians = Math.atan2(dy, dx);

        // Batasi jarak tangan terhadap mouse
        int maxHandDistanceFromMouse = 30;

        // Hitung jarak mouse ke pemain
        double distanceToPlayer = Math.sqrt(dx * dx + dy * dy);

        // Hitung jarak tangan ke mouse
        int newHandDistanceFromCenter = (int) Math.min(distanceToPlayer, maxHandDistanceFromMouse);

        // Koordinat tangan relatif terhadap pusat pemain menggunakan trigonometri
        int handX = (int) (playerX + newHandDistanceFromCenter * Math.cos(angleRadians))
                - player.getHand().getWidth() / 2;
        int handY = (int) (playerY + newHandDistanceFromCenter * Math.sin(angleRadians))
                - player.getHand().getHeight() / 2;

        // Pastikan tangan tetap berada di sekitar player
        int maxX = playerX + player.getWidth() / 2 - player.getHand().getWidth() / 2;
        int maxY = playerY + player.getHeight() / 2 - player.getHand().getHeight() / 2;

        handX = Math.min(Math.max(handX, playerX - maxX), playerX + maxX);
        handY = Math.min(Math.max(handY, playerY - maxY), playerY + maxY);

        // Atur posisi tangan
        player.getHand().updatePosition(handX, handY);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Implementasi method lainnya sesuai kebutuhan
}
