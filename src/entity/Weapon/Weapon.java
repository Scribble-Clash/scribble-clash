package entity.Weapon;

import java.awt.*;

import entity.DummyEnemy;
import entity.Entity;
import entity.Player;

public abstract class Weapon extends Entity {
    public Weapon(int x, int y) {
        super(x, y);
    }

    // overide method
    @Override
    public void set() {
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, x, y, panel);
    }

    // abstract method
    public abstract void setPosition(int x, int y);

    public abstract void hit(DummyEnemy enemy, int damage);

    public abstract void hit(Player enemy, int damage);

    public abstract void attack();

    public abstract void specialattack();

}
