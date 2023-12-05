package entity;

import java.awt.*;

public class Wall extends Entity {
    public Rectangle hitbox;
    Image img;

    public Wall(int x, int y, Image img) {
        super(x, y);
        this.img = img;
        this.hitbox = new Rectangle(x, y, img.getWidth(null) - 11, img.getHeight(null));
    }

    @Override
    public void set() {
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, x, y, null);
    }
}
