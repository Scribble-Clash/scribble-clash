package Entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Wall {
    private int x, y;
    private Image image;
    public Rectangle hitbox; // Ganti hitbox dengan Rectangle yang sesuai dengan gambar

    public Wall(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;

        // Buat hitbox dengan ukuran sesuai dengan gambar
        this.hitbox = new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, null); // Gambar gambar wall di sini
    }
}
