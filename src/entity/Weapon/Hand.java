package entity.Weapon;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import animation.Animated;
import animation.AnimationManager;
import entity.DummyEnemy;
import entity.Player;
import views.GamePanel;

public class Hand extends Weapon {
    private int x, y;
    private int width, height;
    private Image handImage;
    private int orientation;
    private Weapon heldWeapon;
    private BufferedImage[] animation;
    private int totalFrames;
    private boolean isAttacking = false;
    private int dashDistance = 20;
    Animated animated = new Animated();
    private AnimationManager animationManager;

    public Hand(int x, int y, Image img, GamePanel panel) {
        super(x, y);
        this.handImage = img;
        this.panel = panel;
        width = handImage.getWidth(null);
        height = handImage.getHeight(null);
        this.hitbox = new Rectangle(x, y, img.getWidth(this.getPanel()), img.getHeight(this.getPanel()));
        this.animationManager = new AnimationManager(panel);

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

    public int getDashDistance() {
        return dashDistance;
    }

    private DummyEnemy getDummyEnemyReference() {
        return this.getPanel().getDummyEnemy();
    }

    // override method
    public void draw(Graphics2D g2d) {
        if (heldWeapon != null) {
            heldWeapon.setPosition(x, y);
            heldWeapon.draw(g2d);
        }
        g2d.drawImage(handImage, x, y, width, height, panel);
        if (animationManager.isAnimating()) {
            g2d.drawImage(animationManager.getCurrentFrame(), x, y, width, height, panel);
        }
    }

    @Override
    public void set() {

    }

    @Override
    public void setPosition(int x, int y) {
        updatePosition(x, y);
    }

    @Override
    public void hit(DummyEnemy enemy, int damage) {
        Rectangle handRect = new Rectangle(x, y, handImage.getWidth(null), handImage.getHeight(null));
        Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

        if (handRect.intersects(enemyRect) && isAttacking) {
            boolean knockFromRight = panel.getPlayer().getX() > enemy.getX();
            enemy.takeDamage(damage, knockFromRight);
            isAttacking = false;
        }
    }

    @Override
    public void attack() {
        this.animation = animated.handfightanimation();
        totalFrames = animation.length;
        isAttacking = true;
        boolean isFacingLeft = panel.getPlayer().isFacingLeft();
        if (isFacingLeft) {
            for (int i = 1; i < totalFrames; i++) {
                animation[i] = animated.flipImageVertically(animation[i]);
            }
            isFacingLeft = false;
        }
        Thread animationThread = new Thread(() -> {
            for (int i = 1; i < totalFrames; i++) {
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handImage = animation[i];
                panel.repaint();
            }
            handImage = animation[0];
            hit(getDummyEnemyReference(), 1);
            isAttacking = false;
            // resetHitbox();
        });
        animationThread.start();
    }

    @Override
    public void specialattack() {
        this.animation = animated.specialhandfightanimation();
        isAttacking = true;
        totalFrames = animation.length;
        if (panel.getPlayer().isFacingLeft()) {
            panel.getPlayer().setDashLeft(true);
        } else {
            panel.getPlayer().setDashRight(true);
        }
        Thread animationThread = new Thread(() -> {
            for (int i = 0; i < totalFrames; i++) {
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handImage = animation[i];
                panel.repaint();

                hit(getDummyEnemyReference(), 10);
            }
            handImage = animation[0];
            isAttacking = false;
            resetHitbox();
            panel.repaint();
        });

        animationThread.start();
    }

    // other method
    public void updatePosition(int playerX, int playerY) {
        this.x = playerX;
        this.y = playerY;
    }

    private void resetHitbox() {
        this.hitbox = new Rectangle(x, y, handImage.getWidth(null), handImage.getHeight(null));
    }

    public void charge1() {
        if (!animationManager.isAnimating()) {
            animationManager.charge1();
        }
    }

    @Override
    public void hit(Player enemy, int damage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hit'");
    }
}
