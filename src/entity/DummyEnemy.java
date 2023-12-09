package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import controller.Loader;
import views.GamePanel;

public class DummyEnemy extends Entity {
    private int health;
    private double ySpeed;
    private double knockbackX;
    private boolean moveLeft = false;
    private boolean isDefeated = false;

    public DummyEnemy(int x, int y, Image img, GamePanel panel) {
        super(x, y);
        this.panel = panel;
        this.img = img;
        width = img.getWidth(panel);
        height = img.getHeight(panel);
        health = 100;
        ySpeed = 0;
        knockbackX = 0;
    }

    // setter and getter
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Overide Method
    @Override
    public void set() {
        if (isDefeated) {
            return;
        }
        if (knockbackX > 0) {
            knockbackX -= 0.5;
        } else if (knockbackX < 0) {
            knockbackX += 0.5;
        }

        ySpeed += 0.5;
        y += ySpeed;

        if (x <= 650) {
            moveLeft = false;
        } else if (x >= 1200) {
            moveLeft = true;
        }

        if (moveLeft) {
            x -= 2;
        } else {
            x += 2;
        }

        for (Wall wall : panel.walls) {
            if (wall.hitbox.intersects(getBounds())) {
                ySpeed = 0;
                y = wall.hitbox.y - height;
            }
        }
        x += knockbackX;
    }

    @Override
    public void draw(Graphics2D gtd) {
        gtd.drawImage(img, x, y, width, height, panel);
    }

    // Other Method
    public void takeDamage(int damage, boolean knockFromRight) {
        health -= damage;
        if (health <= 0 && !isDefeated) {
            isDefeated = true;
            knockbackX = 0;
            Loader load = new Loader();
            BufferedImage image = (BufferedImage) load.mainimage();
            img = image.getSubimage(384, 256, 64, 64);
            width = img.getWidth(panel);
            height = img.getHeight(panel);
        } else {
            if (!isDefeated) {
                if (knockFromRight) {
                    knockbackX = -10;
                } else {
                    knockbackX = 10;
                }
            }
        }
    }

}
