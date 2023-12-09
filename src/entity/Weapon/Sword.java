package entity.Weapon;

import java.awt.*;
import java.awt.image.BufferedImage;

import animation.Animated;
import controller.Loader;
import entity.DummyEnemy;
import views.GamePanel;

public class Sword extends Weapon {
    private BufferedImage[] animation;
    private int totalFrames;
    private int damage;
    private boolean isAttacking = false;
    BufferedImage image;
    Loader load = new Loader();
    Animated animated = new Animated();

    public Sword(int x, int y, int damage, GamePanel panel) {
        super(x, y);
        this.panel = panel;
        image = (BufferedImage) load.mainimage();
        this.img = image.getSubimage(448, 0, 64, 64); // Make sure img is properly assigned
        this.hitbox = new Rectangle(x, y, this.img.getWidth(this.getPanel()), this.img.getHeight(this.getPanel()));
        this.damage = damage;
    }

    // setter and getter
    private DummyEnemy getDummyEnemyReference() {
        return this.getPanel().getDummyEnemy();
    }

    public int getDamage() {
        return damage;
    }

    // overide method

    @Override
    public void hit(DummyEnemy enemy, int damage) {
        Rectangle swordRect = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
        Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

        if (swordRect.intersects(enemyRect) && isAttacking) {
            boolean knockFromRight = panel.getPlayer().getX() > enemy.getX();
            enemy.takeDamage(damage, knockFromRight);
            isAttacking = false;
        }
    }

    @Override
    public void attack() {
        animation = animated.swingAnimation();
        totalFrames = animation.length;
        isAttacking = true;
        boolean isFacingLeft = panel.getPlayer().isFacingLeft();
        if (isFacingLeft) {
            for (int i = 1; i < totalFrames; i++) {
                animation[i] = animated.flipImageHorizontally(animation[i]);
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
                img = animation[i];
                panel.repaint();
            }
            hit(getDummyEnemyReference(), this.damage);
            img = animation[0];
            isAttacking = false;
            resetHitbox();
        });
        animationThread.start();
    }

    @Override
    public void specialattack() {

    }

    // other method
    private void resetHitbox() {
        this.hitbox = new Rectangle(x, y, this.img.getWidth(this.getPanel()), this.img.getHeight(this.getPanel()));
    }

    @Override
    public void setPosition(int handX, int handY) {
        // posisi saat pedang hadap atwas
        this.x = handX;
        this.y = handY - 20;
        // // posisis saat pedang hadap kanan
        // this.x = handX + 20;
        // this.y = handY;
        // // posisis saat pedang hadap bawah
        // this.x = handX;
        // this.y = handY + 20;
        // // posisis saat pedang hadap kiri
        // this.x = handX - 20;
        // this.y = handY;

    }

}
