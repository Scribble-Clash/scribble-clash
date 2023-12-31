package entity;

import java.awt.*;
import views.GamePanel;

public abstract class Entity {
    protected int width, height, x, y;
    protected Image img;
    protected Rectangle hitbox;
    protected GamePanel panel;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // setter and getter
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

    public Rectangle getHitbox() {
        return hitbox;
    }

    public GamePanel getPanel() {
        return panel;
    }

    // abstract method
    public abstract void set();

    public abstract void draw(Graphics2D gtd);

}