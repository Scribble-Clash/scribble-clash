package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Wall {
    int x;
    int y;
    int widht;
    int height;

    Rectangle hitbox;

    public Wall(int x, int y, int widht, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.widht = widht;

        hitbox = new Rectangle(x, y, widht, height);
    }

    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.black);
        gtd.drawRect(x, y, widht, height);
        gtd.setColor(Color.white);
        gtd.fillRect(x + 1, y + 1, widht - 2, height - 2);

    }
}
