package entity.Weapon;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import animation.Animated;
import entity.DummyEnemy;
import views.GamePanel;

public class Hand extends Weapon {
    private int x, y;
    private int width, height;
    private Image handImage;
    private int orientation;
    private Weapon heldWeapon;
    private BufferedImage[] handfightanimation;
    private int totalFrames;
    private boolean isAttacking = false;
    private boolean isSpecialAttack = false;
    private long startTime = 0;

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

    // setter and getter
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

    // override method
    public void draw(Graphics2D g2d) {
        if (heldWeapon != null) {
            heldWeapon.setPosition(x, y);
            heldWeapon.draw(g2d);
        }
        g2d.drawImage(handImage, x, y, width, height, panel);
    }

    @Override
    public void set() {

    }

    @Override
    public void setPosition(int x, int y) {
        updatePosition(x, y);
    }

    @Override
    public void hit() {
    }

    @Override
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
                panel.repaint();

                checkCollision(getDummyEnemyReference(), 1);
            }
            handImage = handfightanimation[0];
            isAttacking = false;
            resetHitbox();
            panel.repaint();
        });
        animationThread.start();
    }

    public void specialattack() {
        if (isSpecialAttack) {
            int newAttackDamage = 5; // Damage for special attack
            int forwardMovement = 10; // Adjust forward movement pixels

            int playerX = panel.getPlayer().getX(); // Get player X position
            int playerY = panel.getPlayer().getY(); // Get player Y position

            // Update position for special attack (move forward)
            int newX = playerX + forwardMovement; // Change X position
            int newY = playerY; // Change Y position accordingly

            // Update position
            getPanel().getPlayer().updatePosition(newX, newY);
            DummyEnemy enemy = getDummyEnemyReference();
            if (enemy != null) {
                checkCollision(enemy, newAttackDamage);
            }

            resetHitbox();
            panel.repaint();
        }
    }

    public void triggerSpecialAttack() {
        Thread specialAttackThread = new Thread(() -> {
            try {
                startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 5000) { // Change duration as needed
                    if (System.currentTimeMillis() - startTime >= 2000) { // Check for special attack
                        isSpecialAttack = true;
                        // Perform animation for special attack here
                        for (int i = 0; i < handfightanimation.length; i++) {
                            handImage = handfightanimation[i];
                            panel.repaint();
                            Thread.sleep(80);
                        }
                        specialattack(); // Trigger special attack after animation
                        break;
                    }
                    Thread.sleep(50);
                }
                isSpecialAttack = false; // Reset special attack
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        specialAttackThread.start();
    }

    public void updatePosition(int playerX, int playerY) {
        this.x = playerX;
        this.y = playerY;
    }

    public void checkCollision(DummyEnemy enemy, int damage) {
        Rectangle swordRect = new Rectangle(x, y, handImage.getWidth(null), handImage.getHeight(null));
        Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

        if (swordRect.intersects(enemyRect) && isAttacking) {
            boolean knockFromRight = panel.getPlayer().getX() > enemy.getX();
            enemy.takeDamage(damage, knockFromRight);
            isAttacking = false;
        }
    }

    private void resetHitbox() {
        this.hitbox = new Rectangle(x, y, handImage.getWidth(null), handImage.getHeight(null));
    }

    private DummyEnemy getDummyEnemyReference() {
        return this.getPanel().getDummyEnemy();
    }
}
