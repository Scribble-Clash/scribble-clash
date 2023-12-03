package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import entity.Weapon.Weapon;

public class Hand extends Entity {
    private int x, y;
    private int width, height;
    private Image handImage;
    private int orientation;
    private Weapon heldWeapon; // Menyimpan referensi ke Weapon yang sedang digunakan

    public Hand(int x, int y, Image img) {
        super(x, y);
        this.handImage = img;
        width = handImage.getWidth(null);
        height = handImage.getHeight(null);
    }

    public void draw(Graphics2D g2d) {
        if (heldWeapon != null) {
            heldWeapon.setPosition(x, y);
            heldWeapon.draw(g2d);
        }
        g2d.drawImage(handImage, x, y, width, height, panel);
    }

    public void updatePosition(int playerX, int playerY) {
        this.x = playerX;
        this.y = playerY;
    }

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

    @Override
    public void set() {

    }
}
