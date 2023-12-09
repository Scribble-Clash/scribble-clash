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

        for (int i = 0; i < 1600; i += 60) {
            walls.add(new Wall(i, 900, groundImage));
        }
        for (int i = 840; i > 200; i -= 60) {
            walls.add(new Wall(0, i, wallImage));
        }
        for (int i = 840; i > 200; i -= 60) {
            walls.add(new Wall(1520, i, wallImage));
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
