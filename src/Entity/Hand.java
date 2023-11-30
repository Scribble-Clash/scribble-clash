package Entity;

import java.awt.Graphics2D;
import java.awt.Image;

import Entity.Weapon.Weapon;

public class Hand {
    private int x, y;
    private int width, height;
    private Image handImage;
    private Weapon heldWeapon;
    private int orientation;

    public Hand(int x, int y, Image handImage) {
        this.x = x;
        this.y = y;
        this.handImage = handImage;
        width = handImage.getWidth(null);
        height = handImage.getHeight(null);
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(handImage, x, y, width, height, null);
        if (heldWeapon != null) {
            // Menggambar senjata yang dipegang oleh tangan
            heldWeapon.draw(g2d);
        }
    }

    public void updatePosition(int playerX, int playerY) {
        // Mengatur posisi tangan relatif terhadap pemain
        this.x = playerX + -3;
        this.y = playerY - 25;

        // Jika ada senjata yang dipegang, update posisi senjata tersebut
        if (heldWeapon != null) {
            heldWeapon.setPosition(x, y); // Misalnya, method untuk mengatur posisi senjata
        }
    }

    public void holdWeapon(Weapon weapon) {
        this.heldWeapon = weapon;
        weapon.setPosition(x, y); // Atur posisi senjata sesuai posisi tangan
    }

    // Fungsi untuk melepaskan senjata dari tangan
    public void releaseWeapon() {
        this.heldWeapon = null;
    }

    public Weapon getHeldWeapon() {
        return heldWeapon;
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
        // Lakukan apa pun yang diperlukan untuk mengatur orientasi tangan
    }

    public int getOrientation() {
        return orientation;
    }
}
