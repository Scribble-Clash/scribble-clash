package entity;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

import views.GamePanel;

public class Player extends Entity {
    public boolean keyLeft;
    public boolean keyRight;
    public boolean keyDown;
    public boolean keyUp;
    private int health;
    private int maxHealth;
    private Image healthBarImg;
    private Image originalImg;
    private Hand hand;
    private Rectangle handHitbox;
    private boolean facingLeft;

    public Player(int x, int y, Image img, Image handImage, GamePanel panel) {
        super(x, y);
        this.panel = panel;
        this.img = img;
        width = img.getWidth(panel);
        height = img.getHeight(panel);
        this.hitbox = new Rectangle(x, y, width, height);

        hand = new Hand(x + width, y + height, handImage);
        handHitbox = new Rectangle(hand.getX(), hand.getY(), hand.getWidth(), hand.getHeight());

        this.healthBarImg = healthBarImg;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.originalImg = img;
    }

    public void reduceHealth(int amount) {
        health -= amount;
        if (health <= 0) {

        }
    }

    @Override
    public void set() {
        if (keyLeft && !keyRight) {
            xspeed -= 0.5;
            // facingLeft = true;
        } else if (!keyLeft && keyRight) {
            // facingLeft = false;
            xspeed += 0.5;
        } else {
            xspeed *= 0.6;
        }

        if (xspeed > 0 && xspeed < 0.5) {
            xspeed = 0;
        }
        if (xspeed < 0 && xspeed > -0.5) {
            xspeed = 0;
        }
        if (xspeed > 5) {
            xspeed = 5;
        }
        if (xspeed < -5) {
            xspeed = -5;
        }
        if (xspeed < 0) {
            img = flipImageHorizontally(originalImg);
        } else if (xspeed > 0) {
            img = originalImg;
        }
        if (keyUp) {
            hitbox.y++;
            for (Wall wall : panel.walls) {
                if (wall.hitbox.intersects(hitbox)) {
                    yspeed = -6;
                }
            }
            hitbox.y--;

        }
        // Tembok collisions
        hitbox.x += xspeed;
        for (Wall wall : panel.walls) {
            if (hitbox.intersects(wall.hitbox)) {
                if (xspeed > 0) {

                    hitbox.x = wall.hitbox.x - hitbox.width;
                } else if (xspeed < 0) {
                    hitbox.x = wall.hitbox.x + wall.hitbox.width;
                }
                xspeed = 0;
                x = hitbox.x;
            }
        }
        boolean onGround = false;
        hitbox.y++;
        for (Wall wall : panel.walls) {
            if (wall.hitbox.intersects(hitbox)) {
                onGround = true;
                yspeed = 0;
                y = wall.hitbox.y - height;
            }
        }
        hitbox.y--;

        // Penanganan saat di atas tanah
        if (onGround) {
            if (keyUp) {
                yspeed = -6;
            } else {
                yspeed = 0;
            }
        } else {
            yspeed += 0.3;
        }

        // Pembaruan posisi
        x += xspeed;
        y += yspeed;

        hitbox.x = x;
        hitbox.y = y;

    }

    private Image flipImageHorizontally(Image img) {
        BufferedImage bufferedImage = (BufferedImage) img;
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-bufferedImage.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(bufferedImage, null);
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public void draw(Graphics2D gtd) {
        gtd.drawImage(img, x, y, width, height, panel);
        hand.draw(gtd);

        // if (facingLeft) {
        // hand.updatePosition(x + width / 2 - 60, y + height / 2);
        // } else {
        // hand.updatePosition(x + width / 2, y + height / 2);
        // }

        // if (xspeed == 0 && facingLeft) {
        // hand.updatePosition(x + width / 2 - 60, y + height / 2);
        // }
    }

}
