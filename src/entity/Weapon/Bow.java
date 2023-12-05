package entity.Weapon;

import java.awt.*;
import java.awt.image.BufferedImage;

import controller.Loader;

public class Bow extends Weapon {
    BufferedImage image;

    public Bow(int x, int y) {
        super(x, y);
        Loader load = new Loader();
        image = (BufferedImage) load.mainimage();
        this.img = image.getSubimage(512, 128, 64, 64);
        this.hitbox = new Rectangle(x, y, img.getWidth(null) - 11, img.getHeight(null));

    }

    public void setPosition(int handX, int handY) {
        this.x = handX;
        this.y = handY;
    }

    @Override
    public void hit() {
    }

    @Override
    public void attack() {

    }

}
