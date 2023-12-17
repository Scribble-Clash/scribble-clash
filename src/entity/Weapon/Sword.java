package entity.Weapon;

import java.awt.*;
import java.awt.image.BufferedImage;

import animation.Animated;
import animation.AnimationManager;
import controller.Loader;
import data.Multiplayer;
import entity.DummyEnemy;
import entity.Player;
import views.GamePanel;

public class Sword extends Weapon {
    public String userId;
    private BufferedImage[] animation;
    private int totalFrames;
    private int damage;
    private boolean isAttacking = false;
    BufferedImage image;
    Loader load = new Loader();
    Animated animated = new Animated();
    private AnimationManager animationManager;

    public Sword(int x, int y, int damage, String userId, GamePanel panel) {
        super(x, y);
        this.userId = userId;
        this.panel = panel;
        image = (BufferedImage) load.mainimage();
        this.img = image.getSubimage(448, 0, 64, 64);
        this.hitbox = new Rectangle(x, y, this.img.getWidth(this.getPanel()), this.img.getHeight(this.getPanel()));
        this.damage = damage;
        this.animationManager = new AnimationManager(panel);

    }

    // setter and getter
    private DummyEnemy getDummyEnemyReference() {
        return this.getPanel().getDummyEnemy();
    }

    private Player getPlayerReference() {
        if (!data.Players.getPlayerdata(0).getId().equals(this.userId)) {
            return this.getPanel().getPlayer();
        }
        return null;
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
    public void hit(Player enemy, int damage) {
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
        this.animation = animated.swingAnimation();
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
                this.img = animation[i];
                panel.repaint();
            }
            hit(getPlayerReference(), 1);
            this.img = animation[0];
            isAttacking = false;
        });
        animationThread.start();
    }

    @Override
    public void specialattack() {
        this.animation = animated.specialswordanimation();
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
                img = animation[i];
                hit(getDummyEnemyReference(), 10);
            }
            img = animation[0];

            isAttacking = false;
            resetHitbox();
            panel.repaint();
        });

        animationThread.start();
    }

    // other method
    private void resetHitbox() {
        this.hitbox = new Rectangle(x, y, this.img.getWidth(this.getPanel()), this.img.getHeight(this.getPanel()));
    }

    public void charge1() {
        if (!animationManager.isAnimating()) {
            animationManager.charge1();
        }
    }

    public void charge2() {
        if (!animationManager.isAnimating()) {
            animationManager.charge2();
        }
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
