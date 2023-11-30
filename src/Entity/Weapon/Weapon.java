package Entity.Weapon;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class Weapon {
    protected int x, y;
    protected int width, height;
    protected Image weaponImage;
    protected int orientation;

    public Weapon(int x, int y, Image weaponImage) {
        this.x = x;
        this.y = y;
        this.weaponImage = weaponImage;
        width = weaponImage.getWidth(null);
        height = weaponImage.getHeight(null);
    }

    public abstract void setPosition(int x, int y);

    public void setOrientation(int angle) {
        this.orientation = angle;
    }

    public int getOrientation() {
        return orientation;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(weaponImage, x, y, width, height, null);
    }

    public void updatePosition(int handX, int handY) {
        // Mengatur posisi senjata relatif terhadap posisi tangan pemain
        this.x = handX - 5; // Sesuaikan posisi X relatif terhadap tangan pemain
        this.y = handY + 5; // Sesuaikan posisi Y relatif terhadap tangan pemain
    }

    // Getter dan setter lainnya
}
