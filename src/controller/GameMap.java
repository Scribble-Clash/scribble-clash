package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.BgrAssets;
import entity.Wall;
import views.GamePanel;

public class GameMap {
    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<BgrAssets> assets = new ArrayList<>();

    private BufferedImage image;
    private Loader loadimg = new Loader();

    public GameMap(GamePanel panel) {

    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<BgrAssets> getAssets() {
        return assets;
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

    public void Map1() {
        image = (BufferedImage) loadimg.mainimage();
        Image cloud1 = image.getSubimage(0, 0, 128, 64);
        Image brick = image.getSubimage(128, 448, 64, 64);
        Image bridge = image.getSubimage(320, 256, 64, 65);
        Image grass = image.getSubimage(192, 450, 64, 64);

        Image wallImage = image.getSubimage(320, 321, 64, 64);

        Image tree = image.getSubimage(128, 320, 64, 64);
        Image bush = image.getSubimage(320, 128, 64, 64);

        for (int i = 100; i < 1800; i += 60) {
            walls.add(new Wall(i, 960, grass));
        }

        for (int i = 400; i < 1520; i += 60) {
            walls.add(new Wall(i, 500, bridge));
        }
        for (int i = 100; i < 300; i += 60) {
            walls.add(new Wall(i, 700, brick));
        }
        for (int i = 1600; i < 1800; i += 60) {
            walls.add(new Wall(i, 700, brick));
        }
        for (int i = 0; i < 1820; i += 430) {
            assets.add(new BgrAssets(i, 10, cloud1));
        }

        // pohon
        assets.add(new BgrAssets(100, 900, tree));
        assets.add(new BgrAssets(1780, 900, tree));
        assets.add(new BgrAssets(160, 900, bush));
        assets.add(new BgrAssets(1720, 900, bush));
        assets.add(new BgrAssets(220, 900, bush));
        assets.add(new BgrAssets(1660, 900, bush));
        assets.add(new BgrAssets(280, 900, bush));
        assets.add(new BgrAssets(1600, 900, bush));

        // wall
        walls.add(new Wall(340, 900, wallImage));
        walls.add(new Wall(1540, 900, wallImage));
        walls.add(new Wall(1540, 900, wallImage));
        walls.add(new Wall(1340, 780, wallImage));
        walls.add(new Wall(550, 780, wallImage));

        // wall tangga kiri
        walls.add(new Wall(100, 640, wallImage));
        walls.add(new Wall(160, 640, wallImage));
        walls.add(new Wall(220, 640, wallImage));
        walls.add(new Wall(100, 580, wallImage));
        walls.add(new Wall(160, 580, wallImage));
        walls.add(new Wall(100, 520, wallImage));

        // wall tangga kanan
        walls.add(new Wall(1780, 640, wallImage));
        walls.add(new Wall(1780, 580, wallImage));
        walls.add(new Wall(1780, 520, wallImage));
        walls.add(new Wall(1720, 640, wallImage));
        walls.add(new Wall(1720, 640, wallImage));
        walls.add(new Wall(1720, 580, wallImage));
        walls.add(new Wall(1660, 640, wallImage));

        for (int i = 400; i < 1490; i += 60) {
            walls.add(new Wall(i, 840, brick));
        }
    }

    // tambah map baru di bawah

}
