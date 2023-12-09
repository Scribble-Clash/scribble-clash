package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import animation.Animated;
import entity.Weapon.Weapon;
import views.GamePanel;

public class Hand extends Entity {
    private int x, y;
    private int width, height;
    private Image handImage;
    private int orientation;
    private Weapon heldWeapon;
    private BufferedImage[] handfightanimation;
    private int totalFrames;
    private boolean isAttacking = false;
    private int attackdamage = 4;

    public Hand(int x, int y, Image img, GamePanel panel) {
        super(x, y);
        this.handImage = img;
        this.panel = panel;
        width = handImage.getWidth(null);
        height = handImage.getHeight(null);
        this.hitbox = new Rectangle(x, y, img.getWidth(this.getPanel()), img.getHeight(this.getPanel()));
        Animated animated = new Animated();
        this.handfightanimation = animated.handfightanimation();
    }

    public void draw(Graphics2D g2d) {
        if (heldWeapon != null) {
            heldWeapon.setPosition(x, y);
            heldWeapon.draw(g2d);
        }
        g2d.drawImage(handImage, x, y, width, height, panel);
    }

    public void updatePosition(int playerX, int playerY) {
        this.x = playerX;
        this.y = playerY;
    }

    public void checkCollision(DummyEnemy enemy) {
        Rectangle swordRect = new Rectangle(x, y, handImage.getWidth(null), handImage.getHeight(null));
        Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

        if (swordRect.intersects(enemyRect) && isAttacking) {
            boolean knockFromRight = panel.getPlayer().getX() > enemy.getX();
            enemy.takeDamage(attackdamage, knockFromRight);
            isAttacking = false;
        }
    }

    private void resetHitbox() {
        this.hitbox = new Rectangle(x, y, handImage.getWidth(null), handImage.getHeight(null));
    }

    private DummyEnemy getDummyEnemyReference() {
        return this.getPanel().getDummyEnemy();
    }

    public void attack() {
        totalFrames = handfightanimation.length;
        isAttacking = true;

        Thread animationThread = new Thread(() -> {
            for (int i = 1; i < totalFrames; i++) {
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handImage = handfightanimation[i];
                panel.repaint(); // Trigger panel repaint to show updated image

                checkCollision(getDummyEnemyReference());
            }
            handImage = handfightanimation[0]; // Reset to initial frame
            isAttacking = false;
            resetHitbox();
            panel.repaint(); // Trigger panel repaint for final image
        });
        animationThread.start();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setOrientation(int angle) {
        this.orientation = angle;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setHeldWeapon(Weapon weapon) {
        this.heldWeapon = weapon;
    }

    @Override
    public void set() {

    }
}
