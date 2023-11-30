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
        if (keyLeft && !keyRight) {
            xspeed -= 1.0;
        } else if (!keyLeft && keyRight) {
            xspeed += 1.0;
        } else {
            xspeed *= 0.8;
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
            // hitbox.y++;
            for (Wall wall : panel.walls) {
                if (wall.hitbox.intersects(hitbox)) {
                    yspeed = -10;

                }
                // hitbox.y--

            }
        }

        // Tembok collisions
        hitbox.x += xspeed;
        // for (Wall wall : panel.walls) {
        // if (hitbox.intersects(wall.hitbox)) {
        // // if (xspeed > 0) {
        // // // Pemain bergerak ke kanan, atur pemain ke posisi sebelum bersentuhan
        // dengan
        // // // dinding
        // // hitbox.x = wall.hitbox.x - hitbox.width;
        // // } else if (xspeed < 0) {
        // // // Pemain bergerak ke kiri, atur pemain ke posisi sebelum bersentuhan
        // dengan
        // // // dinding
        // // hitbox.x = wall.hitbox.x + wall.hitbox.width;
        // // }
        // // xspeed = 0; // Hentikan pergerakan horizontal
        // x = hitbox.x;
        // }
        // }

        boolean onGround = false; // Menandakan apakah pemain berada di tanah atau tidak
        hitbox.y++;
        for (Wall wall : panel.walls) {
            if (wall.hitbox.intersects(hitbox)) {
                onGround = true;
                yspeed = 0;
                y = wall.hitbox.y - height;
            }
        }
        hitbox.y--;

        if (onGround && keyUp) {
            yspeed = -10; // Ganti 6 dengan 10 untuk melompat lebih tinggi
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
