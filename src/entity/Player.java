package entity;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.Objects;

import controller.Loader;
import data.Multiplayer;
import data.Players;
import entity.Weapon.*;
import views.GamePanel;
import animation.AnimationManager;

public class Player extends Entity {
    String id;
    // generate 4 length string
    String roomCode;
    private boolean keyLeft;
    private boolean keyRight;
    private boolean keyDown;
    private boolean keyUp;
    private int health;
    private int lives;

    protected double xspeed, yspeed;
    private Image originalImg;
    private Hand hand;
    private Weapon heldWeapon;
    private boolean facingLeft;
    boolean isDashLeft = false;
    boolean isDashRight = false;
    private boolean isDefeated = false;
    private int startingX;
    private int startingY;
    private double knockbackX;
    AnimationManager animationManager;

    public Player(int x, int y, int colorId, GamePanel panel, String roomCode, String playerId) {
        super(x, y);

        this.id = playerId;
        BufferedImage sprites = (BufferedImage) new Loader().mainimage();
        switch (colorId) {
            case 1:
                this.img = sprites.getSubimage(576, 448, 64, 64);
                this.hand = new Hand(x + width, y + height, sprites.getSubimage(640, 0, 64, 64), this.panel);
            case 2:
        }

        this.panel = panel;
        this.img = img;
        width = img.getWidth(panel);
        height = img.getHeight(panel);
        this.hitbox = new Rectangle(x, y, width, height);
        this.originalImg = img;

        setHealth(100);
        setLives(3);

        startingX = x;
        startingY = y;

        new Rectangle(hand.getX(), hand.getY(), hand.getWidth(), hand.getHeight());
        heldWeapon = new Sword(x + width, y + height, 10, this.panel);

        // generate string random 6 character
        // roomCode = new StringBuilder();
        // String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        // int length = 6;
        // for (int i = 0; i < length; i++) {
        // int index = (int) (Math.random() * characters.length());
        // roomCode.append(characters.charAt(index));
        // }

        this.roomCode = roomCode;
    }

    // setter and getter
    public void setDashLeft(boolean isDashLeft) {
        this.isDashLeft = isDashLeft;
    }

    public void setDashRight(boolean isDashRight) {
        this.isDashRight = isDashRight;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Hand getHand() {
        return hand;
    }

    public Weapon getHeldWeapon() {
        return heldWeapon;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public int getHealth() {
        return health;
    }

    public void setFirebaseHealth (int health) {
        this.health = health;
    }

    public void setHealth(int health) {
        if (this.id.equals(Multiplayer.id) && Players.getData(this.id) != null) {
            Players.getData(this.id).setHealth(health);
        }
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Point getHealthTextPosition() {
        int textX = x + (width) - 60;
        int textY = y - 10;
        return new Point(textX, textY);
    }

    public void setKeyDown(boolean keyDown) {
        this.keyDown = keyDown;
    }

    public void setKeyLeft(boolean keyLeft) {
        this.keyLeft = keyLeft;
    }

    public void setKeyRight(boolean keyRight) {
        this.keyRight = keyRight;
    }

    public void setKeyUp(boolean keyUp) {
        this.keyUp = keyUp;
    }

    // overide method
    @Override
    public void set() {
        int dashDistance = 60;
        boolean canDash = true;
        if (isDashLeft) {
            for (Wall wall : panel.walls) {
                Rectangle nextX = new Rectangle(x - dashDistance, y, width, height);
                if (nextX.intersects(wall.hitbox)) {
                    canDash = false;
                    break;
                }
            }
            if (canDash) {
                x -= dashDistance;
            }
        } else if (isDashRight) {
            for (Wall wall : panel.walls) {
                Rectangle nextX = new Rectangle(x + dashDistance, y, width, height);
                if (nextX.intersects(wall.hitbox)) {
                    canDash = false;
                    break;
                }
            }
            if (canDash) {
                x += dashDistance;
            }
        }
        isDashLeft = false;
        isDashRight = false;

        if (keyLeft && !keyRight) {
            xspeed -= 0.5;
            facingLeft = true;
        } else if (!keyLeft && keyRight) {
            facingLeft = false;
            xspeed += 0.5;
        } else {
            xspeed *= 0.6;
        }

        if (xspeed > 0 && xspeed < 0.5) {
            xspeed = 0;
        }
        if (xspeed < 0 && xspeed > -0.5) {
            xspeed = 0;
        }
        if (xspeed > 5) {
            xspeed = 5;
        }
        if (xspeed < -5) {
            xspeed = -5;
        }
        if (xspeed < 0) {
            img = flipImageHorizontally(originalImg);
        } else if (xspeed > 0) {
            img = originalImg;
        }

        if (keyUp) {
            hitbox.y--;
            for (Wall wall : panel.walls) {
                if (wall.hitbox.intersects(hitbox)) {
                    y = wall.hitbox.y + wall.hitbox.height;
                }
            }
            hitbox.y++;
        }

        // Logika collision dengan dinding (horizontal)
        hitbox.x += xspeed;
        for (Wall wall : panel.walls) {
            if (hitbox.intersects(wall.hitbox)) {
                if (xspeed > 0) {
                    hitbox.x = wall.hitbox.x - hitbox.width;
                } else if (xspeed < 0) {
                    hitbox.x = wall.hitbox.x + wall.hitbox.width;
                }
                xspeed = 0;
                x = hitbox.x;
            }
        }
        // Logika collision dengan dinding (vertical)
        hitbox.y += yspeed;
        for (Wall wall : panel.walls) {
            if (hitbox.intersects(wall.hitbox)) {
                if (yspeed > 0) {
                    hitbox.y = wall.hitbox.y - hitbox.height;
                } else if (yspeed < 0) {
                    hitbox.y = wall.hitbox.y + wall.hitbox.height;
                }
                yspeed = 0;
                y = hitbox.y;
            }
        }
        boolean onGround = false;
        hitbox.y++;
        for (Wall wall : panel.walls) {
            if (wall.hitbox.intersects(hitbox)) {
                onGround = true;
                yspeed = 0;
                y = wall.hitbox.y - height;
            }
        }
        hitbox.y--;

        // Logika lompatan saat di atas tanah
        if (onGround) {
            if (keyUp) {
                yspeed = -6;
            } else {
                yspeed = 0;
            }
        } else {
            yspeed += 0.3;
        }

        // Logika Mati Spawn Lagi
        int maxYBoundary = 1000;
        if (y > maxYBoundary) {
            lives--;
            if (lives >= 0) {
                respawnPlayer();
            } else {
                isDefeated = true;
            }
        }

        // knockback Logic
        if (isDefeated) {
            this.lives--;
            if (this.lives >= 0) {
                respawnPlayer();
            }
        }
        if (knockbackX > 0) {
            knockbackX -= 0.5;
        } else if (knockbackX < 0) {
            knockbackX += 0.5;
        }
        // Pembaruan posisi
        x += xspeed;
        y += yspeed;
        hitbox.x = x;
        hitbox.y = y;

        if (!data.Players.isEmpty() && this.id.equals(Multiplayer.id)) {
            Players.getData(Multiplayer.id).setPlayerPosition(x, y);
            Players.getData(Multiplayer.id).setSpeedX(xspeed);
        }
    }

    public void setSpeedX(Double xspeed) {
        this.xspeed = xspeed;
    }

    public void setXPosition(int x) {
        this.x = x;
    }

    public void setYPosition(int y) {
        this.y = y;
    }

    public void setHandXPosition(int x) {
        getHand().setPosition(x, getHand().getY());
    }

    public void setHandYPosition(int y) {
        getHand().setPosition(getHand().getX(), y);
    }

    @Override
    public void draw(Graphics2D gtd) {
        if (!isDefeated) {
            gtd.drawImage(img, x, y, width, height, panel);
            gtd.setColor(Color.RED);
            gtd.fillRect(x, y - 10, width, 5); // Contoh: Gambar label darah sebagai kotak merah di atas enemy
            gtd.setColor(Color.GREEN);
            double healthBarWidth = ((double) health / 100) * width;
            gtd.fillRect(x, y - 10, (int) healthBarWidth, 5); // Contoh: Gambar label hijau sebagai indikator darah
        }
        gtd.drawImage(img, x, y, width, height, panel);

        if (heldWeapon != null) {
            heldWeapon.setPosition(hand.getX(), hand.getY());
            heldWeapon.draw(gtd);
        }
        hand.draw(gtd);
    }

// other method

    private void respawnPlayer() {
        x = startingX + (int) (Math.random() * (1300 - 500) - 100);
        y = startingY - 500;
        setHealth(100);
    }

    public void takeDamage(int damage, boolean knockFromRight) {
        setHealth(health - damage);
        if (health <= 0 && !isDefeated) {
            isDefeated = true;
            knockbackX = 0;
            Loader load = new Loader();
            BufferedImage image = (BufferedImage) load.mainimage();
            img = image.getSubimage(384, 256, 64, 64);
            width = img.getWidth(panel);
            height = img.getHeight(panel);
        } else {
            if (!isDefeated) {
                if (knockFromRight) {
                    knockbackX = -10;
                    blod();
                } else {
                    knockbackX = 10;
                    blod();
                }
            }
        }
    }

    public void blod() {
        if (!animationManager.isAnimating()) {
            animationManager.startBlodAnimation();
        }
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updateHandPosition(int mouseX, int mouseY) {
        int playerX = getX() + getWidth() / 2;
        int playerY = getY() + getHeight() / 2;

        int dx = mouseX - playerX;
        int dy = mouseY - playerY;

        double angleRadians = Math.atan2(dy, dx);

        int maxHandDistanceFromMouse = 30;
        double distanceToPlayer = Math.sqrt(dx * dx + dy * dy);
        int newHandDistanceFromCenter = (int) Math.min(distanceToPlayer, maxHandDistanceFromMouse);

        int handX = (int) (playerX + newHandDistanceFromCenter * Math.cos(angleRadians))
                - getHand().getWidth() / 2;
        int handY = (int) (playerY + newHandDistanceFromCenter * Math.sin(angleRadians))
                - getHand().getHeight() / 2;

        int maxX = playerX + getWidth() / 2 - getHand().getWidth() / 2;
        int maxY = playerY + getHeight() / 2 - getHand().getHeight() / 2;

        handX = Math.min(Math.max(handX, playerX - maxX), playerX + maxX);
        handY = Math.min(Math.max(handY, playerY - maxY), playerY + maxY);

        getHand().updatePosition(handX, handY);
        if (!data.Players.isEmpty() && Objects.equals(Multiplayer.id, this.id)) {
            Players.getData(Multiplayer.id).setHandPosition(handX, handY);
        }
    }

    private Image flipImageHorizontally(Image img) {
        BufferedImage bufferedImage = (BufferedImage) img;
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-bufferedImage.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(bufferedImage, null);
    }

}
