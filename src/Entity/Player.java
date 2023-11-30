package Entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import Controllers.Loader;
import Entity.Weapon.*;
import Views.GamePanel;

public class Player {
    GamePanel panel;
    int x, y;
    int width, height;
    double xspeed;
    double yspeed;

    public boolean keyLeft;
    public boolean keyRight;
    public boolean keyDown;
    public boolean keyUp;

    Image playerImage;
    Rectangle hitbox;
    private Hand hand;
    private Rectangle handHitbox;
    String wea = "D:\\#Programing\\Project\\Fight-Scribble\\Fight-ScribbleGit\\scribble-fight\\assets\\sword.png";

    public Player(int x, int y, Image playerImage, Image handImage, GamePanel panel) {
        this.panel = panel;
        this.x = x;
        this.y = y;

        this.playerImage = playerImage;
        width = playerImage.getWidth(panel);
        height = playerImage.getHeight(panel);
        hitbox = new Rectangle(x, y, width, height);

        hand = new Hand(x + width / 2, y + height / 2, handImage);
        handHitbox = new Rectangle(hand.getX(), hand.getY(), hand.getWidth(), hand.getHeight());
        Image weaponImage = Loader.loadImage(wea);

        Sword weapon = new Sword(0, 0, weaponImage); // Membuat objek Weapon (misalnya)
        hand.holdWeapon(weapon); //
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
            hitbox.y++;
            for (Wall wall : panel.walls) {
                if (wall.hitbox.intersects(hitbox)) {
                    yspeed = -10;

                }
            }
            hitbox.y--;

        }

        // Tembok collisions
        hitbox.x += xspeed;
        for (Wall wall : panel.walls) {
            if (hitbox.intersects(wall.hitbox)) {
                if (xspeed > 0) {
                    // Pemain bergerak ke kanan, atur pemain ke posisi sebelum bersentuhan
                    // dengan
                    // dinding
                    hitbox.x = wall.hitbox.x - hitbox.width;
                } else if (xspeed < 0) {
                    // Pemain bergerak ke kiri, atur pemain ke posisi sebelum bersentuhan
                    // dengan
                    // dinding
                    hitbox.x = wall.hitbox.x + wall.hitbox.width;
                }
                // Hentikan pergerakan horizontal
                xspeed = 0;
                x = hitbox.x;
            }
        }

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

        // Update posisi hitbox pemain dan tangan
        hitbox.x = x;
        hitbox.y = y;
        hand.updatePosition(x + width / 2, y + height / 2); // Memperbarui posisi tangan
        handHitbox.setBounds(hand.getX(), hand.getY(), hand.getWidth(), hand.getHeight()); // Memperbarui hitbox tangan
    }

    public Hand getHand() {
        return hand;
    }

    public Point getHandPosition() {
        int handX = this.x + this.width / 2;
        int handY = this.y + this.height / 2;

        return new Point(handX, handY);
    }

    public void draw(Graphics2D gtd) {
        gtd.drawImage(playerImage, x, y, width, height, panel);
        hand.draw(gtd);
    }
}
