package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Loader {
    private BufferedImage image;

    public Loader Loader() {
        return this;
    }
    public static Image loadImage(String imagePath) {
        Image image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Image mainimage() {
        InputStream is = getClass().getResourceAsStream("../assets/spritesheet_default.png");
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Image mainimage(String imagePath) {
        InputStream is = getClass().getResourceAsStream(imagePath);
        Image image = null;
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
