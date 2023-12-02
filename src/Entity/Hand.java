package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Hand extends Entity {
    private int x, y;
    private int width, height;
    private Image handImage;
    private int orientation;

    public Hand(int x, int y, Image img) {
        super(x, y);
        this.handImage = img;
        width = handImage.getWidth(null);
        height = handImage.getHeight(null);
    }

    public void draw(Graphics2D g2d) {
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

    @Override
    public void set() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }
}
