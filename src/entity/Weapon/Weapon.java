package entity.Weapon;

import java.awt.*;

import entity.Entity;

public abstract class Weapon extends Entity {
    public Weapon(int x, int y) {
        super(x, y);
    }

    @Override
    public void set() {
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, x, y, panel);
    }

    public abstract void setPosition(int x, int y);

    public abstract void hit();

    public abstract void attack();

}
