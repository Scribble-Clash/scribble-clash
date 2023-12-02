package entity;

import java.awt.*;
import views.GamePanel;

public abstract class Entity {
    protected int width, height, x, y;
    protected double xspeed, yspeed;
    protected Image img;
    protected Rectangle hitbox;
    GamePanel panel;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
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

    public Rectangle getHitbox() {
        return hitbox;
    }

    public abstract void set();

    public abstract void draw(Graphics2D gtd);

}