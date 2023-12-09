package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Wall;
import views.GamePanel;

public class GameMap {
    private ArrayList<Wall> walls = new ArrayList<>();
    private BufferedImage image;
    private Loader loadimg = new Loader();

    public GameMap(GamePanel panel) {

    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void testMap() {
        image = (BufferedImage) loadimg.mainimage();
        Image groundImage = image.getSubimage(192, 450, 64, 64);
        Image wallImage = image.getSubimage(320, 321, 64, 64);

        for (int i = 400; i < 1540; i += 60) {
            walls.add(new Wall(i, 960, groundImage));
        }
        for (int i = 900; i > 700; i -= 60) {
            walls.add(new Wall(400, i, wallImage));
        }
        for (int i = 900; i > 700; i -= 60) {
            walls.add(new Wall(1470, i, wallImage));
        }
        walls.add(new Wall(600, 900, wallImage));
        walls.add(new Wall(1260, 900, wallImage));
        walls.add(new Wall(1200, 900, wallImage));
        walls.add(new Wall(460, 780, wallImage));
        walls.add(new Wall(1410, 780, wallImage));

        for (int i = 900; i > 850; i -= 60) {
            walls.add(new Wall(660, i, wallImage));
        }
        for (int i = 660; i < 1260; i += 60) {
            walls.add(new Wall(i, 840, wallImage));
        }
    }

    // public void Map1() {
    // this.groundImage = Loader.loadImage(groundImagePath);
    // this.wallImage = Loader.loadImage(wallImagePath);
    // for (int i = 0; i < 1900; i += 50) {
    // walls.add(new Wall(i, 1000, groundImage));
    // }
    // for (int i = 1000; i > 200; i -= 50) {
    // walls.add(new Wall(0, i, wallImage));
    // }
    // for (int i = 1000; i > 200; i -= 50) {
    // walls.add(new Wall(1850, i, wallImage));
    // }
    // }

    // tambah map baru di bawah

}
