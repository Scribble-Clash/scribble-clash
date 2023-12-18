package entity;

import java.awt.Graphics2D;
import java.awt.Image;

public class BgrAssets extends Entity {

    public BgrAssets(int x, int y, Image img) {
        super(x, y);
        this.img = img;
    }

    @Override
    public void set() {
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, x, y, null);
    }

}