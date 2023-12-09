package entity.Weapon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;

public class Arrow extends Entity {
    private int x, y; // Koordinat anak panah
    private BufferedImage image;
    private boolean shot;

    public Arrow(int x, int y, BufferedImage arrowImage) {
        super(x, y);
        this.image = arrowImage;
        this.shot = false;
    }

    // overide method
    @Override
    public void set() {
    }

    @Override
    public void draw(Graphics2D g) {
        if (shot) {
            g.drawImage(image, x, y, null);
        }
    }

    // other method
    public void shoot(int bowX, int bowY) {

        this.x = bowX;
        this.y = bowY;
        this.shot = true;
    }

    public boolean isShot() {
        return shot;
    }
}