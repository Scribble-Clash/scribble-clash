package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Views.GamePanel;

public class Player {
    GamePanel panel;
    int x, y;
    int widht, height;
    double xspeed;
    double yspeed;

    Rectangle hitbox;

    public boolean keyLeft;
    public boolean keyRight;
    public boolean keyDown;
    public boolean keyUp;

    public Player(int x, int y, GamePanel panel) {
        this.panel = panel;
        this.x = x;
        this.y = y;

        widht = 50;
        height = 100;
        hitbox = new Rectangle(x, y, widht, height);
    }

    public void set() {
        if (keyLeft && keyRight || !keyLeft && !keyRight) {
            xspeed *= 0.8;
        } else if (keyLeft && !keyRight) {
            xspeed--;
        } else if (keyRight && !keyLeft) {
            xspeed++;
        }

        if (xspeed > 0 && xspeed < 0.75) {
            xspeed = 0;
        }
        if (xspeed < 0 && xspeed > -0.75) {
            xspeed = 0;
        }
        if (xspeed > 7) {
            xspeed = 7;
        }
        if (xspeed < -7) {
            xspeed = -7;
        }

        if (keyUp) {
            // checkif touching Ground
            hitbox.y++;
            for (Wall wall : panel.walls) {
                if (wall.hitbox.intersects(hitbox)) {
                    yspeed = -6;
                }
                hitbox.y--;
            }
        }

        // horizontal collisions
        hitbox.x += xspeed;
        for (Wall wall : panel.walls) {
            if (hitbox.intersects(wall.hitbox)) {
                hitbox.x -= xspeed;
                while (!wall.hitbox.intersects(hitbox)) {
                    hitbox.x += Math.signum(xspeed);
                }
                hitbox.x -= Math.signum(xspeed);
                xspeed = 0;
                x = hitbox.x;
            }
        }
        // vertical collisions
        hitbox.y += yspeed;
        for (Wall wall : panel.walls) {
            if (hitbox.intersects(wall.hitbox)) {
                hitbox.y -= yspeed;
                while (!wall.hitbox.intersects(hitbox)) {
                    hitbox.y += Math.signum(yspeed);
                }
                hitbox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitbox.y;
            }
        }

        yspeed += 0.3;
        x += xspeed;
        y += yspeed;

        hitbox.x = x;
        hitbox.y = y;
    }

    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.blue);
        gtd.fillRect(x, y, widht, height);
    }
}
