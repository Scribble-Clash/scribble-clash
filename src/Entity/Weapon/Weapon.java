package entity.Weapon;

import java.awt.*;

import entity.Entity;

public abstract class Weapon extends Entity {
    public Weapon(int x, int y) {
        super(x, y);
        this.img = img;
        this.hitbox = new Rectangle(x, y, img.getWidth(null) - 11, img.getHeight(null));
    }

    @Override
    public void set() {
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, x, y, null);
    }

    public abstract void hit();

    public abstract void attack();

}
