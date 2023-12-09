package entity.Weapon;

import java.awt.*;
import java.awt.image.BufferedImage;

import animation.Animated;
import controller.Loader;
import entity.DummyEnemy;
import views.GamePanel;

public class Sword extends Weapon {
    private BufferedImage[] swingAnimation;
    private int totalFrames;
    private int damage;
    private boolean isAttacking = false;

    public Sword(int x, int y, int damage, GamePanel panel) {
        super(x, y);
        this.panel = panel;
        this.img = (BufferedImage) Loader.loadImage("src\\assets\\pedangkanan.png");
        this.hitbox = new Rectangle(x, y, img.getWidth(this.getPanel()), img.getHeight(this.getPanel()));
        Animated animated = new Animated();
        swingAnimation = animated.swingAnimation();
        this.damage = damage;
    }

    public void checkCollision(DummyEnemy enemy) {
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
        totalFrames = swingAnimation.length;
        Thread animationThread = new Thread(() -> {
            isAttacking = true;

            for (int i = 1; i < totalFrames; i++) {
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                img = swingAnimation[i];

                checkCollision(getDummyEnemyReference());
            }
            img = swingAnimation[0];
            resetHitbox();
        });
        animationThread.start();
    }

    private DummyEnemy getDummyEnemyReference() {
        return this.getPanel().getDummyEnemy();
    }

    private void resetHitbox() {
        this.hitbox = new Rectangle(x, y, img.getWidth(this.getPanel()), img.getHeight(this.getPanel()));
    }

    @Override
    public void setPosition(int handX, int handY) {
        // posisi saat pedang hadap atwas
        // this.x = handX;
        // this.y = handY - 20;
        // // posisis saat pedang hadap kanan
        this.x = handX + 20;
        this.y = handY;
        // // posisis saat pedang hadap bawah
        // this.x = handX;
        // this.y = handY + 20;
        // // posisis saat pedang hadap kiri
        // this.x = handX - 20;
        // this.y = handY;

    }

    @Override
    public void hit() {
    }

}
