package controller;

import java.awt.event.*;

import entity.Player;
import entity.Weapon.Sword;

public class MouseInput implements MouseListener, MouseMotionListener {
    private Player player;
    private boolean isMouseInside;
    private int lastMouseX;
    private int lastMouseY;
    private long pressTime = 0;
    private boolean isMousePressed = false;

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

    // setter and getter
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

    // Overide Method
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

    public void mouseClicked(MouseEvent e) {
        if (isMouseInside) {
            if (player.getHeldWeapon() == null) {
                player.getHand().attack();
                updateHandPosition(e.getX(), e.getY());
            } else {
                updateHandPosition(e.getX(), e.getY());
                player.getHeldWeapon().attack();
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        isMousePressed = true;
        pressTime = System.currentTimeMillis();
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                if (player.getHeldWeapon() == null) {
                    if (isMousePressed && System.currentTimeMillis() - pressTime >= 1500) {
                        System.out.println("Charge sudah 1.5 detik");
                        player.getHand().charge1();

                        Thread.sleep(1500);
                        if (isMousePressed) {
                            System.out.println("Charge sudah 3 detik");
                            player.getHand().charge2();
                        }
                    }
                } else if (player.getHeldWeapon() instanceof Sword) {
                    Sword sword = (Sword) player.getHeldWeapon();
                    if (isMousePressed && System.currentTimeMillis() - pressTime >= 1500) {
                        System.out.println("Charge sudah 1.5 detik");
                        sword.charge1();

                        Thread.sleep(1500);
                        if (isMousePressed) {
                            System.out.println("Charge sudah 3 detik");
                            sword.charge2();
                        }
                    }
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

    }

    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
        if (isMouseInside) {
            long releaseTime = System.currentTimeMillis();
            long holdDuration = releaseTime - pressTime;
            updateHandPosition(e.getX(), e.getY());

            if (player.getHeldWeapon() == null) {
                if (holdDuration >= 3000) {
                    player.getHand().specialattack();
                    System.out.println("Akhir Hold 3 dtk");
                } else if (holdDuration >= 1500) {
                    player.getHand().specialattack();
                    System.out.println("Akhir Hold 1.5 dtk");
                } else {
                    System.out.println("Charge Gagal");
                }
            } else if (player.getHeldWeapon() instanceof Sword) {
                Sword sword = (Sword) player.getHeldWeapon();

                if (holdDuration >= 5000) {
                    sword.specialattack();
                    System.out.println("Sword Special Attack 5 dtk");

                } else if (holdDuration >= 2000) {
                    sword.specialattack();
                    System.out.println("Sword Special Attack 2 dtk");
                } else {
                    System.out.println("Sword Charge Gagal");
                }
            }
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

    // other method
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

}
