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
    public String userId;
    private int totalFrames;
    private boolean isAttacking = false;
    private int dashDistance = 20;
    Animated animated = new Animated();
    private AnimationManager animationManager;

    public Hand(int x, int y, Image img, String userId, GamePanel panel) {
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

    // setter and getter
    private DummyEnemy getDummyEnemyReference() {
        DummyEnemy dummyEnemy = this.getPanel().getDummyEnemy();
        if (dummyEnemy == null) {
            System.out.println("Tidak ada musuh di sekitar");
            return null;
        }
        return dummyEnemy;
    }

    private Object getPlayerReference() {
        if (!data.Players.getPlayerdata(0).getId().equals(this.userId)) {
            return this.getPanel().getPlayer();
        } else if (this.getPanel().getDummyEnemy() != null) {
            return this.getPanel().getDummyEnemy();
        } else {
            System.out.println("Tidak Ada Dummy Atau Player Disekitar");
            return null;
        }
        // return null;
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
    public void hit(Player enemy, int damage) {
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
            this.img = animation[0];
            attackReference(getPlayerReference(), 1); // Use attackReference method here
            isAttacking = false;
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
                img = animation[i];
                attackReference(getPlayerReference(), 10); // Use attackReference method here
            }
            img = animation[0];
            isAttacking = false;
            resetHitbox();
            panel.repaint();
        });

        animationThread.start();
    }

    public void attackReference(Object enemy, int damage) {
        if (enemy instanceof DummyEnemy) {
            hit((DummyEnemy) enemy, damage);
        } else if (enemy instanceof Player) {
            hit((Player) enemy, damage);
        } else {
            // throw an exception or do nothing
        }
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
}
