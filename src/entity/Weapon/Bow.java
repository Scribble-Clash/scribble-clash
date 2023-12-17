package entity.Weapon;

import java.awt.*;
import java.awt.image.BufferedImage;

import controller.Loader;
import entity.DummyEnemy;
import entity.Player;

public class Bow extends Weapon {
    BufferedImage image;

    public Bow(int x, int y) {
        super(x, y);
        Loader load = new Loader();
        image = (BufferedImage) load.mainimage();
        this.img = image.getSubimage(512, 128, 64, 64);
        this.hitbox = new Rectangle(x, y, img.getWidth(null) - 11, img.getHeight(null));

    }

    // setter and getter
    public void setPosition(int handX, int handY) {
        this.x = handX;
        this.y = handY;
    }

    // overide method
    @Override
    public void hit(DummyEnemy enemy, int damage) {
        throw new UnsupportedOperationException("Unimplemented method 'hit'");
    }

    @Override
    public void attack() {

    }

    @Override
    public void specialattack() {

    }

    @Override
    public void hit(Player enemy, int damage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hit'");
    }

}
